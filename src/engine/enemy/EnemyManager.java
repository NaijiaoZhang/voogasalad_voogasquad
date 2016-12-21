package engine.enemy;


import engine.Manager;
import engine.effect.EffectManager;

/**
 * Created by ezra on 11/19/16.
 */
public interface EnemyManager extends Manager<Enemy> {
    EffectManager getEnemyEffectManager () ;
}
