package engine.enemy;

import java.util.function.BiConsumer;

import engine.AbstractTypeBuilder;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public class EnemyTypeBuilder extends AbstractTypeBuilder<Enemy, EnemyBuilder> implements EnemyBuilder, EnemyInitializer{

    public static final String DEFAULT_NAME = "New Enemy";
    public static final String DEFAULT_IMAGE_PATH = "Images/butterfly.png";
    public static final double DEFAULT_SIZE = 1;
    public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";

    public static final double DEFAULT_SPEED = 1;
    public static final double DEFAULT_HEALTH = 10;
    public static final double DEFAULT_DAMAGE = 2;
    public static final double DEFAULT_SCORE = 10;
    public static final double DEFAULT_MONEY = 20;
    public static final String DEFAULT_COLLISION_EFFECT = "";


		  
    private ObservableProperty<Double> speed;
	private ObservableProperty<Double> health;
	private ObservableProperty<Double> damage;
	private ObservableProperty<Double> score;
	private ObservableProperty<Double> money;
	private ObservableProperty<String> collisionEffect;
    
	public EnemyTypeBuilder() {
		super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
	}

	@Override
	public EnemyBuilder addSpeedListener(BiConsumer<Double, Double> listener) {
		speed.addListener(listener);
		return this;
	}

	@Override
	public EnemyBuilder addHealthListener(BiConsumer<Double, Double> listener) {
		health.addListener(listener);
		return this;
	}

	@Override
	public EnemyBuilder addDamageListener(BiConsumer<Double, Double> listener) {
		damage.addListener(listener);
		return this;
	}

	@Override
	public EnemyBuilder addScoreListener(BiConsumer<Double, Double> listener) {
		score.addListener(listener);
		return this;
	}

	@Override
	public EnemyBuilder addMoneyListener(BiConsumer<Double, Double> listener) {
		money.addListener(listener);
		return this;
	}

	@Override
	public EnemyBuilder addCollisionEffectListener(BiConsumer<String, String> listener) {
		collisionEffect.addListener(listener);
		return this;
	}

	@Override
	public ObservableProperty<Double> getSpeed() {
		return speed;
	}

	@Override
	public ObservableProperty<Double> getHealth() {
		return health;
	}

	@Override
	public ObservableProperty<Double> getDamage() {
		return damage;
	}

	@Override
	public ObservableProperty<Double> getScore() {
		return score;
	}

	@Override
	public ObservableProperty<Double> getMoney() {
		return money;
	}

	@Override
	public ObservableProperty<String> getCollisionEffect() {
		return collisionEffect;
	}

	@Override
	public EnemyBuilder buildSpeed(double speed) {
		this.speed.setProperty(speed);
		return this;
	}

	@Override
	public EnemyBuilder buildHealth(double health) {
		this.health.setProperty(health);
		return this;
	}

	@Override
	public EnemyBuilder buildDamage(double damage) {
		this.damage.setProperty(damage);
		return this;
	}

	@Override
	public EnemyBuilder buildScore(double points) {
		this.score.setProperty(points);
		return this;
	}

	@Override
	public EnemyBuilder buildMoney(double money) {
		this.money.setProperty(money);
		return this;
	}

	@Override
	public EnemyBuilder buildCollisionEffect(String collisionEffect) {
		this.collisionEffect.setProperty(collisionEffect);
		return this;
	}

	@Override
	protected Enemy create() {
		return new EnemyType(this);
	}

	@Override
	protected void restoreTypeDefaults() {
		this.speed = new ObservableObjectProperty<Double>(DEFAULT_SPEED);
		this.health = new ObservableObjectProperty<Double>(DEFAULT_HEALTH);
		this.damage = new ObservableObjectProperty<Double>(DEFAULT_DAMAGE);
		this.score = new ObservableObjectProperty<Double>(DEFAULT_SCORE);
		this.money = new ObservableObjectProperty<Double>(DEFAULT_MONEY);
		this.collisionEffect = new ObservableObjectProperty<String>(DEFAULT_COLLISION_EFFECT);
	}

	@Override
	protected EnemyBuilder getThis() {
		return this;
	}

	@Override
	protected EnemyBuilder copyType(Enemy type) {
		return this
		.buildCollisionEffect(type.getCollisionEffect())
		.buildDamage(type.getDamage())
		.buildHealth(type.getHealth())
		.buildMoney(type.getMoney())
		.buildScore(type.getScore())
		.buildSpeed(type.getSpeed());
	}

}
