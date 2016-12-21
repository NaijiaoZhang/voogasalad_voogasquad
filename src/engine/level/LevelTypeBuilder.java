package engine.level;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import engine.AbstractTypeBuilder;
import engine.level.wave.WaveManager;
import engine.level.wave.WaveTypeManager;
import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public class LevelTypeBuilder extends AbstractTypeBuilder<Level, LevelBuilder>
        implements LevelBuilder, LevelInitializer {

    public static final String DEFAULT_NAME = "Level";
    public static final String DEFAULT_IMAGE_PATH = "Images/blacksquarejpg";
    public static final double DEFAULT_SIZE = 1;
    public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";

    //remove??
    public static final WaveManager DEFAULT_ENEMY_COUNTS = new WaveTypeManager();
    public static final double DEFAULT_REWARD_HEALTH = 0;
    public static final double DEFAULT_REWARD_MONEY = 200;
    public static final double DEFAULT_REWARD_SCORE = 200;
    public static final double DEFAULT_DURATION_IN_SECONDS = 1;
    public static final double DEFAULT_LEVEL_TIME = 0;
    public static final Integer[] DEFAULT_PATHS = new Integer[] {};

    private ObservableList<Integer> paths;
    private ObservableObjectProperty<WaveManager> enemyCounts;
    private ObservableProperty<Double> rewardHealth;
    private ObservableProperty<Double> rewardMoney;
    private ObservableProperty<Double> rewardScore;
    private ObservableProperty<Double> durationInSeconds;
    private ObservableProperty<Double> time;

    protected LevelTypeBuilder () {
        super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
    }

    @Override
    public LevelBuilder addLevelTimeListener (BiConsumer<Double, Double> listener) {
        this.time.addListener(listener);
        return this;
    }

    @Override
    public LevelBuilder addPathListener (BiConsumer<List<Integer>, List<Integer>> listener) {
        paths.addListener(listener);
        return this;
    }
    
    @Override
	public LevelBuilder addWaveListener(BiConsumer<WaveManager, WaveManager> listener) {
    	enemyCounts.addListener(listener);
      return this;
	}

    @Override
    public LevelBuilder addRewardHealthListener (BiConsumer<Double, Double> listener) {
        rewardHealth.addListener(listener);
        return this;
    }

    @Override
    public LevelBuilder addRewardMoneyListener (BiConsumer<Double, Double> listener) {
        rewardMoney.addListener(listener);
        return this;
    }

    @Override
    public LevelBuilder addRewardScoreListener (BiConsumer<Double, Double> listener) {
        rewardScore.addListener(listener);
        return this;
    }

    @Override
    public LevelBuilder addDurationInSecondsListener (BiConsumer<Double, Double> listener) {
        durationInSeconds.addListener(listener);
        return this;
    }

    @Override
    public ObservableObjectProperty<WaveManager> getWaves () {
        return enemyCounts;
    }

    @Override
    public ObservableProperty<Double> getRewardHealth () {
        return rewardHealth;
    }

    @Override
    public ObservableProperty<Double> getRewardMoney () {
        return rewardMoney;
    }

    @Override
    public ObservableProperty<Double> getRewardScore () {
        return rewardScore;
    }

    @Override
    public ObservableProperty<Double> getDurationInSeconds () {
        return durationInSeconds;
    }

    @Override
    public ObservableProperty<Double> getLevelTime () {
        return time;
    }

    @Override
    public ObservableList<Integer> getPaths () {
        return paths;
    }

    @Override
    public LevelBuilder buildWaves (WaveManager enemies) {
        this.enemyCounts.setProperty(enemies);
        return this;
    }

    @Override
    public LevelBuilder buildRewardHealth (double rewardHealth) {
        this.rewardHealth.setProperty(rewardHealth);
        return this;
    }

    @Override
    public LevelBuilder buildRewardMoney (double rewardMoney) {
        this.rewardMoney.setProperty(rewardMoney);
        return this;
    }

    @Override
    public LevelBuilder buildRewardScore (double rewardPoints) {
        this.rewardScore.setProperty(rewardPoints);
        return this;
    }

    @Override
    public LevelBuilder buildDurationInSeconds (double durationInSeconds) {
        this.durationInSeconds.setProperty(durationInSeconds);
        return this;
    }

    @Override
    public LevelBuilder buildPaths (List<Integer> paths) {
        this.paths.setProperty(paths);
        return this;
    }

    @Override
    public LevelBuilder buildLevelTime (double time) {
        this.time.setProperty(time);
        return this;
    }

    @Override
    protected Level create () {
        return new LevelType(this);
    }

    @Override
    protected void restoreTypeDefaults () {
        this.enemyCounts = new ObservableObjectProperty<WaveManager>(new WaveTypeManager());
        this.rewardHealth = new ObservableObjectProperty<Double>(DEFAULT_REWARD_HEALTH);
        this.rewardMoney = new ObservableObjectProperty<Double>(DEFAULT_REWARD_MONEY);
        this.rewardScore = new ObservableObjectProperty<Double>(DEFAULT_REWARD_SCORE);
        this.durationInSeconds = new ObservableObjectProperty<Double>(DEFAULT_DURATION_IN_SECONDS);
        this.time = new ObservableObjectProperty<Double>(DEFAULT_LEVEL_TIME);
        this.paths =
                new ObservableListProperty<Integer>(Arrays.stream(DEFAULT_PATHS)
                        .collect(Collectors.toList()));

    }

    @Override
    protected LevelBuilder getThis () {
        return this;
    }

	@Override
	protected LevelBuilder copyType(Level type) {
		return this
		.buildDurationInSeconds(type.getDurationInSeconds())
		.buildLevelTime(type.getLevelTime())
		.buildPaths(type.getPaths())
		.buildRewardHealth(type.getRewardHealth())
		.buildRewardMoney(type.getRewardMoney())
		.buildRewardScore(type.getRewardScore())
		.buildWaves(type.getWaveManager());
	}

	

}
