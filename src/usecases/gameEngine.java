package usecases;

import engine.level.LevelManagerController;

public class gameEngine {
    LevelManagerController dataSource;
    
    
    /**
     * This method will take in input from the game authoring environment and will call a method in the ILevelUpdater
     * interface to add a new enemy amount.
     * 
     * @param enemyType
     * @param amount
     */
    public void setEnemiesPerLevel(int levelID, int enemyID, int amount) {
        //dataSource.setEnemy(levelID, enemyID, amount);
    }
    
    
    
}
