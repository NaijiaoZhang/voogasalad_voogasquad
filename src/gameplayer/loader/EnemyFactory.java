package gameplayer.loader;

import engine.enemy.Enemy;
import gameplayer.model.Cell;

public class EnemyFactory {

	int currID;
	
	public EnemyFactory() {
		currID = 0;
	}
	
	public gameplayer.model.enemy.Enemy createModelEnemy(Enemy enemyType, Cell start, int pathID) { //refactor name
		int ID = currID;
		String name = enemyType.getName();
		double movingSpeed = enemyType.getSpeed();
		int health = (int) enemyType.getHealth();
		String image = enemyType.getImagePath();
		double height = enemyType.getSize();
		double width = enemyType.getSize();
		double goldReward = enemyType.getMoney();
		double scoreReward = enemyType.getScore();
		gameplayer.model.enemy.Enemy modelEnemy = new gameplayer.model.enemy.Enemy(ID, name, 
				movingSpeed, health, image, height, width, pathID, goldReward, scoreReward);
		modelEnemy.setCurrentCell(start);
		currID++;
		return modelEnemy;
	}
}
