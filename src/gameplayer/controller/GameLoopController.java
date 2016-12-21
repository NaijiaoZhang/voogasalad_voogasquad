// This entire file is part of my masterpiece.
// Naijiao Zhang
package gameplayer.controller;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import gameplayer.model.GamePlayData;
import gameplayer.model.GamePlayModel;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import gameplayer.view.GameGUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/*
 * This controller is a part of my masterpiece because it plays a pivotal part of the game player as well 
 * as demonstrates good code design. Originally, GamePlayerController was a class that did handling
 * of a lot of responsibilities. By factoring out the Game Loop portion of GamePlayerController and
 * putting it into its own class, the code for GamePlayerController is more readable and the structure
 * of the overall project is better. The code in this class demonstrates a couple of things. First,
 * a binding to this class's method 'startAnimation' is done in GamePlayerController. The start button
 * of the engine is passed a lambda that calls startAnimation. A Game Loop is started once the button 
 * is pressed and the backend logic is updated. By handling it this way, encapsulation is kept and the 
 * design is better. Since this class handles all Game Loop and communication with backend related
 * things, the Observer pattern is used here to update the view's statistics once something related
 * with view stats is updated in the backend. The naming of the variables are also very intuitive
 * and there is no doubt as to what each method does. For example, spawnEnemyOnInterval will spawn
 * enemies in the waves at the proper interval specified in the authoring environment. Everything is
 * encapsulated and are on a need to know basis. The only public method is one that is necessary
 * for GamePlayerController to call. 
 */

public class GameLoopController implements Observer{

	public static final int MILLISECOND_DELAY = 50;

	private GamePlayModel voogaBackEnd;
	private GameGUI view; 
	private EnemyController enemyController; 
	private EnemyManager waveManager; 
	private Queue<Enemy> currentWave; 
	private Timeline animation; 
	private double startTime, intervalBetweenWaves; 
	
	public GameLoopController(GamePlayModel voogaBackEnd, EnemyManager waveManager,
			EnemyController enemyController, GameGUI view){
		
		this.startTime = -1;
		this.currentWave = new LinkedList<>(); 
		this.voogaBackEnd = voogaBackEnd;
		this.waveManager = waveManager; 
		this.enemyController = enemyController;
		this.view = view; 
		this.animation = new Timeline(); 
	}
	
	public void startAnimation() {
		this.voogaBackEnd.getData().getGrid().printGrid();
		this.startTime = System.currentTimeMillis();
		this.intervalBetweenWaves = this.voogaBackEnd.getEnemyManager().getTimeOfNextWave();
		
		spawnEnemyOnInterval(this.waveManager,this.enemyController);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
			((Pane) this.view.getGrid().getGrid()).getChildren().clear();
			if (waveTimeIntervalElapsed()) {
				this.currentWave = this.voogaBackEnd.getEnemyManager().getPackOfEnemyComing();
				this.intervalBetweenWaves = this.voogaBackEnd.getEnemyManager().getTimeOfNextWave();
			}
			this.waveManager.update();
			this.voogaBackEnd.getCollisionManager().handleCollisions();
		});
		
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

	}
	
	private void spawnEnemyOnInterval(EnemyManager enemyManager, EnemyController control) {
		Thread enemyThread = new Thread() {
			public void run() {
				long intervalBetween = (long) control.getEnemyModel().getFrequencyOfNextWave();
				while (intervalBetween >= 0) {
					if (currentWave.size() > 0) {
						Enemy enemy = currentWave.poll();
						enemyManager.spawnEnemy(enemy);
					}
					try {
						Thread.sleep(intervalBetween);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		enemyThread.start();
	}
	
	private boolean waveTimeIntervalElapsed() {
		return (System.currentTimeMillis() - this.startTime > intervalBetweenWaves && intervalBetweenWaves >= 0);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GamePlayData) {
			this.view.updateStatsDisplay(((GamePlayData) o).getGold(), ((GamePlayData) o).getLife(),
			this.voogaBackEnd.getData().getCurrentLevel(), ((GamePlayData) o).getScore());
			this.view.updateCurrentLevelStats(((GamePlayData) o).getCurrentLevel());
		}
	}
}
