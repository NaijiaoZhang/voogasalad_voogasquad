package authoring.editorview.level;

/**
 * 
 * @author Kayla Schulz, Andrew Bihl, Diane Hadley
 *
 */
public interface LevelAuthoringViewDelegate {

    public void onUserEnteredRewardScore (String score);

    public void onUserEnteredRewardMoney (String money);

    public void onUserEnteredRewardHealth (String health);

    public void onUserEnteredEditLevel (String level);

    public void onUserEnteredLevelName (String levelName);

    public void onUserEnteredCreateLevel ();

    public void onUserPressedDeleteLevel ();

    public void onUserEnteredTransitionTime (String time);

    public void onUserPressedAddEffect ();

    public void onUserSelectedLevel (int levelID);

    // WAVE

    public void onUserEnteredAddWave ();

    public void onUserEnteredRemoveWave (String waveID);

    public void onUserEnteredEnemyFrequency (String waveID, String frequency);

    public void onUserEnteredEnemy (String waveID, String enemyID);

    public void onUserEnteredNumofEnemies (String waveID, String numEnemies);

    public void onUserEnteredSpawnPoint (String waveID, String spawnPoint);

    public void onUserEnteredWaveTimeDelay (String waveID, String timeDelay);

    public void onUserEnteredLevelImagePath (String path);

}
