package engine.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.AbstractType;
import engine.level.wave.Wave;
import engine.level.wave.WaveManager;
import engine.level.wave.WaveTypeComparator;
import engine.observer.ObservableList;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public class LevelType extends AbstractType implements Level {
    private ObservableObjectProperty<WaveManager> waves;
    private ObservableProperty<Double> rewardHealth;
    private ObservableProperty<Double> rewardMoney;
    private ObservableProperty<Double> rewardScore;
    private ObservableProperty<Double> durationInSeconds;
    private ObservableProperty<Double> time;
    private ObservableList<Integer> paths;

    protected LevelType (LevelInitializer levelInitializer) {
        super(levelInitializer);
        this.waves = levelInitializer.getWaves();
        this.rewardHealth = levelInitializer.getRewardHealth();
        this.rewardMoney = levelInitializer.getRewardMoney();
        this.rewardScore = levelInitializer.getRewardScore();
        this.durationInSeconds = levelInitializer.getDurationInSeconds();
        this.time = levelInitializer.getLevelTime();
        this.paths = levelInitializer.getPaths();
    }

    @Override
    public List<Wave> getWaves () {
        List<Wave> sortedWaves = new ArrayList<Wave>(waves.getProperty().getEntities().values());
        Collections.sort(sortedWaves, new WaveTypeComparator());
        return sortedWaves;
    }

    @Override
    public void removeWave (int enemy) {
        waves.getProperty().getEntities().remove(enemy);
        waves.notifyObservers(waves.getProperty());
    }

    @Override
    public double getRewardHealth () {
        return rewardHealth.getProperty();
    }

    @Override
    public void setRewardHealth (double rewardHealth) {
        this.rewardHealth.setProperty(rewardHealth);
    }

    @Override
    public double getRewardMoney () {
        return rewardMoney.getProperty();
    }

    @Override
    public void setRewardMoney (double rewardMoney) {
        this.rewardMoney.setProperty(rewardMoney);
    }

    @Override
    public double getRewardScore () {
        return rewardScore.getProperty();
    }

    @Override
    public void setRewardScore (double rewardPoints) {
        this.rewardScore.setProperty(rewardPoints);
    }

    @Override
    public double getDurationInSeconds () {
        return durationInSeconds.getProperty();
    }

    @Override
    public void setDurationInSeconds (double durationInSeconds) {
        this.durationInSeconds.setProperty(durationInSeconds);
    }

    @Override
    public double getLevelTime () {
        return time.getProperty();
    }

    @Override
    public int createWave (Wave wave) {
    	waves.getProperty().addEntry(wave);
    	waves.notifyObservers(waves.getProperty());
        return wave.getId();
    }

    @Override
    public Wave getWave (int id) {
        return waves.getProperty().getEntity(id);
    }

    @Override
    public void calculateLevelTime (int waveID) {
        Wave wave = waves.getProperty().getEntity(waveID);
        if (wave.getEnemyCount() * wave.getFrequency() + wave.getStartTime() > time.getProperty()) {
            time.setProperty(wave.getEnemyCount() * wave.getFrequency() + wave.getStartTime());
        }
    }

    @Override
    public void addPath (int pathID) {
        paths.add(pathID);
    }

    @Override
    public void removePath (int pathID) {
        paths.remove(pathID);
    }

    @Override
    public List<Integer> getPaths () {
        return paths.getProperty();
    }

	@Override
	public Map<Integer, Wave> getWaveMap() {
		return waves.getProperty().getEntities();
	}

	@Override
	public WaveManager getWaveManager() {
		return waves.getProperty();
	}

	@Override
	public void removeEnemyReferences(int enemyID) {
		for (Wave wave : waves.getProperty().getEntities().values()) {
			if (wave.getEnemyID() == enemyID) {
				removeWave(wave.getId());
			}
		}
	}

	@Override
	public void resetGridWaves() {
		paths.setProperty(new ArrayList<Integer>());
		waves.getProperty().setEntities(new HashMap<Integer, Wave>());
        waves.notifyObservers(waves.getProperty());
	}

	@Override
	public void setWaveManager(WaveManager waveManager) {
		waves.setProperty(waveManager);
	}

	@Override
	public void removePathReferences(int pathID) {
		paths.remove(pathID);
		for (Wave wave : waves.getProperty().getEntities().values()) {
			if (wave.getPathID() == pathID) {
				removeWave(wave.getId());
			}
		}
		System.out.println(waves);
	}

}
