package engine.enemy;

import engine.AbstractTypeManager;
import engine.effect.EffectManager;
import engine.effect.EffectManagerFactory;
import gameplayer.model.effect.AbstractEffectFactory;
import gameplayer.model.effect.CollisionEffectFactory;

public class EnemyTypeManager extends AbstractTypeManager<Enemy> implements EnemyManager {
    
    public static final Class<? extends AbstractEffectFactory> DEFAULT_EFFECT_FACTORY = CollisionEffectFactory.class;

    private EffectManager enemyEffectManager;
    

    EnemyTypeManager() {
    	this.enemyEffectManager = new EffectManagerFactory().create(DEFAULT_EFFECT_FACTORY);
    }
	@Override
	public EffectManager getEnemyEffectManager() {
		return enemyEffectManager;
	}

}
