package gameplayer.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameplayer.model.enemy.Enemy;
import gameplayer.model.IDrawable;
import gameplayer.model.tower.Tower;
import gameplayer.view.buttonPanel.ButtonPanel;
import gameplayer.view.buttonPanel.GamePlayButtonPanel;
import gameplayer.view.helper.GraphicsLibrary;
import gameplayer.view.helper.dragdrop.DragDrop;
import gameplayer.view.helper.dragdrop.DragDropView;
import gameplayer.view.statsdisplay.StatsDisplay;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Overseer of game GUI
 * 
 * @author lucyzhang
 *
 */
public class GameGUI {

	public static final int SCENE_WIDTH = 1200;
	public static final int SCENE_HEIGHT = 700;
	public static final double xError = -20;
	public static final double yError = -60;

	private BorderPane mainScreen;
	private Scene scene;
	private VBox leftPane;
	private Button sellButton;
	private GraphicsLibrary graphics;
	private GridGUI grid;
	private DragDropView dragDrop;
	private GamePlayButtonPanel buttonPanel;
	private StatsDisplay statsDisplay;
	private double numLevels;
	private double currentLevel;
	private List path;
	private EventHandler animationBind;
	private int rows;
	private int columns;
	
	private WebView newLevelBrowser;
	
	private HashMap<Integer,ImageView>towersOnScreen;

	public GameGUI(int rows, int columns) {
		this.mainScreen = new BorderPane();
		this.graphics = new GraphicsLibrary();
		this.grid = new GridGUI(rows, columns/* , path */);
		this.dragDrop = new DragDropView(xError, yError, this);
		this.buttonPanel = new GamePlayButtonPanel();
		this.currentLevel = 0;
		this.rows = rows;
		this.columns = columns;
	}

	public Scene init(double gold, double lives, double level, double score, List<String> imagePaths) {
		this.numLevels = level;
		createScene();
		createGrid();
		initDragDropPane(imagePaths);
		initChat();
		initStatsTab();
		addButtonPanel();
		initStatsDisplay(gold, lives, currentLevel, score);
		return this.scene;
	}

	public Scene init(double gold, double lives, double level, double score, List<String> imagePaths, Scene scene) {
		//System.out.println("GameGUI stats: "+lives+","+level+","+score+","+imagePaths);
		this.numLevels = level;
		// createScene();
		this.scene = scene;
		clearScreen();
		createGrid();
		initDragDropPane(imagePaths);
		initChat();
		initStatsTab();
		addButtonPanel();
		initStatsDisplay(gold, lives, currentLevel, score);
		//createNewLevelBrowser();
		return this.scene;
	}
	
	private void clearScreen(){
		mainScreen.setRight(null);
		mainScreen.setTop(null);
		this.buttonPanel.getPane().getChildren().clear();
		this.dragDrop.getDragDropPane().getTabs().clear();
		this.grid.getGrid().getChildren().clear();
	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}

	public double gridToPixelCoordWidth(double num) {
		return num * GridGUI.GRID_WIDTH / this.columns;
	}

	public double gridToPixelCoordHeight(double num) {
		return num * GridGUI.GRID_HEIGHT / this.rows;
	}

	public int pixelToGridCoord(double pixel) {
		return (int) pixel / (GridGUI.GRID_HEIGHT / this.rows);
	}

	public BorderPane getMainScreen() {
		return mainScreen;
	}
	
	private void createNewLevelBrowser(){
		this.newLevelBrowser = new WebView();
		WebEngine webEngine = this.newLevelBrowser.getEngine();
		webEngine.load("http://people.duke.edu/~lz107/voogaTemplates/newlevel.html");
	}

	public void setMainScreen(BorderPane mainScreen) {
		this.mainScreen = mainScreen;
	}

	/**
	 * MIGHT NOT BE NECESSARY Update the current level in the stats display only
	 * 
	 * @param newLevel
	 */
	public void updateCurrentLevelStats(double newLevel) {
		this.currentLevel = newLevel;
		this.statsDisplay.updateLevel(newLevel);
	}

	public void setPath(List<int[]> path) {
		this.path = path;
	}

	public GridGUI getGrid() {
		return this.grid;
	}

	public DragDrop getDragDrop() {
		return this.dragDrop.getDragDrop();
	}

	public void bindAnimationStart(EventHandler<ActionEvent> handle) {
		this.buttonPanel.bindAnimationStart(handle);
	}

	public void bindAnimationStop(EventHandler<ActionEvent> handle) {
		this.buttonPanel.bindAnimationStop(handle);
	}

	public List<int[]> getDroppedTowerCoords() {
		return getDragDrop().getCoordinates();
	}
	
	public void saveButton(EventHandler<ActionEvent> handle){
		this.buttonPanel.bindSaveGame(handle);
	}

