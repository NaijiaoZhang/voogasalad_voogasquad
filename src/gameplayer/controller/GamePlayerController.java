package gameplayer.controller;

import gameplayer.loader.GamePlayerFactory;
import gameplayer.loader.GameSavingController;
import gameplayer.loader.SavedSettings;
import gameplayer.loader.XMLParser;
import gameplayer.model.GamePlayData;
import gameplayer.model.GamePlayModel;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import gameplayer.model.tower.Tower;
import gameplayer.model.weapon.Weapon;
import gameplayer.view.GameGUI;
import gameplayer.view.helper.GraphicsLibrary;
import gameplayer.view.helper.dragdrop.DragDropView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import statswrapper.Wrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import engine.path.PathManager;

public class GamePlayerController implements Observer {
	public static final int Y_OFFSET = 54;
	public static final int ENTITY_SIZE = 70;
	public static final int FRAMES_PER_SECOND = 60;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private GamePlayerFactory loader;
	private GameGUI view;
	private Scene mainScene;
	private GamePlayModel model;
	private Timeline animation;
	private EnemyController enemyController;
	private TowerController towerController;
	private WeaponController weaponController;
	private CollisionController collisionController;
	private GameSavingController gameSavingController;
	private DragDropController dropController;
	private GameLoopController loopController; 
	private EnemyManager enemyManager;
	private double oldLevel;
	private GraphicsLibrary graphics;
	private Queue<Enemy> currentWave;
	private HashMap<String, Integer> towerToId;
	private double startTime;
	private double intervalBetweenWaves;
	

	private boolean animationOn = false;

	// Might need to be refactored into a different class
	private HashMap<Integer, ImageView> weaponsOnScreen;
	private HashMap<Integer, ImageView> enemiesOnScreen;
	private HashMap<String, Image> imageBank;

	public GamePlayerController(String xmlFilePath) {
		this.loader = new GamePlayerFactory(new XMLParser(/* "player.samplexml/"+ */xmlFilePath));// hardcoded
		checkIfValid();
		this.currentWave = new LinkedList<>();
		this.enemiesOnScreen = new HashMap<Integer, ImageView>();
		this.weaponsOnScreen = new HashMap<Integer, ImageView>();
		this.model = new GamePlayModel(this.loader, enemiesOnScreen);
		this.enemyController = new EnemyController(this.model.getEnemyManager(), null);
		this.weaponController = new WeaponController(this.model.getWeaponManager());
		this.collisionController = new CollisionController(this.model.getCollisionManager());
		this.model.getData().addObserver(this);
		this.enemyController.getEnemyModel().addObserver(this);
		this.oldLevel = 0;
		this.towerToId = new HashMap<String, Integer>();
		this.weaponsOnScreen = new HashMap<Integer, ImageView>();
		this.animation = new Timeline();
		this.graphics = new GraphicsLibrary();
		this.enemyManager = this.enemyController.getEnemyModel();
		this.loopController = new GameLoopController(this.model,this.enemyController.getEnemyModel(),this.enemyController,this.view); 
		this.imageBank = new HashMap<String, Image>();
		createImageBank();
		this.gameSavingController = new GameSavingController(this.model, xmlFilePath);
		init(false);
	}

	public GamePlayerController(String xmlFilePath, SavedSettings settings) {
		this(xmlFilePath);
		this.oldLevel = settings.getLevel();
		System.out.println("What is the level? :" + settings.getLevel());
		this.oldLevel = settings.getLevel()-1; //does this do anything?
		this.model.getData().setLevel(settings.getLevel());
		this.model.getData().setGold(settings.getGold());
		this.model.getData().setLife(settings.getLives());
		this.model.getData().setScore(settings.getScore());
		this.model.initializeLevelInfo();
	}
	
	
	private void populateTowerToId() {
		HashMap<Integer, engine.tower.Tower> mapping = this.model.getTowerManager().getAvailableTower();
		for (int key : mapping.keySet()) {
			this.towerToId.put(mapping.get(key).getImagePath(), key);
		}
	}

	public HashMap<String, Integer> getTowerImageMap() {
		return this.towerToId;
	}

