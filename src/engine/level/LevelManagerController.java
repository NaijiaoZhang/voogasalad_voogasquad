package engine.level;

import java.util.List;
import authoring.editorview.level.LevelUpdateView;
import engine.ManagerController;
import engine.effect.EffectManagerController;
import engine.level.wave.Wave;
import engine.level.wave.WaveString;


public interface LevelManagerController
        extends ManagerController<LevelManager, LevelBuilder, Level, LevelUpdateView> {

    // public Map<Integer, Wave> getEnemies (int levelID);

    public void setRewardScore (int levelID, double winScore);

    public double getRewardScore (int levelID);

    public void setRewardMoney (int levelID, double winMoney);

    public double getRewardMoney (int levelID);

    public void setRewardHealth (int levelID, double winHealth);

    public double getRewardHealth (int levelID);

    public void addPath (int levelID, int pathID);

    public List<Integer> getPaths (int levelID);

    public void removePath (int levelID, int pathID);

    public void setTransitionTime (int levelID, double time);

    public double getTransitionTime (int levelID);

    public void setLevelNumber (int levelID, int levelNumber);

    public int getLevelNumber (int levelID);

    public double getLevelTime (int levelID);

    public int createWave (int levelID, LevelUpdateView updateView);

    public List<Wave> getWaves (int levelID);

    public Wave getWave (int levelID, int waveID);

    public void removeWave (int levelID, int waveID);

    public void setWaveEnemy (int levelID, int waveID, int enemyID);

    public int getWaveEnemy (int levelID, int waveID);

    public void setWaveCount (int levelID, int waveID, int count);

    public int getWaveCount (int levelID, int waveID);

    public void setWaveFrequency (int levelID, int waveID, double frequency);

    public double getWaveFrequency (int levelID, int waveID);

    public void setWavePath (int levelID, int waveID, int pathID);

    public int getWavePath (int levelID, int waveID);

    public void setWaveDelay (int levelID, int waveID, double delay);

    public double getWaveDelay (int levelID, int waveID);

    public List<WaveString> getWaveStrings (int levelID);
    
    public EffectManagerController getEffectManagerController ();
}
