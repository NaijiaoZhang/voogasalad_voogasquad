package engine.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import authoring.editorview.level.LevelUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.effect.EffectManagerController;
import engine.effect.EffectTypeManagerController;
import engine.level.wave.Wave;
import engine.level.wave.WaveBuilder;
import engine.level.wave.WaveManager;
import engine.level.wave.WaveString;
import engine.level.wave.WaveStringType;
import engine.level.wave.WaveTypeBuilder;


public class LevelTypeManagerController
        extends AbstractTypeManagerController<LevelManager, LevelBuilder, Level, LevelUpdateView>
        implements LevelManagerController {

    private WaveBuilder waveBuilder;
    private EffectManagerController levelEffectManagerController;
    
    public LevelTypeManagerController (ManagerMediator managerMediator) {
        super(new LevelTypeManager(), new LevelTypeBuilder(), managerMediator);
        waveBuilder = new WaveTypeBuilder();
        this.levelEffectManagerController = new EffectTypeManagerController(managerMediator, getTypeManager().getLevelEffectManager());
    }

    @Override
    public void setRewardScore (int levelID, double winScore) {
        getTypeManager().getEntity(levelID).setRewardScore(winScore);
    }

    @Override
    public void setRewardMoney (int levelID, double winMoney) {
        getTypeManager().getEntity(levelID).setRewardMoney(winMoney);
    }

    @Override
    public void setRewardHealth (int levelID, double winHealth) {
        getTypeManager().getEntity(levelID).setRewardHealth(winHealth);
    }

    @Override
    public void addPath (int levelID, int pathID) {
        getTypeManager().getEntity(levelID).addPath(pathID);
    }

    @Override
    public void setTransitionTime (int levelID, double time) {
        getTypeManager().getEntity(levelID).setDurationInSeconds(time);
    }

    // @Override
    // //Probably remove
    // public Map<Integer, Wave> getEnemies (int levelID) {
    // //return getTypeManager().getEntity(levelID).getEnemyCounts()
    // return null;
    // }

    @Override
    public double getRewardScore (int levelID) {
        return getTypeManager().getEntity(levelID).getRewardScore();
    }

    @Override
    public double getRewardMoney (int levelID) {
        return getTypeManager().getEntity(levelID).getRewardMoney();
    }

    @Override
    public double getRewardHealth (int levelID) {
        return getTypeManager().getEntity(levelID).getRewardHealth();
    }

    @Override
    public List<Integer> getPaths (int levelID) {
        return getTypeManager().getEntity(levelID).getPaths();
    }

    @Override
    public double getTransitionTime (int levelID) {
        return getTypeManager().getEntity(levelID).getDurationInSeconds();
    }

    @Override
    protected LevelBuilder constructTypeProperties (LevelUpdateView updateView,
                                                    LevelBuilder typeBuilder) {
        return typeBuilder.addDurationInSecondsListener( (oldValue, newValue) -> updateView
                .updateTransitionTime(newValue))
                .addWaveListener( (oldValue, newValue) -> updateView
                        .updateWaves(getWaveStrings(newValue.waveList())))
                //.addPathListener(listener)
                .addRewardHealthListener( (oldValue, newValue) -> updateView
                        .updateRewardHealth(newValue))
                .addRewardScoreListener( (oldValue, newValue) -> updateView
                        .updateRewardScore(newValue))
                .addRewardMoneyListener( (oldValue, newValue) -> updateView
                        .updateRewardMoney(newValue));
        // TODO add listener .addLevelTimeListener()

    }

    private List<WaveString> getWaveStrings (List<Wave> waveList) {
        List<WaveString> waveStrings = new ArrayList<WaveString>();
        for (Wave wave : waveList) {
            waveStrings.add(new WaveStringType(wave));
        }
        return waveStrings;
    }

    @Override
    public void setLevelNumber (int levelID, int levelNumber) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLevelNumber (int levelID) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getLevelTime (int levelID) {
        return getTypeManager().getEntity(levelID).getLevelTime();
    }

    @Override
    public int createWave (int levelID, LevelUpdateView updateView) {
        return getTypeManager().getEntity(levelID).createWave(buildWave(updateView));
        // TODO view methods need to actually go to right thing also need to add to level
    }

    private Wave buildWave (LevelUpdateView updateView) {
        return waveBuilder.addNameListener( (oldValue, newValue) -> updateView
                .updateNameDisplay(newValue))
                .addImagePathListener( (oldValue, newValue) -> updateView
                        .updateImagePathDisplay(newValue))
                .addSizeListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .addEnemyCountListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .addEnemyIDListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .addFrequencyListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .addPathIDListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .addStartTimeListener( (oldValue, newValue) -> updateView
                        .updateSizeDisplay(newValue))
                .build();
    }

    @Override
    public void removeWave (int levelID, int waveID) {
        getTypeManager().getEntity(levelID).removeWave(waveID);
    }

    @Override
    public void setWaveEnemy (int levelID, int waveID, int enemyID) {
        getWave(levelID, waveID).setEnemyID(enemyID);
        getTypeManager().getEntity(levelID).getWaveManager().addEntry(getWave(levelID, waveID));
    }

    @Override
    public int getWaveEnemy (int levelID, int waveID) {
        return getWave(levelID, waveID).getEnemyID();
    }

    @Override
    public void setWaveCount (int levelID, int waveID, int count) {
        getWave(levelID, waveID).setEnemyCount(count);
        getTypeManager().getEntity(levelID).getWaveManager().addEntry(getWave(levelID, waveID));
    }

    @Override
    public int getWaveCount (int levelID, int waveID) {
        return getWave(levelID, waveID).getEnemyCount();
    }

    @Override
    public void setWaveFrequency (int levelID, int waveID, double frequency) {
        getWave(levelID, waveID).setFrequency(frequency);
        getTypeManager().getEntity(levelID).getWaveManager().addEntry(getWave(levelID, waveID));
    }

    @Override
    public double getWaveFrequency (int levelID, int waveID) {
        return getWave(levelID, waveID).getFrequency();
    }

    @Override
    public void setWavePath (int levelID, int waveID, int pathID) {
//    	if (getTypeManager().getEntity(levelID).getPaths().contains(pathID)){
//    		getWave(levelID, waveID).setPathID(pathID);
//    	}
		getWave(levelID, waveID).setPathID(pathID);
		getTypeManager().getEntity(levelID).getWaveManager().addEntry(getWave(levelID, waveID));

    }

    @Override
    public int getWavePath (int levelID, int waveID) {
        return getWave(levelID, waveID).getPathID();
    }

    @Override
    public void setWaveDelay (int levelID, int waveID, double delay) {
        getWave(levelID, waveID).setStartTime(delay);
        getTypeManager().getEntity(levelID).getWaveManager().addEntry(getWave(levelID, waveID));
    }

    @Override
    public List<Wave> getWaves (int levelID) {
        return getTypeManager().getEntity(levelID).getWaves();
    }

    @Override
    public Wave getWave (int levelID, int waveID) {
        return getTypeManager().getEntity(levelID).getWave(waveID);
    }

    @Override
    public void removePath (int levelID, int pathID) {
        getTypeManager().getEntity(levelID).removePath(pathID);
    }

    @Override
    public double getWaveDelay (int levelID, int waveID) {
        return getWave(levelID, waveID).getStartTime();
    }

    @Override
    public List<WaveString> getWaveStrings (int levelID) {
        return getWaveStrings(getTypeManager().getEntity(levelID).getWaveManager().waveList());
    }

	@Override
	public EffectManagerController getEffectManagerController() {
		return levelEffectManagerController;
	}

}
