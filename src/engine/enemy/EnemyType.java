package engine.enemy;

import engine.AbstractType;
import engine.observer.ObservableProperty;

public class EnemyType extends AbstractType implements Enemy {

    private ObservableProperty<Double> speed;
	private ObservableProperty<Double> health;
	private ObservableProperty<Double> damage;
	private ObservableProperty<Double> score;
	private ObservableProperty<Double> money;
	private ObservableProperty<String> collisionEffect;

	
    protected EnemyType (EnemyInitializer enemyInitializer) {
        super(enemyInitializer);
        this.speed = enemyInitializer.getSpeed();
        this.health = enemyInitializer.getHealth();
        this.damage = enemyInitializer.getDamage();
        this.score = enemyInitializer.getScore();
        this.money = enemyInitializer.getMoney();
        this.collisionEffect = enemyInitializer.getCollisionEffect();
    }
	
	
	@Override
    public double getSpeed() {
		return speed.getProperty();
	}
	@Override
    public void setSpeed(double speed) {
		this.speed.setProperty(speed);
	}
	@Override
    public double getHealth() {
		return health.getProperty();
	}
	@Override
    public void setHealth(double health) {
		this.health.setProperty(health);
	}
	@Override
    public double getDamage(){
		return damage.getProperty();
	}
	@Override
    public void setDamage(double damage) {
		this.damage.setProperty(damage);
	}
	@Override
    public double getScore() {
		return score.getProperty();
	}
	@Override
    public void setScore(double score) {
		this.score.setProperty(score); 
	}
	@Override
    public double getMoney() {
		return money.getProperty();
	}
	@Override
    public void setMoney(double money) {
		this.money.setProperty(money);
	}
	@Override
    public String getCollisionEffect() {
		return collisionEffect.getProperty();
	}
	@Override
    public void setCollisionEffect(String collisionEffect) {
		this.collisionEffect.setProperty(collisionEffect);
	}
	
}
