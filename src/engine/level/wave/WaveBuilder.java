package engine.level.wave;

import engine.TypeBuilder;


public interface WaveBuilder extends TypeBuilder<Wave, WaveBuilder>, BindableWave {
    WaveBuilder buildEnemyID (int enemyID);

    WaveBuilder buildPathID (int pathID);

    WaveBuilder buildEnemyCount (int count);

    WaveBuilder buildStartTime (double time);

    WaveBuilder buildFrequency (double frequency);

}
