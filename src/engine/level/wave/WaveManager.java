package engine.level.wave;

import java.util.List;

import engine.Manager;
import engine.enemy.EnemyManager;

public interface WaveManager extends Manager<Wave>{
    void visitRemoveEntry(EnemyManager manager, Integer index);
    public List<Wave> waveList();

}
