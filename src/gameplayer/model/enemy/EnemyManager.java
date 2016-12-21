package gameplayer.model.enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import engine.level.wave.Wave;
import gameplayer.loader.EnemyFactory;
import gameplayer.loader.GamePlayerFactory;
import gameplayer.model.Cell;
import gameplayer.model.GamePlayData;
import gameplayer.model.Grid;
import gameplayer.model.Path;
import gameplayer.model.weapon.Weapon;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.image.ImageView;

public class EnemyManager extends Observable{

	private Map<Integer, Enemy> enemyOnGrid; 
	private GamePlayData gameData;
	private GamePlayerFactory gameFactory;
	private Grid grid;
	//private Cell current;
	//private Cell currentCopy;
	//private Cell startCell;
	//private GraphicsLibrary graphicLib;
	private Queue<Wave> allWaves;
	private Queue<Double> allWaveStartTimes;
	private Queue<Double> allWaveFrequencies;
	private Boolean noMoreWave;
	
	
	HashMap<Integer,ImageView>enemiesOnScreen;


	public EnemyManager(GamePlayData gameData,HashMap<Integer,ImageView>enemiesOnScreen) {		
		this.gameData = gameData;
		this.gameFactory = gameData.getFactory();
		//this.graphicLib = new GraphicsLibrary();
		this.allWaveFrequencies = new LinkedList<Double>();
		this.allWaveStartTimes = new LinkedList<Double>();
		this.enemiesOnScreen =enemiesOnScreen;
		//initializeNewLevel();
	}

	public void initializeNewLevel(){
		this.grid = this.gameData.getGrid();
		//this.startCell = this.grid.getStartPoint();
		this.enemyOnGrid = new HashMap<Integer, Enemy>();
		this.allWaves = this.gameFactory.getWaves(this.gameData.getCurrentLevel());
		initializeWaves();
		this.noMoreWave = false;
		System.out.println("reaching new level: " + this.gameData.getCurrentLevel() + " wave size " + this.allWaves.size());
		//System.out.println("Start: " + grid.getStart().getX() + " " + grid.getStart().getY());
		//System.out.println("end: " + grid.getEnd().getX() + " " + grid.getEnd().getY());

	}
	
	public GamePlayData getData(){
		return this.gameData;
	}
	
	private void initializeWaves() {

		//System.out.println("Does all WaveStartTimes exist?");
		//System.out.println(allWaveStartTimes);
		allWaves.forEach(w -> allWaveStartTimes.add(w.getStartTime()));
		allWaves.forEach(w -> allWaveFrequencies.add(w.getFrequency()));
	}
	
	public Boolean getNoMoreWave(){
		return this.noMoreWave;
	}

	public void setNoMoreWave(){
		this.noMoreWave = true;
		setChanged();
		notifyObservers();
	}
	/*
	public void setCurrentCell(Cell cell) {
		this.current = cell;
		//this.currentCopy = cell;
	}
	*/


	public HashMap<Integer, Enemy> getEnemyOnGrid() {
		return (HashMap<Integer, Enemy>) this.enemyOnGrid; //refactor
	}
	
	public List<Enemy> getEnemyListOnGrid() {
		List<Enemy> enemies = new ArrayList<Enemy>();
		enemies.addAll(enemyOnGrid.values());
		return enemies;
	}

	public void spawnEnemy(Enemy enemy) {
		/*
		System.out.println("rararara " + enemy.getCurrentCell().getX());
		Path testPath = this.grid.getPath(0);
		//System.out.println("testting :))) "+this.grid.getPath(enemy.getPathID()).getNext(enemy.getCurrentCell()).getY());
		enemy.setxDirection(this.grid.getPath(enemy.getPathID()).getNext(enemy.getCurrentCell()).getX() 
				- enemy.getCurrentCell().getX());
		enemy.setyDirection(this.grid.getPath(enemy.getPathID()).getNext(enemy.getCurrentCell()).getY()
				- enemy.getCurrentCell().getY());
		*/
		Cell nextCell = this.grid.getNext(enemy.getPathID(), enemy.getCurrentCell());
		enemy.setxDirection(nextCell.getX() - enemy.getCurrentCell().getX());
		enemy.setyDirection(nextCell.getY() - enemy.getCurrentCell().getY());
		enemy.setX(gameData.cellToCoordinate(enemy.getCurrentCell().getX()));
		enemy.setY(gameData.cellToCoordinate(enemy.getCurrentCell().getY()));
		//System.out.println("enemy spawning: "+ enemy.getUniqueID());
		enemyOnGrid.put(enemy.getUniqueID(), enemy);
	}

