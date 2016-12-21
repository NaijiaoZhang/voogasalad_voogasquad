package engine.level.wave;

import engine.AbstractType;
import engine.observer.ObservableProperty;


public class WaveType extends AbstractType implements Wave {
    // ID for type of enemy
    private ObservableProperty<Integer> enemyID;
    // ID for path of wave
    private ObservableProperty<Integer> pathID;
    // Number of enemies in wave
    private ObservableProperty<Integer> enemyCount;
    // Time in seconds that wave starts to come out
    private ObservableProperty<Double> delay;
    // Time in seconds between each enemy spawned
    private ObservableProperty<Double> frequency;

    public WaveType (WaveInitializer initializer) {
        super(initializer);
        this.enemyCount = initializer.getEnemyCount();
        this.enemyID = initializer.getEnemyID();
        this.pathID = initializer.getPathID();
        this.delay = initializer.getStartTime();
        this.frequency = initializer.getFrequency();
    }

    public int getEnemyID () {
        return enemyID.getProperty();
    }

    public void setEnemyID (int enemyID) {
        this.enemyID.setProperty(enemyID);
    }

    public int getPathID () {
        return pathID.getProperty();
    }

    public void setPathID (int pathID) {
        this.pathID.setProperty(pathID);
    }

    public int getEnemyCount () {
        return enemyCount.getProperty();
    }

    public void setEnemyCount (int enemyCount) {
        this.enemyCount.setProperty(enemyCount);
    }

    public double getStartTime () {
        return delay.getProperty();
    }

    public void setStartTime (double startTime) {
        this.delay.setProperty(startTime);
    }

    public double getFrequency () {
        return frequency.getProperty();
    }

    public void setFrequency (double frequency) {
        this.frequency.setProperty(frequency);
    }

    @Override
    public double calculateTime () {
        return enemyCount.getProperty() * frequency.getProperty() + delay.getProperty();
    }

}
