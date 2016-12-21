package engine.level;

import engine.AbstractTypeManager;
import engine.effect.EffectManager;
import engine.effect.EffectManagerFactory;
import engine.enemy.EnemyManager;
import engine.path.PathManager;
import engine.settings.GameModeManager;
import gameplayer.model.effect.AbstractEffectFactory;
import gameplayer.model.effect.CollisionEffectFactory;


/**
 *
 * Created by ezra on 11/17/16.
 */
public class LevelTypeManager extends AbstractTypeManager<Level> implements LevelManager {
    public static final Class<? extends AbstractEffectFactory> DEFAULT_EFFECT_FACTORY = CollisionEffectFactory.class;
	
	private EffectManager levelEffectManager;
	
	LevelTypeManager() {
		this.levelEffectManager = new EffectManagerFactory().create(DEFAULT_EFFECT_FACTORY);
	}

    @Override
    public void visitRemoveEntry (EnemyManager manager, Integer index) {
        applyToAllEntities(a -> a.removeEnemyReferences(index));
    }

	@Override
	public void visitRemoveEntry(PathManager manager, Integer index) {
		removePathReferences(index);
	}

	@Override
	public void visitAddPath(GameModeManager manager, Integer pathID) {
		applyToAllEntities(a -> a.addPath(pathID));
	}

	@Override
	public void visitRemovePath(GameModeManager manager, Integer pathID) {
		removePathReferences(pathID);	
	}
	
	private void removePathReferences(int pathID) {
        applyToAllEntities(a -> a.removePathReferences(pathID));
	}

	@Override
	public void visitGridSize(GameModeManager manager, Integer gridSize) {
		applyToAllEntities(a -> a.resetGridWaves());
	}

	@Override
	public EffectManager getLevelEffectManager() {
		return levelEffectManager;
	}

}
