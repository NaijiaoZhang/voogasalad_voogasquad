package authoring.editorview.enemy;

import java.util.ResourceBundle;
import authoring.editorview.EditorViewController;
import authoring.editorview.ListCellData;
import authoring.editorview.ListDataSource;
import authoring.editorview.collisioneffects.EffectAuthoringViewController;
import authoring.utilityfactories.DialogueBoxFactory;
import engine.effect.EffectManagerController;
import engine.enemy.*;


/**
 * 
 * @author Andrew Bihl
 * @author Kayla Schulz
 *
 */
public class EnemyAuthoringViewController extends EditorViewController
        implements EnemyAuthoringViewDelegate, ListDataSource {

    private EnemyManagerController enemyDataSource;
    private EffectManagerController effectDataSource;
    private int currentEnemyID;
    private EnemyUpdateView enemyView;

    public EnemyAuthoringViewController (int editorWidth, int editorHeight) {
        enemyView = EnemyAuthoringViewFactory.build(editorWidth, editorHeight);
        enemyView.setDelegate(this);
        enemyView.setEnemyListDataSource(this);
        this.view = enemyView;
    }

    public void setEnemyDataSource (EnemyManagerController source) {
        this.enemyDataSource = source;
        this.enemyDataSource.addTypeBankListener(this.enemyView);
        effectDataSource = enemyDataSource.getEffectManagerController();
        onUserPressedCreateEnemy();
    }

    public void refreshView () {
        enemyView.updateImagePathDisplay(enemyDataSource.getImagePath(currentEnemyID));
        enemyView.updateNameDisplay(enemyDataSource.getName(currentEnemyID));
        enemyView.updateSizeDisplay(enemyDataSource.getSize(currentEnemyID));
        enemyView.updateEnemyDamage(enemyDataSource.getEnemyDamage(currentEnemyID));
        enemyView.updateEnemySpeed(enemyDataSource.getEnemySpeed(currentEnemyID));
        enemyView.updateEnemyRewardMoney(enemyDataSource.getEnemyRewardMoney(currentEnemyID));
        enemyView.updateEnemyRewardPoints(enemyDataSource.getEnemyRewardScore(currentEnemyID));
        enemyView.updateEnemyHealthDisplay(enemyDataSource.getEnemyHealth(currentEnemyID));
    }

    @Override
    public void onUserPressedCreateEnemy () {
        currentEnemyID = enemyDataSource.createType(enemyView);
        refreshView();
    }

    @Override
    public void onUserEnteredEnemySpeed (String enemySpeed) {
        try {
            Double.parseDouble(enemySpeed);
            enemyDataSource.setEnemySpeed(currentEnemyID, Double.parseDouble(enemySpeed));

        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredEnemyHealth (String enemyHealth) {
        try {
            Double.parseDouble(enemyHealth);
            enemyDataSource.setEnemyHealth(currentEnemyID, Double.parseDouble(enemyHealth));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }

    }

    @Override
    public void onUserEnteredEnemyDamage (String enemyDamage) {
        try {
            Double.parseDouble(enemyDamage);
            enemyDataSource.setEnemyDamage(currentEnemyID, Double.parseDouble(enemyDamage));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredEnemyPoints (String enemyRewardPoints) {
        try {
            Double.parseDouble(enemyRewardPoints);
            enemyDataSource.setEnemyRewardScore(currentEnemyID,
                                                Double.parseDouble(enemyRewardPoints));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredEnemyMoney (String enemyRewardMoney) {
        try {
            Double.parseDouble(enemyRewardMoney);
            enemyDataSource.setEnemyRewardMoney(currentEnemyID,
                                                Double.parseDouble(enemyRewardMoney));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredEnemyImagePath (String enemyImagePath) {
        enemyDataSource.setImagePath(currentEnemyID, enemyImagePath);
    }

    @Override
    public void onUserEnteredEnemyName (String enemyName) {
        enemyDataSource.setName(currentEnemyID, enemyName);
    }

    private void createDialogueBox () {
        ResourceBundle dialogueBoxResource = ResourceBundle.getBundle("resources/DialogueBox");
        DialogueBoxFactory.createErrorDialogueBox(dialogueBoxResource.getString("Integer"),
                                                  dialogueBoxResource.getString("CheckInput"));
    }

    @Override
    public void onUserPressedDeleteEnemy () {
        int nextID = this.enemyView.getNearestAvailableItemID(currentEnemyID);
        enemyDataSource.deleteType(currentEnemyID);
        currentEnemyID = nextID;
        this.refreshView();
    }

    @Override
    public void onUserEnteredEnemySize (String enemySize) {
        try {
            Double.parseDouble(enemySize);
            enemyDataSource.setSize(currentEnemyID,
                                    Double.parseDouble(enemySize));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public ListCellData getCellDataForSubject (int enemyID) {
        ListCellData cellData = new ListCellData();
        cellData.setName(enemyDataSource.getName(enemyID));
        cellData.setImagePath(enemyDataSource.getImagePath(enemyID));
        cellData.setId(enemyID);
        return cellData;
    }

    @Override
    public void onUserSelectedEnemy (int enemyID) {
        currentEnemyID = enemyID;
        refreshView();
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

}
