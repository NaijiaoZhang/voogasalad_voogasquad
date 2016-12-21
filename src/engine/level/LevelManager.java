package engine.level;

import engine.Manager;
import engine.effect.EffectManager;
import engine.enemy.EnemyManager;
import engine.path.PathManager;
import engine.settings.GameModeManager;


/**
 * Created by ezra on 11/20/16.
 */
public interface LevelManager extends Manager<Level> {

    EffectManager getLevelEffectManager () ;
    void visitRemoveEntry (EnemyManager manager, Integer index);
    void visitRemoveEntry(PathManager manager, Integer index);
    void visitAddPath(GameModeManager manager, Integer pathID);
    void visitRemovePath(GameModeManager manager, Integer pathID);
    void visitGridSize(GameModeManager manager, Integer gridSize);
}
