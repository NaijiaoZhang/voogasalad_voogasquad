package engine.enemy;

import java.util.function.BiConsumer;

import engine.BindableType;

public interface BindableEnemy extends BindableType<EnemyBuilder>{ 
	
	EnemyBuilder addSpeedListener(BiConsumer<Double, Double> listener);
	EnemyBuilder addHealthListener(BiConsumer<Double, Double> listener);
	EnemyBuilder addDamageListener(BiConsumer<Double, Double> listener);
	EnemyBuilder addScoreListener(BiConsumer<Double, Double> listener);
	EnemyBuilder addMoneyListener(BiConsumer<Double, Double> listener);
	EnemyBuilder addCollisionEffectListener(BiConsumer<String, String> listener);

}
