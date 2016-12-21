package engine.enemy;

import authoring.editorview.enemy.EnemyUpdateView;
import engine.ManagerController;
import engine.effect.EffectManagerController;


// IEnemyUpdateView needs to extend IUpdateView
public interface EnemyManagerController
        extends ManagerController<EnemyManager, EnemyBuilder, Enemy, EnemyUpdateView> {

    public void setEnemySpeed (int enemyID, double enemySpeed);

    public void setEnemyHealth (int enemyID, double enemyHealth);

    public void setEnemyDamage (int enemyID, double enemyDamage);

    public void setEnemyRewardMoney (int enemyID, double enemyRewardMoney);

    public void setEnemyRewardScore (int enemyID, double enemyRewardScore);

    public void setEnemyCollisionEffect (int enemyID, String enemyCollisionEffect);

    /**
     * Values to get from model
     * 
     * @param enemyID
     * @return
     */

    public double getEnemySpeed (int enemyID); //

    public double getEnemyHealth (int enemyID); //

    public double getEnemyDamage (int enemyID); //

    public double getEnemyRewardMoney (int enemyID); //

    public double getEnemyRewardScore (int enemyID); //

    public String getEnemyCollisionEffect (int enemyID);
    
    public EffectManagerController getEffectManagerController ();


}
