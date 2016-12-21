package usecases;

import gameplayer.model.enemy.Enemy;

/*
public class UpdateEnemyPosition {
	private void moveSingleEnemy(Enemy e) throws NullPointerException{
		//to make it easier, only updating enemy's current cell once it reaches the center point of the next cell
		double moveDist = e.getMovingSpeed();
		double distToMove = (Math.abs(cellToCoordinate(e.getCurrentCell().getNext().getX()) - e.getX()) + 
				Math.abs(cellToCoordinate(e.getCurrentCell().getNext().getY()) - e.getY()));
		while (moveDist > 0) {
			if (moveDist >= distToMove) { //can move to center of next cell
				e.setX(e.getX() + e.getxDirection() * distToMove);
				e.setY(e.getY() + e.getyDirection() * distToMove);
				e.setCurrentCell(e.getCurrentCell().getNext());
				e.setxDirection(e.getCurrentCell().getNext().getX() - e.getCurrentCell().getX()); //-1, 0, or 1
				e.setyDirection(e.getCurrentCell().getNext().getY() - e.getCurrentCell().getY());
				moveDist -= distToMove;
			}
			else {
				e.setX(e.getX() + e.getxDirection() * moveDist);
				e.setY(e.getY() + e.getyDirection() * moveDist);
			}
			
		}
	}
	
}

*/	

