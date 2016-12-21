package gameplayer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Queue;
import engine.tower.Tower;
import engine.weapon.*;
import engine.weapon.WeaponManager;
import gameplayer.loader.GamePlayerFactory;
import gameplayer.model.collision.CollisionManager;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import gameplayer.model.tower.TowerManager;
import gameplayer.model.weapon.Weapon;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.image.ImageView;
import gameplayer.model.weapon.*;

public class GamePlayModel extends Observable {
	private GamePlayerFactory factory;
	private String gameTitle;
	private GamePlayData gameData;

	// private int hitBuffer = 10; // initialize from xml

	private HashMap<Integer, engine.weapon.Weapon> weaponMap;
	//private GraphicsLibrary graphicLib;
	private TowerManager towerManager;
	private gameplayer.model.weapon.WeaponManager weaponManager;
	private EnemyManager enemyManager;
	private CollisionManager collisionManager;
	// private EnemyModel enemyModel;
	
	/**
	 * this constructor is for reloading the game
	 * with the progress user already made in the game
	 * @param gameData
	 */
	public GamePlayModel(GamePlayData gameData, HashMap<Integer,ImageView>enemiesOnScreen){
		this.gameData = gameData;
		//this.gameData.initializeGameSetting(); don't need to do this b/c gameData is already initialized
		this.enemyManager = new EnemyManager(this.gameData,enemiesOnScreen);
		this.towerManager = new TowerManager(gameData, this.enemyManager);
		this.towerManager.initializeTowerForLoading(); //getting towerList from gameData
		this.weaponManager = new gameplayer.model.weapon.WeaponManager(this.gameData, this.towerManager);
		this.collisionManager = new CollisionManager(gameData, this.weaponManager, this.enemyManager,enemiesOnScreen, gameData);
		initializeGameSetting(gameData.getFactory());
	}
	
	
	
	public GamePlayModel(GamePlayerFactory factory,HashMap<Integer,ImageView>enemiesOnScreen) {
		//graphicLib = new GraphicsLibrary();
		this.gameData = new GamePlayData(factory);
		this.gameData.initializeGameSetting();
		this.enemyManager = new EnemyManager(this.gameData,enemiesOnScreen);
		this.towerManager = new TowerManager(gameData, this.enemyManager);
		this.weaponManager = new gameplayer.model.weapon.WeaponManager(this.gameData, this.towerManager);
		this.collisionManager = new CollisionManager(gameData, this.weaponManager, this.enemyManager,enemiesOnScreen, this.gameData);
		initializeGameSetting(factory);
	}

	/**
	 * could be used when start another game
	 * 
	 * @param factory
	 */
	public void initializeGameSetting(GamePlayerFactory factory) {
		this.factory = factory;
		this.gameTitle = this.factory.getGameTitle();
		initializeLevelInfo();
	}

	public GamePlayData getData() {
		return this.gameData;
	}

	public TowerManager getTowerManager() {
		return this.towerManager;
	}

	public EnemyManager getEnemyManager() {
		return this.enemyManager;
	}

	public CollisionManager getCollisionManager() {
		return this.collisionManager;
	}

	public gameplayer.model.weapon.WeaponManager getWeaponManager() {
		return this.weaponManager;
	}

	public HashMap<Integer, engine.tower.Tower> getAvailableTower() {
		return this.towerManager.getAvailableTower();
	}

	public HashMap<Integer, Weapon> getWeaponOnGrid() {
		return this.weaponManager.getWeaponOnGrid();
	}

	public void initializeLevelInfo() {
		this.gameData.initializeLevelInfo();
		this.weaponManager.initializeNewLevel();
		this.towerManager.updateAvailableTower();
		this.enemyManager.initializeNewLevel();
	}

	/*
	 * should just get this in towerController
	 */
	public Map<Integer, gameplayer.model.tower.Tower> getTowerOnGrid() { // fix
																			// naming
		return this.towerManager.getTowerOnGrid();
	}

	public Boolean placeTower(int type, int x, int y) {
		return this.towerManager.placeTower(type, x, y);
	}

	public void updateInLevel(HashMap<Integer,ImageView>weaponsOnScreen) {
		// checkCollision();
		this.weaponManager.updateWeapon(weaponsOnScreen);
		this.towerManager.updateAvailableTower();
		this.enemyManager.update();
		// this.enemyModel.update();

	}

}