package authoring.editorview.level;

import javafx.beans.property.SimpleStringProperty;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WaveObject {
    private final SimpleStringProperty enemyName;
    private final SimpleStringProperty enemyFrequency;
    private final SimpleStringProperty enemyCount;
    private final SimpleStringProperty waveNumber;
    private final SimpleStringProperty path;
    private final SimpleStringProperty startTime;

    public WaveObject (SimpleStringProperty waveNumber,
                       SimpleStringProperty enemyName,
                       SimpleStringProperty enemyCount,
                       SimpleStringProperty enemyFrequency,
                       SimpleStringProperty path,
                       SimpleStringProperty startTime) {
        this.enemyName = enemyName;
        this.enemyFrequency = enemyFrequency;
        this.enemyCount = enemyCount;
        this.waveNumber = waveNumber;
        this.path = path;
        this.startTime = startTime;
    }

    public String getEnemyName () {
        return enemyName.get();
    }

    public void setEnemyName (String enemyName) {
        this.enemyName.set(enemyName);
    }

    public String getEnemyFrequency () {
        return enemyFrequency.get();
    }

    public void setEnemyFrequency (String enemyFrequency) {
        this.enemyFrequency.set(enemyFrequency);
    }

    public String getNumOfEnemies () {
        return enemyCount.get();
    }

    public void setNumOfEnemies (String numOfEnemies) {
        this.enemyCount.set(numOfEnemies);
    }

    public String getWaveNumber () {
        return waveNumber.get();
    }

    public void setWaveNumber (String waveNumber) {
        this.waveNumber.set(waveNumber);
    }

    public String getPath () {
        return path.get();
    }

    public void setPath (String path) {
        this.path.set(path);
    }

    public String getTimeDelay () {
        return startTime.get();
    }

    public void setTimeDelay (String timeDelay) {
        this.startTime.set(timeDelay);
    }
}