	/**
	 * Checks to see if XML is valid If not, it will not create a game and it
	 * will throw an error
	 */
	public void checkIfValid() {
		if (!loader.xmlIsValid()) {
			//// ////System.out.println("XML is invalid, game cannot be
			//// created");
			// TODO: actually throw an error
		}
	}

	public void init(boolean newLevel) {
		// HashMap<String, Double> settings = this.loader.getGameSetting();
		// this.enemyManager.setCurrentCell(this.model.getData().getGrid().getStartPoint());
		createImageBank();
		populateTowerToId();
		initGUI(newLevel);
		try {
			Wrapper.getInstance().recordGameScores("" + this.model.getData().getGold(),
					"" + this.model.getData().getLife(), "" + this.model.getData().getCurrentLevel());
		} catch (IOException e) {
		}
		this.towerController = new TowerController(this.model.getTowerManager(), this.view);
		// initSaveGameButton();
		//this.view.getStatsDisplay().getScorePane().getChildren().clear();

	}

	/**
	 * FILL THIS METHOD WITH XML SAVING CRAP
	 */
	private void initSaveGameButton() {
		this.view.saveButton(e -> {
			this.gameSavingController.saveGame();
			// TODO: end game?
		});
	}

	public void setDataStoreOnClose(Stage s) {
		s.setOnCloseRequest(e -> {
			try {
				Wrapper.getInstance().logEndScore("" + this.model.getData().getGold(),
						"" + this.model.getData().getLife(), "" + this.model.getData().getCurrentLevel());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	private void initGUI(boolean newlevel) {
		// this.view = null;
		int rows = model.getData().getRow();
		int cols = model.getData().getColumns();
		if (!newlevel) {
			this.view = new GameGUI(rows, cols); // just for testing, should be
		}
		initSaveGameButton();
		this.view.bindAnimationStart(e -> {
			this.loopController.startAnimation();
		});
		this.view.bindAnimationStop(e -> {
			this.animation.pause();
			this.animationOn = false;
		});
		this.dropController = new DragDropController(this.view, this.model, this.getTowerImageMap());
		System.out.println("The level in initGUI: "+this.model.getData().getCurrentLevel());

		if (newlevel) {
			this.mainScene = view.init(this.model.getData().getGold(), this.model.getData().getLife(),
					this.model.getData().getCurrentLevel(), this.model.getData().getScore(), getTowerImages(),
					this.mainScene);
		} else {
			this.mainScene = view.init(this.model.getData().getGold(), this.model.getData().getLife(),
					this.model.getData().getCurrentLevel(), this.model.getData().getScore(), getTowerImages());
		}
		this.view.getGrid().getGrid().setOnMouseClicked(e -> handleMouseClicked(e.getX(), e.getY()));
		// //System.out.println("line 172, gameplay controller: Is the grid
		// null?");
		// //System.out.println(model.getData().getGrid());
		if (!this.model.getData().getGrid().isNoPathType()) {
			PathManager pathManager = this.loader.getPathManager();
			this.view.getGrid().populatePath(model.getData().getGrid().getAllPaths(), pathManager);
		}
		
	}

	private void handleMouseClicked(double x, double y) {
		Map<Integer, Tower> towersOnGrid = this.model.getTowerOnGrid();
		for (int i : towersOnGrid.keySet()) {
			Tower t = towersOnGrid.get(i);
			double newX = this.view.gridToPixelCoordWidth(t.getX());
			double newY = this.view.gridToPixelCoordHeight(t.getY());
			if ((newX <= x && newX + ENTITY_SIZE >= x && newY <= y && newY + ENTITY_SIZE >= y)) {
				t.toggleInfoVisibility();
				addButtons(t);
			}
		}
		HashMap<Integer, Enemy> enemiesOnGrid = this.enemyManager.getEnemyOnGrid();
		for (int i : enemiesOnGrid.keySet()) {
			Enemy e = enemiesOnGrid.get(i);
			if (e.getX() <= x && e.getX() + ENTITY_SIZE >= x && e.getY() <= y && e.getY() + ENTITY_SIZE >= y) {
				e.toggleInfoVisibility();
			}
		}
	}

	public void addButtons(Tower t) {
		t.getSellButton().setOnAction(e -> this.towerController.handleSellTowerClick(t));
		if(t.getUpgradeCount() == 0){
			t.getUpgradeButton().setOnAction(e -> this.towerController.upgrade(t.getUniqueID()));
		}
		else
			t.getUpgradeButton().setDisable(true);
	}

	// probably should move to frontend
	private ArrayList<String> getTowerImages() {
		ArrayList<String> towerImages = new ArrayList<String>();
		Map<Integer, engine.tower.Tower> towers = this.loader.getTowers();
		for (engine.tower.Tower tower : towers.values()) {
			towerImages.add(tower.getImagePath());
		}
		return towerImages;
	}

	public Scene getMainScene() {
		return this.mainScene;
	}

	public GameGUI getView() {
		return view;
	}

	private void gameOver() {
		endCondition("http://people.duke.edu/~lz107/voogaTemplates/gameover.html");
	}

	private void winGame() {
		// System.out.println("WIN GAME CONDITION");
		this.animation.pause();
		endCondition("http://people.duke.edu/~lz107/voogaTemplates/win.html");
	}

	private boolean okForNewLevel() {

		return (enemyManager.getEnemyOnGrid().size() == 0&&this.intervalBetweenWaves<0
				&& currentWave.size() == 0);
	}
	
	private boolean noMoreEnemies(){
		return (enemyManager.getEnemyOnGrid().size() == 0 && currentWave.size() == 0);
	}

	private void checkCreateNewLevel() {
		// new level condition
		if (okForNewLevel()) {
			System.out.println("OK FOR NEW LEVEL");
			enemyManager.getData().setLevel(enemyManager.getData().getCurrentLevel() + 1);
			this.animation.pause();
			System.out.println("GAMELEVEL: "+this.enemyManager.getData().getCurrentLevel());

			this.view.newLevelPopUp(e -> {
				winLoseCondition();
				this.model.initializeLevelInfo();
				this.intervalBetweenWaves = this.model.getEnemyManager().getTimeOfNextWave();

				// this.view.getGrid().getGrid().getChildren().clear();

				this.view.getMainScreen().getChildren().clear();
				init(true);
				this.animation.play();
				this.startTime = System.currentTimeMillis();
			});
		}
	}

	private void endCondition(String url) {
		try {
			Wrapper.getInstance().logEndScore("" + this.model.getData().getGold(), "" + this.model.getData().getLife(),
					"" + this.model.getData().getCurrentLevel());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// System.out.println("Log end score went wrong");
			e.printStackTrace();
		}
		// System.out.println("Trying to set the winning screen");
		this.view.getMainScreen().getChildren().clear();
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load(url);
		this.view.getMainScreen().setCenter(browser);
	}

	private boolean loseCondition() {
		return (this.model.getData().getLife() <= 0);
	}

	private boolean winCondition() {
		System.out.println("this.model.getData().getCurrentLevel() >= this.model.getData().getLevelNumber()"+
	this.model.getData().getCurrentLevel() +">="+ this.model.getData().getLevelNumber());
		return (this.model.getData().won() || (this.model.getData().getLife() > 0
				&& noMoreEnemies() && this.model.getData().getCurrentLevel() >= this.model.getData().getLevelNumber()));
	}

	private void winLoseCondition() {
		if (loseCondition()) {
			System.out.println("GAME OVER");
			this.animation.pause();
			gameOver();
		} else if (winCondition()) {
			// System.out.println("WIn game!");
			winGame();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GamePlayData) {
			// update level in display
			System.out.println("The level in update observable: "+this.model.getData().getCurrentLevel());
			this.view.updateStatsDisplay(((GamePlayData) o).getGold(), ((GamePlayData) o).getLife(),
				this.model.getData().getCurrentLevel(), ((GamePlayData) o).getScore());
			this.view.updateCurrentLevelStats(((GamePlayData) o).getCurrentLevel());
		

		}
	}

	private boolean waveTimeIntervalElapsed() {
		return (System.currentTimeMillis() - this.startTime > intervalBetweenWaves && intervalBetweenWaves >= 0);
	}

	/*
	 */

	private void redrawEverything() {
		this.view.getGrid().getGrid().getChildren().addAll(this.view.getGrid().getPathImages());
		HashMap<Integer, Enemy> enemyRedraw = this.enemyManager.getEnemyOnGrid();
		Map<Integer, Tower> towerRedraw = this.model.getTowerOnGrid();
		HashMap<Integer, Weapon> bulletRedraw = this.model.getWeaponManager().getWeaponOnGrid();
		updateBulletOnScreen(bulletRedraw);
		updateEnemiesOnScreen(enemyRedraw);
		this.view.reRenderEnemy(enemiesOnScreen);
		this.view.reRenderWeapon(weaponsOnScreen);
		this.view.reRenderTower(towerRedraw);
	}

	public Timeline getTimeline() {
		return this.animation;
	}

	private void removeValFromMap(Map<Integer, ?> map, Iterator<Integer> it) {
		while (it.hasNext()) {
			int value = it.next();
			if (!map.containsKey(value)) {
				it.remove();
			}
		}
	}

	private void updateBulletOnScreen(HashMap<Integer, Weapon> bulletRedraw) {

		Iterator<Integer> it = weaponsOnScreen.keySet().iterator();
		removeValFromMap(bulletRedraw, it);
		for (int i : bulletRedraw.keySet()) {
			if (!weaponsOnScreen.containsKey(bulletRedraw.get(i).getUniqueID())) {
				Image ii = imageBank.get("Weapon " + bulletRedraw.get(i).getWeaponTypeID());
				ImageView image = new ImageView(ii);
				graphics.setImageViewParams(image, DragDropView.DEFENSIVEWIDTH * 0.5,
						DragDropView.DEFENSIVEHEIGHT * 0.5);
				image.setCache(true);
				image.setCacheHint(CacheHint.SPEED);
				image.setX(bulletRedraw.get(i).getX());
				image.setY(bulletRedraw.get(i).getY());
				weaponsOnScreen.put(bulletRedraw.get(i).getUniqueID(), image);
				// this.view.getGrid().getGrid().getChildren().add(weaponsOnScreen.get(i));
			} else {
				weaponsOnScreen.get(bulletRedraw.get(i).getUniqueID()).setX(bulletRedraw.get(i).getX());
				weaponsOnScreen.get(bulletRedraw.get(i).getUniqueID()).setY(bulletRedraw.get(i).getY());
			}
		}
	}

	private void updateEnemiesOnScreen(HashMap<Integer, Enemy> enemyRedraw) {
		// might fix later
		Iterator<Integer> it = enemiesOnScreen.keySet().iterator();
		removeValFromMap(enemyRedraw, it);
		for (int i : enemyRedraw.keySet()) {
			if (!enemiesOnScreen.containsKey(enemyRedraw.get(i).getUniqueID())) {
				ImageView image = new ImageView(graphics.createImage(enemyRedraw.get(i).getImage()));
				graphics.setImageViewParams(image, DragDropView.DEFENSIVEWIDTH * 0.9,
						DragDropView.DEFENSIVEHEIGHT * 0.9);
				image.setCache(true);
				image.setCacheHint(CacheHint.SPEED);
				image.setX(enemyRedraw.get(i).getX());
				image.setY(enemyRedraw.get(i).getY());
				enemiesOnScreen.put(enemyRedraw.get(i).getUniqueID(), image);
			} else {
				enemiesOnScreen.get(enemyRedraw.get(i).getUniqueID()).setX(enemyRedraw.get(i).getX());
				enemiesOnScreen.get(enemyRedraw.get(i).getUniqueID()).setY(enemyRedraw.get(i).getY());
			}
		}
	}

	public HashMap<String, Image> createImageBank() {
		Map<Integer, engine.tower.Tower> towers = this.loader.getTowers();
		for (int i : towers.keySet()) {
			Image image = graphics.createImage(towers.get(i).getImagePath());
			imageBank.put("Tower " + i, image);
		}
		Map<Integer, engine.weapon.Weapon> weapons = this.loader.getWeaponBank();
		for (int i : weapons.keySet()) {
			Image image = graphics.createImage(weapons.get(i).getImagePath());
			imageBank.put("Weapon " + i, image);
		}
		return imageBank;
	}
}