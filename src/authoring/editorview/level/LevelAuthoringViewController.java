package authoring.editorview.level;

import java.util.ResourceBundle;
import authoring.editorview.EditorViewController;
import authoring.editorview.ListCellData;
import authoring.editorview.ListDataSource;
import authoring.editorview.collisioneffects.EffectAuthoringViewController;
import authoring.utilityfactories.DialogueBoxFactory;
import engine.effect.EffectManagerController;
import engine.enemy.EnemyManagerController;
import engine.level.LevelManagerController;


/**
 * 
 * @author Kayla Schulz
 * @author Diane Hadley
 *
 */
public class LevelAuthoringViewController extends EditorViewController
        implements LevelAuthoringViewDelegate, ListDataSource {

    private LevelUpdateView levelView;
    private LevelManagerController levelDataSource;
    private EnemyManagerController enemyDataSource;
    private EffectManagerController effectDataSource;
    private int currentLevelID;

    public LevelAuthoringViewController (int editorWidth, int editorHeight) {
        levelView = LevelAuthoringViewFactory.build(editorWidth, editorHeight);
        levelView.setDelegate(this);
        this.levelView.setLevelListDataSource(this);
        this.view = levelView;
    }

    public void setLevelDataSource (LevelManagerController source, EnemyManagerController enemySource) {
        this.levelDataSource = source;
        enemyDataSource = enemySource;
        this.levelDataSource.addTypeBankListener(this.levelView);
        effectDataSource = levelDataSource.getEffectManagerController();
        onUserEnteredCreateLevel();
    }

    public void setEnemyDataSource (EnemyManagerController source) {
        this.enemyDataSource = source;
    }

    @Override
    public void onUserEnteredEditLevel (String level) {
        try {
            Integer.parseInt(level);
            currentLevelID = Integer.parseInt(level);
            currentLevelID = levelDataSource.createType(levelView);
        }
        catch (Exception e) {
            DialogueBoxFactory.createErrorDialogueBox("Not a correct level",
                                                      "Choose an appropriate integer");
        }
    }

    @Override
    public void onUserEnteredLevelName (String levelName) {
        levelDataSource.setName(currentLevelID, levelName);
    }

    @Override
    public void onUserEnteredCreateLevel () {
        currentLevelID = levelDataSource.createType(levelView);
        refreshView();
    }

    @Override
    public void onUserPressedDeleteLevel () {
        int nextID = this.levelView.getNearestAvailableItemID(currentLevelID);
        levelDataSource.deleteType(currentLevelID);
        currentLevelID = nextID;
        this.refreshView();
    }

    @Override
    public void onUserEnteredTransitionTime (String time) {
        try {
            Double.parseDouble(time);
            levelDataSource.setTransitionTime(currentLevelID, Double.parseDouble(time));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredEnemyFrequency (String waveID, String frequency) {
        try {
            Double.parseDouble(frequency);
            levelDataSource.setWaveFrequency(currentLevelID, Integer.parseInt(waveID),
                                             Double.parseDouble(frequency));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredRemoveWave (String waveID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUserEnteredRewardScore (String score) {
        try {
            Double.parseDouble(score);
            levelDataSource.setRewardScore(currentLevelID, Double.parseDouble(score));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredRewardMoney (String money) {
        try {
            Double.parseDouble(money);
            levelDataSource.setRewardMoney(currentLevelID, Double.parseDouble(money));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredRewardHealth (String health) {
        try {
            Double.parseDouble(health);
            levelDataSource.setRewardHealth(currentLevelID, Double.parseDouble(health));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    private void createIntCheckDialogueBox () {
        ResourceBundle dialogueBoxResource = ResourceBundle.getBundle("resources/DialogueBox");
        DialogueBoxFactory.createErrorDialogueBox(dialogueBoxResource.getString("Integer"),
                                                  dialogueBoxResource.getString("CheckInput"));
    }

    @Override
    public void onUserEnteredEnemy (String waveID, String enemyID) {
        try {
            levelDataSource.setWaveEnemy(currentLevelID, Integer.parseInt(waveID),
                                         Integer.parseInt(enemyID));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }

    }

    @Override
    public void onUserEnteredNumofEnemies (String waveID, String numEnemies) {
        try {
            Integer.parseInt(numEnemies);
            levelDataSource.setWaveCount(currentLevelID, Integer.parseInt(waveID),
                                         Integer.parseInt(numEnemies));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredSpawnPoint (String waveID, String spawnPoint) {
        try {
            Integer.parseInt(spawnPoint);
            levelDataSource.setWavePath(currentLevelID, Integer.parseInt(waveID),
                                        Integer.parseInt(spawnPoint));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserEnteredWaveTimeDelay (String waveID, String timeDelay) {
        try {
            Double.parseDouble(timeDelay);
            levelDataSource.setWaveDelay(currentLevelID, Integer.parseInt(waveID),
                                         Double.parseDouble(timeDelay));
        }
        catch (NumberFormatException e) {
            createIntCheckDialogueBox();
        }
    }

    @Override
    public void onUserPressedAddEffect () {
        EffectAuthoringViewController effectAuthoringView =
                new EffectAuthoringViewController(effectDataSource);
        effectDataSource.createType(effectAuthoringView.getEffectAuthoringView());
        effectAuthoringView.setEffectOptions(effectDataSource.getCreatedTypeIds());
        effectAuthoringView.setAvailClasses(effectDataSource.getAvailableClasses());
        effectAuthoringView.setAvailDataObjects(effectDataSource.getAvailableDataObjects());
        effectAuthoringView.openEffectView();
    }

    @Override
    public void refreshView () {
        levelView.updateTransitionTime(levelDataSource.getTransitionTime(currentLevelID));
        levelView.updateRewardHealth(levelDataSource.getRewardHealth(currentLevelID));
        levelView.updateRewardMoney(levelDataSource.getRewardMoney(currentLevelID));
        levelView.updateRewardScore(levelDataSource.getRewardScore(currentLevelID));
        levelView.updateNameDisplay(levelDataSource.getName(currentLevelID));
        levelView.updateWaves(levelDataSource.getWaveStrings(currentLevelID));
    }

    @Override
    public void onUserSelectedLevel (int levelID) {
        currentLevelID = levelID;
        refreshView();
    }

    @Override
    public void onUserEnteredLevelImagePath (String path) {
        levelDataSource.setImagePath(currentLevelID, path);
    }

    @Override
    public ListCellData getCellDataForSubject (int id) {
        ListCellData cellData = new ListCellData();
        cellData.setName(levelDataSource.getName(id));
        cellData.setImagePath(levelDataSource.getImagePath(id));
        cellData.setId(id);
        return cellData;
    }

    @Override
    public void onUserEnteredAddWave () {
        levelDataSource.createWave(currentLevelID, levelView);
    }
}
