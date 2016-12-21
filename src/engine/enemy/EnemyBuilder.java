package engine.enemy;


import engine.TypeBuilder;

public interface EnemyBuilder extends TypeBuilder<Enemy, EnemyBuilder>, BindableEnemy { //TODO - Add bindable interface

	EnemyBuilder buildSpeed(double speed);
	EnemyBuilder buildHealth(double health);
	EnemyBuilder buildDamage(double damage);
	EnemyBuilder buildScore(double score);
	EnemyBuilder buildMoney(double money);
	EnemyBuilder buildCollisionEffect(String collisionEffect);
}
