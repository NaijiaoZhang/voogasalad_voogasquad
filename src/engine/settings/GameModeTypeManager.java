package engine.settings;

import engine.AbstractTypeManager;
import engine.effect.EffectBuilder;
import engine.effect.EffectManager;
import engine.effect.EffectManagerFactory;
import engine.effect.EffectTypeBuilder;
import engine.path.PathManager;
import gameplayer.model.effect.AbstractEffectFactory;
import gameplayer.model.effect.WinEffectFactory;

public class GameModeTypeManager extends AbstractTypeManager<GameMode> implements GameModeManager{
    public static final Class<? extends AbstractEffectFactory> DEFAULT_EFFECT_FACTORY = WinEffectFactory.class;
	
	private EffectManager gameModeEffectManager;
	
	GameModeTypeManager() {
		this.gameModeEffectManager = new EffectManagerFactory().create(DEFAULT_EFFECT_FACTORY);
		 EffectBuilder effectFactory = new EffectTypeBuilder();
		 gameModeEffectManager.addEntry(effectFactory.buildTriggerConditionGroovy("data.getTotalLevel() + 1 == data.getCurrentLevel()")
				 									.buildEffectGroovy("data.setWin()")
				 									.build());
	}
	
	@Override
	public EffectManager getGameModeEffectManager() {
		return gameModeEffectManager;
	}

	@Override
	public void visitCreatePath(PathManager pathManager, int pathID) {
        applyToAllEntities(a -> a.addPath(pathID));
	}

}
