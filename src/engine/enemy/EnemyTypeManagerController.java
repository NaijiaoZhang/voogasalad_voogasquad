package engine.enemy;

import authoring.editorview.enemy.EnemyUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.effect.EffectManager;
import engine.effect.EffectManagerController;
import engine.effect.EffectTypeManager;
import engine.effect.EffectTypeManagerController;


/**
 * Created by ezra on 11/29/16.
 */
public class EnemyTypeManagerController
        extends AbstractTypeManagerController<EnemyManager, EnemyBuilder, Enemy, EnemyUpdateView>
        implements EnemyManagerController {
    private EffectManagerController enemyEffectManagerController;

    public EnemyTypeManagerController (ManagerMediator managerMediator) {
        super(new EnemyTypeManager(), new EnemyTypeBuilder(), managerMediator);
        this.enemyEffectManagerController =
                new EffectTypeManagerController(managerMediator,
                                                getTypeManager().getEnemyEffectManager());
    }

    @Override
    public void setEnemySpeed (int enemyID, double enemySpeed) {
        getTypeManager().getEntity(enemyID).setSpeed(enemySpeed);
    }

    @Override
    public void setEnemyHealth (int enemyID, double enemyHealth) {
        getTypeManager().getEntity(enemyID).setHealth(enemyHealth);
    }

    @Override
    public void setEnemyDamage (int enemyID, double enemyDamage) {
        getTypeManager().getEntity(enemyID).setDamage(enemyDamage);
    }

    @Override
    public void setEnemyRewardMoney (int enemyID, double enemyRewardMoney) {
        getTypeManager().getEntity(enemyID).setMoney(enemyRewardMoney);
    }

    @Override
    public void setEnemyRewardScore (int enemyID, double enemyRewardPoints) {
        getTypeManager().getEntity(enemyID).setScore(enemyRewardPoints);
    }

    @Override
    public void setEnemyCollisionEffect (int enemyID, String enemyCollisionEffect) {
        getTypeManager().getEntity(enemyID).setCollisionEffect(enemyCollisionEffect);
    }

    @Override
    public double getEnemySpeed (int enemyID) {
        return getTypeManager().getEntity(enemyID).getSpeed();
    }

    @Override
    public double getEnemyHealth (int enemyID) {
        return getTypeManager().getEntity(enemyID).getHealth();
    }

    @Override
    public double getEnemyDamage (int enemyID) {
        return getTypeManager().getEntity(enemyID).getDamage();
    }

    @Override
    public double getEnemyRewardMoney (int enemyID) {
        return getTypeManager().getEntity(enemyID).getMoney();
    }

    @Override
    public double getEnemyRewardScore (int enemyID) {
        return getTypeManager().getEntity(enemyID).getScore();
    }

    @Override
    public String getEnemyCollisionEffect (int enemyID) {
        return getTypeManager().getEntity(enemyID).getCollisionEffect();
    }

    @Override
    protected EnemyBuilder constructTypeProperties (EnemyUpdateView updateView,
                                                    EnemyBuilder typeBuilder) {
        return typeBuilder
                .addDamageListener( (oldValue, newValue) -> updateView.updateEnemyDamage(newValue))
                .addHealthListener( (oldValue, newValue) -> updateView
                        .updateEnemyHealthDisplay(newValue))
                .addMoneyListener( (oldValue, newValue) -> updateView
                        .updateEnemyRewardMoney(newValue))
                .addScoreListener( (oldValue, newValue) -> updateView
                        .updateEnemyRewardPoints(newValue))
                .addSpeedListener( (oldValue, newValue) -> updateView.updateEnemySpeed(newValue));

    }

    @Override
    public EffectManagerController getEffectManagerController () {
        return enemyEffectManagerController;
    }

}
