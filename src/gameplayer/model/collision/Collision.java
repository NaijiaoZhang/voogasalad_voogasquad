package gameplayer.model.collision;

import gameplayer.model.GamePlayData;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.weapon.Weapon;

public class Collision {
	
	private Enemy enemy;
	private Weapon weapon;
	private GamePlayData data;
	
	public Collision(Weapon shootingWeapon, Enemy targetEnemy, GamePlayData data) {
		enemy = targetEnemy;
		weapon = shootingWeapon;
		this.data = data;
	}
	
	public void processCollision() {
		this.weapon.triggerEffect(this.enemy);
		//System.out.println("old moneny: " + this.data.getGold() +" " + this.data.getScore());
		//System.out.println("add moneny: " + this.enemy.getGoldReward() + " " + this.enemy.getScoreReward());
		
		
		this.data.setGold(this.data.getGold() + this.enemy.getGoldReward());
		this.data.setScore(this.enemy.getScoreReward());
		//this.data.setScore(2);

		//enemy.setHealth(enemy.getHealth()-weapon.getDamage());
		//enemy.setHealth(enemy.getHealth()-weapon.getDamage());
//		System.out.println("PROCESSED COLLISION:::::: ");
	}
	
	
}