	/*
	 * returns true if enemy should be removed from grid
	 */
	private void moveSingleEnemy(Enemy enemy) {
		// to make it easier, only updating enemy's current cell once it reaches
		// the center point of the next cell
		double distToMove;
		boolean onLastCell = false;

		double moveDist = enemy.getMovingSpeed();
		if (enemy.getMovingSpeed() == 0.0) System.out.println("moving speed should stop");
		

		while (moveDist > 0) {
			try {
				Cell nextCell = this.grid.getNext(enemy.getPathID(), enemy.getCurrentCell());
				double deltaX = Math.abs(gameData.cellToCoordinate(nextCell.getX()) - enemy.getX());
				double deltaY = Math.abs(gameData.cellToCoordinate(nextCell.getY()) - enemy.getY());
				distToMove = deltaX + deltaY;
			} catch (NullPointerException exception) { // enemy is currently at
				// last cell on path
				double destinationXpos = enemy.getCurrentCell().getX()
						+ enemy.getxDirection() * gameData.getCellWidth() / 2; // midpoint
				// +
				// width/2
				// =
				// edge
				double destinationYpos = enemy.getCurrentCell().getY()
						+ enemy.getyDirection() * gameData.getCellWidth() / 2;
				distToMove = Math.abs(destinationXpos - enemy.getX()) + Math.abs(destinationYpos - enemy.getY());
				onLastCell = true;
			}
			
			
			if (moveDist >= distToMove) { // can move to center of next cell
				enemy.setX(enemy.getX() + enemy.getxDirection() * distToMove);
				enemy.setY(enemy.getY() + enemy.getyDirection() * distToMove);
				Cell nextCell = this.grid.getNext(enemy.getPathID(), enemy.getCurrentCell());
				enemy.setCurrentCell(nextCell);
				if (enemy.getCurrentCell().equals(this.grid.getEnd(enemy.getPathID()))) { //THIS IS THE ERRORR
					if (gameData.getLife() >= 0) {
						gameData.setLife(gameData.getLife() - 1);
					}
					
					//System.out.println("ENEMY REMOVING");
					enemy.setRemove(true);
					return;
				}
				Cell nCell = this.grid.getNext(enemy.getPathID(), enemy.getCurrentCell());
				enemy.setxDirection(nCell.getX() - enemy.getCurrentCell().getX());
				enemy.setyDirection(nCell.getY() - enemy.getCurrentCell().getY());
				moveDist -= distToMove;
			} else {
				enemy.setX(enemy.getX() + enemy.getxDirection() * moveDist);
				enemy.setY(enemy.getY() + enemy.getyDirection() * moveDist);
				moveDist -= moveDist;
			}
		}
		return;
	}
	



	/*
	private void checkCollision(Enemy e) {
		GamePlayerModel gameModel = game
		for (Weapon w : this.gamePlayModel.getWeaponOnGrid()) {
			gamePlayModel.singleCollision(e, w);
		}
		if (e.getHealth() < 0)
			getEnemyList().remove(e);
		setChanged();
		notifyObservers();
	}
	*/
	 




	public void update() {
		moveEnemies();
	}

	
	private void moveEnemies() {
		Iterator<Enemy> iter = enemyOnGrid.values().iterator();
		while (iter.hasNext()) {
			Enemy enemy = iter.next();
			moveSingleEnemy(enemy);
			if (enemy.mustRemove()) {
				iter.remove();
			}
		}
	}


	public double getTimeOfNextWave() {
		if (allWaveStartTimes.isEmpty()) return -1;
		double timeInSeconds = this.allWaveStartTimes.poll();
		double timeInMillis = timeInSeconds * 1000;
		return timeInMillis;
	}
	
	public double getFrequencyOfNextWave() {
		if (allWaveFrequencies.isEmpty()) return 0;
		double timeInSeconds = this.allWaveFrequencies.poll();
		//double timeInMillis = 10000;//10 seconds
		double timeInMillis = timeInSeconds * 1000;
		return timeInMillis;
	}
	
	public Queue<Enemy> getPackOfEnemyComing() {
		if (allWaves.isEmpty()) {
			this.setNoMoreWave();
			//System.out.println("ALL WAVES IS EMPTY");
			return new LinkedList<Enemy>();
		}
		Wave wave = this.allWaves.poll();
		return this.gameFactory.getIndividualWaveQueue(wave, this.gameData.getCurrentLevel());
	}


}
