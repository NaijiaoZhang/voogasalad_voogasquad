package gameplayer.model.collision;

import java.util.HashMap;
import java.util.Iterator;

import gameplayer.model.GamePlayData;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import gameplayer.model.weapon.Weapon;
import gameplayer.model.weapon.WeaponManager;
import javafx.scene.image.ImageView;
import statswrapper.Wrapper;

public class CollisionManager {
	EnemyManager enemyManager;
	WeaponManager weaponManager;
	HashMap<Integer,ImageView>enemiesOnScreen;
	GamePlayData gameData;
	
	public CollisionManager(GamePlayData gameData, WeaponManager wManager, EnemyManager eManager,HashMap<Integer,ImageView>enemiesOnScreen, GamePlayData data) {
		enemyManager = eManager;
		weaponManager = wManager;
		this.enemiesOnScreen=enemiesOnScreen;
		this.gameData = data;
	}
	
	public void handleCollisions() {
		Iterator<Weapon> iter = weaponManager.getWeaponOnGrid().values().iterator();
		while(iter.hasNext()) {
			Weapon weapon = iter.next();
			int targetUniqueID = weapon.getTargetEnemyID();
			boolean enemyOnGrid = enemyManager.getEnemyOnGrid().keySet().contains(targetUniqueID);
			boolean xInRange;
			boolean yInRange;
			if (!enemyOnGrid) {
				xInRange = false;
				yInRange = false;
			}
			else {
				Enemy targetEnemy = enemyManager.getEnemyOnGrid().get(targetUniqueID);
				int hitBox = 75;
				xInRange = Math.abs(weapon.getX() - targetEnemy.getX()) <= hitBox; //make more robust
				//System.out.println("weapon x " + weapon.getX());
				//System.out.println("enemy x "+ targetEnemy.getX());
				//System.out.println("weapon y " + weapon.getY());
				//System.out.println("enemy y "+ targetEnemy.getY());
				yInRange = Math.abs(weapon.getY() - targetEnemy.getY()) <= hitBox; //make more robust
				
				if (xInRange && yInRange) {
					Collision collision = new Collision(weapon, targetEnemy, this.gameData);
					collision.processCollision();
					iter.remove(); 
					if (targetEnemy.getHealth() <= 0) {
						
						enemiesOnScreen.remove(targetUniqueID);
						enemyManager.getEnemyOnGrid().remove(targetUniqueID);
					}

				}
			}
			
			
		}
	}
	
}
