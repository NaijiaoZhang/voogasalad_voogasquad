package engine.settings;

import engine.Manager;
import engine.effect.EffectManager;
import engine.path.PathManager;

public interface GameModeManager extends Manager<GameMode>{
    EffectManager getGameModeEffectManager () ;
    void visitCreatePath (PathManager pathManager, int pathID);
}