	private void addButtonPanel() {
		this.buttonPanel.init();
		mainScreen.setTop(this.buttonPanel.getPane());
	}

	private void createScene() {
		this.scene = new Scene(mainScreen, SCENE_WIDTH, SCENE_HEIGHT);
		this.scene.getStylesheets()
				.add(this.getClass().getResource("/gameplayer/view/voogaStyle.css").toExternalForm());
	}

	/**
	 * Call this method when new level needs to be triggered
	 * 
	 * @param e
	 */
	public void newLevelPopUp(EventHandler<ActionEvent> e) {
		this.grid.getGrid().getChildren().clear();
		
		Button btn = graphics.createButton("Next level", e);
		btn.getStyleClass().add("ipad-dark-grey");
		ImageView stuff = graphics.createImageView(graphics.createImage("newlevel.png"));
		graphics.setImageViewParams(stuff, GridGUI.GRID_WIDTH, GridGUI.GRID_HEIGHT);
		//this.grid.getGrid().getChildren().add(this.newLevelBrowser);
		this.grid.getGrid().getChildren().add(btn);
	}

	private void createGrid() {
		styleGrid();
		// this.mainScreen.setLeft(grid.getPathGrid());
		this.mainScreen.setLeft(grid.getGrid());
		grid.init();
	}

	private void styleGrid() {
		BorderPane.setAlignment(this.grid.getGrid(), Pos.CENTER);
		// BorderPane.setMargin(this.grid.getGrid(), new Insets(10,50,10,0));
	}

	private void initDragDropPane(List<String> imagePaths) {
		dragDrop.setDragTarget(grid.getGrid());
		mainScreen.setRight(dragDrop.getDragDropPane());
		Tab tab = dragDrop.createTab("Towers");
		dragDrop.populateImageViewsToTab(tab, imagePaths);
	}

	private void initChat() {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("http://voogachat.herokuapp.com");
		Tab tab = dragDrop.createTab("Chat");
		tab.setContent(browser);
	}

	private void initStatsTab() {
//		WebView browser = new WebView();
//		WebEngine webEngine = browser.getEngine();
//		webEngine.load("http://voogasquad.herokuapp.com/home");
//		ScrollPane scroll = new ScrollPane();
//		scroll.setContent(browser);
//		Tab tab = dragDrop.createTab("Your Stats");
//		tab.setContent(scroll);
	}
	
	public StatsDisplay getStatsDisplay(){
		return this.statsDisplay;
	}

	private void initStatsDisplay(double gold, double lives, double level, double score) {
		this.statsDisplay = new StatsDisplay(gold, lives, level, score);
		statsDisplay.init();
		this.mainScreen.setBottom(statsDisplay.getScorePane());
	}

	public void updateStatsDisplay(double gold, double lives, double level, double score) {
		//System.out.println("update stats display: "+gold+","+lives+","+level+);
		this.statsDisplay.updateLevelUI(gold, lives, level, score);
	}

	public void reRenderTower(Map<Integer, Tower> redraw) {// should be interface of
		// drawables
		ArrayList<int[]> towerCoords = (ArrayList<int[]>) this.getDroppedTowerCoords();
		int i = 0;

		for (Tower entity : redraw.values()) {
			// System.out.println("Invalid
			// image?"+entity.getImage().toString());
			ImageView image = new ImageView(graphics.createImage(entity.getImage().toString()));
			if (i < towerCoords.size() && towerCoords.get(i).length > 1) {
				// System.out.println("TOWER BEING RENDERED?!");
				image.setX(towerCoords.get(i)[0]);
				image.setY(towerCoords.get(i)[1]);
				image.setCache(true);
				image.setCacheHint(CacheHint.SPEED);
				graphics.setImageViewParams(image, DragDropView.DEFENSIVEWIDTH, DragDropView.DEFENSIVEHEIGHT);
				this.grid.getGrid().getChildren().add(image);
				if (entity instanceof Tower) {
					// System.out.println("Tower added");
					((Tower) entity).getInfoBox().setLayoutX(image.getX());
					((Tower) entity).getInfoBox().setLayoutY(image.getY() + image.getFitHeight());
					this.grid.getGrid().getChildren().add(((Tower) entity).getInfoBox());
				}
				i++;
			}
		}
	}

	public void reRenderEnemy(HashMap<Integer, ImageView> redraw) {
		for (ImageView image : redraw.values()) {
			//System.out.println("Enemy image: "+image.toString());
			this.grid.getGrid().getChildren().add(image);
		}
	}

	public void reRenderWeapon(HashMap<Integer, ImageView> weaponsOnScreen) {

		for (Integer weapon : weaponsOnScreen.keySet()) {
			this.grid.getGrid().getChildren().add(weaponsOnScreen.get(weapon));

		}
	}



}
