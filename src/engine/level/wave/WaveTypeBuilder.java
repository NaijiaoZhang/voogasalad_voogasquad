package engine.level.wave;

import java.util.function.BiConsumer;
import engine.AbstractTypeBuilder;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public class WaveTypeBuilder extends AbstractTypeBuilder<Wave, WaveBuilder>
        implements WaveBuilder, WaveInitializer {

    public static final String DEFAULT_NAME = "New Wave";
    public static final String DEFAULT_IMAGE_PATH = "Images/penguin.jpg";
    public static final double DEFAULT_SIZE = 1;
    public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";


    public static final int DEFAULT_ENEMY_COUNT = 0;
    public static final int DEFAULT_ENEMY_ID = 0;
    public static final int DEFAULT_PATH_ID = 0;
    public static final double DEFAULT_START_TIME = 0;
    public static final double DEFAULT_FREQUENCY = 0;

    private ObservableProperty<Integer> enemyCount;
    private ObservableProperty<Integer> enemyID;
    private ObservableProperty<Integer> pathID;
    private ObservableProperty<Double> startTime;
    private ObservableProperty<Double> frequency;

    public WaveTypeBuilder () {
        super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
    }

    @Override
    public WaveBuilder addEnemyIDListener (BiConsumer<Integer, Integer> listener) {
        enemyID.addListener(listener);
        return this;
    }

    @Override
    public WaveBuilder addPathIDListener (BiConsumer<Integer, Integer> listener) {
        pathID.addListener(listener);
        return this;
    }

    @Override
    public WaveBuilder addEnemyCountListener (BiConsumer<Integer, Integer> listener) {
        enemyCount.addListener(listener);
        return this;
    }

    @Override
    public WaveBuilder addStartTimeListener (BiConsumer<Double, Double> listener) {
        startTime.addListener(listener);
        return this;
    }

    @Override
    public WaveBuilder addFrequencyListener (BiConsumer<Double, Double> listener) {
        frequency.addListener(listener);
        return this;
    }

    @Override
    public ObservableProperty<Integer> getEnemyID () {
        return enemyID;
    }

    @Override
    public ObservableProperty<Integer> getPathID () {
        return pathID;
    }

    @Override
    public ObservableProperty<Integer> getEnemyCount () {
        return enemyCount;
    }

    @Override
    public ObservableProperty<Double> getStartTime () {
        return startTime;
    }

    @Override
    public ObservableProperty<Double> getFrequency () {
        return frequency;
    }

    @Override
    public WaveBuilder buildEnemyID (int enemyID) {
        this.enemyID.setProperty(enemyID);
        return this;
    }

    @Override
    public WaveBuilder buildPathID (int pathID) {
        this.pathID.setProperty(pathID);
        return this;
    }

    @Override
    public WaveBuilder buildEnemyCount (int count) {
        this.enemyCount.setProperty(count);
        return this;
    }

    @Override
    public WaveBuilder buildStartTime (double time) {
        this.startTime.setProperty(time);
        return this;
    }

    @Override
    public WaveBuilder buildFrequency (double frequency) {
        this.frequency.setProperty(frequency);
        return this;
    }

    @Override
    protected Wave create () {
        return new WaveType(this);
    }

    @Override
    protected void restoreTypeDefaults () {
        this.enemyID = new ObservableObjectProperty<Integer>(DEFAULT_ENEMY_ID);
        this.enemyCount = new ObservableObjectProperty<Integer>(DEFAULT_ENEMY_COUNT);
        this.pathID = new ObservableObjectProperty<Integer>(DEFAULT_PATH_ID);
        this.startTime = new ObservableObjectProperty<Double>(DEFAULT_START_TIME);
        this.frequency = new ObservableObjectProperty<Double>(DEFAULT_FREQUENCY);

    }

    @Override
    protected WaveBuilder getThis () {
        return this;
    }

	@Override
	protected WaveBuilder copyType(Wave type) {
		return this
		.buildEnemyCount(type.getEnemyCount())
		.buildEnemyID(type.getEnemyID())
		.buildFrequency(type.getFrequency())
		.buildPathID(type.getPathID())
		.buildStartTime(type.getStartTime());
	}

}
