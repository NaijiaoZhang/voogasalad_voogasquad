package engine.level;

import engine.Type;
import engine.level.wave.Wave;
import engine.level.wave.WaveManager;

import java.util.List;
import java.util.Map;


/**
 * Created by ezra on 11/19/16.
 */
public interface Level extends Type {

    Map<Integer, Wave> getWaveMap ();
	
	List<Wave> getWaves ();

    double getRewardHealth ();

    void setRewardHealth (double rewardHealth);

    double getRewardMoney ();

    void setRewardMoney (double rewardMoney);

    double getRewardScore ();

    void setRewardScore (double rewardScore);

    double getDurationInSeconds ();

    void setDurationInSeconds (double durationInSeconds);

    double getLevelTime ();

    void calculateLevelTime (int waveID);

    void removeWave (int waveID);

    int createWave (Wave wave);

    // List of Waves
    Wave getWave (int id);

    void addPath (int pathID);

    void removePath (int pathID);

    List<Integer> getPaths ();
    
    WaveManager getWaveManager ();
    
    void setWaveManager (WaveManager waveManager);
    
    void removeEnemyReferences (int enemyID);
    
    void resetGridWaves();
    
    void removePathReferences (int pathID);

    
}
