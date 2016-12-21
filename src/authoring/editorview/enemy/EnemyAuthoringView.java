package authoring.editorview.enemy;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ListDataSource;
import authoring.editorview.enemy.subviews.EnemyEditorView;
import authoring.editorview.enemy.subviews.EnemyImageBank;
import authoring.editorview.enemy.subviews.editorfields.EnemyHealthField;
import authoring.editorview.enemy.subviews.editorfields.EnemyImageView;
import authoring.editorview.enemy.subviews.editorfields.EnemyNameField;
import authoring.editorview.enemy.subviews.editorfields.EnemyRewardMoneyField;
import authoring.editorview.enemy.subviews.editorfields.EnemyRewardPointsField;
import authoring.editorview.enemy.subviews.editorfields.EnemySizeField;
import authoring.editorview.enemy.subviews.editorfields.AddEnemyEffectView;
import authoring.editorview.enemy.subviews.editorfields.DeleteEnemy;
import authoring.editorview.enemy.subviews.editorfields.EnemyDamageField;
import authoring.editorview.enemy.subviews.editorfields.EnemySpeedField;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * @author Kayla Schulz
 * @author Diane Hadley
 * 
 */

public class EnemyAuthoringView implements EnemyUpdateView {
    @SuppressWarnings("unused")
    private EnemyAuthoringViewDelegate delegate;
    private GridPane enemyView;
    private EnemyImageBank enemyBank;
    private EnemyNameField enemyName;
    private EnemySpeedField enemySpeed;
    private EnemyImageView enemyImage;
    private EnemyEditorView enemyEditorView;
    private EnemyDamageField enemyDamage;
    private EnemyHealthField enemyHealth;
    private EnemyRewardMoneyField enemyRewardMoney;
    private EnemyRewardPointsField enemyRewardPoints;
    private EnemySizeField enemySize;
    private DeleteEnemy deleteEnemy;
    private AddEnemyEffectView addEnemyEffect;

    public EnemyAuthoringView () {
        String ENEMY_EFFECT_RESOURCE_PATH = "resources/GameAuthoringEnemy";
        ResourceBundle labelsResource = ResourceBundle.getBundle(ENEMY_EFFECT_RESOURCE_PATH);

        enemyView = new GridPane();
        enemyBank = new EnemyImageBank();
        enemyName = new EnemyNameField(labelsResource);
        enemySpeed = new EnemySpeedField(labelsResource);
        enemyImage = new EnemyImageView(labelsResource);
        enemyDamage = new EnemyDamageField(labelsResource);
        enemyHealth = new EnemyHealthField(labelsResource);
        enemyRewardMoney = new EnemyRewardMoneyField(labelsResource);
        enemyRewardPoints = new EnemyRewardPointsField(labelsResource);
        enemySize = new EnemySizeField(labelsResource);
        deleteEnemy = new DeleteEnemy(labelsResource);
        addEnemyEffect = new AddEnemyEffectView(labelsResource);
        enemyEditorView =
                new EnemyEditorView(enemyImage, enemyName,
                                    enemySpeed, enemyDamage, enemyHealth, enemyRewardMoney,
                                    enemyRewardPoints, enemySize, deleteEnemy, addEnemyEffect);
        buildView();
    }

    private void buildView () {

        ColumnConstraints bankColumn = new ColumnConstraints();
        bankColumn.setMinWidth(150);

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setPrefWidth(300);

        ColumnConstraints previewColumn = new ColumnConstraints();

        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(700);

        enemyView.getColumnConstraints().addAll(bankColumn, editorColumn, previewColumn);
        enemyView.getRowConstraints().add(fullRow);

        enemyView.add(enemyBank.getInstanceAsNode(), 0, 0);
        enemyView.add(enemyEditorView.getInstanceAsNode(), 1, 0);
        enemyView.add(enemyImage.getInstanceAsNode(), 2, 0);
    }

    @Override
    public Node getInstanceAsNode () {
        return enemyView;
    }

    @Override
    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
        enemyBank.setDelegate(delegate);
        enemyEditorView.setDelegate(delegate);
        enemyName.setDelegate(delegate);
        enemySpeed.setDelegate(delegate);
        enemyImage.setDelegate(delegate);
        enemyDamage.setDelegate(delegate);
        enemyHealth.setDelegate(delegate);
        enemyRewardMoney.setDelegate(delegate);
        enemyRewardPoints.setDelegate(delegate);
        enemySize.setDelegate(delegate);
        deleteEnemy.setDelegate(delegate);
        addEnemyEffect.setDelegate(delegate);
    }

    @Override
    public void updateEnemySpeed (double speed) {
        this.enemySpeed.updateField(Double.toString(speed));
    }

    @Override
    public void updateEnemyBank (List<Integer> activeEnemies) {
        enemyBank.updateEnemyBank(activeEnemies);
    }

    @Override
    public void createNewEnemy () {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateEnemyHealthDisplay (double enemyHealth) {
        this.enemyHealth.updateField(Double.toString(enemyHealth));
    }

    @Override
    public void updateEnemyDamage (double damage) {
        this.enemyDamage.updateField(Double.toString(damage));
    }

    @Override
    public void updateEnemyRewardMoney (double rewardMoney) {
        this.enemyRewardMoney.updateField(Double.toString(rewardMoney));
    }

    @Override
    public void updateEnemyRewardPoints (double rewardPoints) {
        this.enemyRewardPoints.updateField(Double.toString(rewardPoints));
    }

    @Override
    public void updateNameDisplay (String name) {
        this.enemyName.updateName(name);
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        this.enemyImage.updateEnemyImagePath(imagePath);
        this.enemyBank.updateBank();
    }

    @Override
    public void updateSizeDisplay (double size) {
        this.enemySize.updateField(Double.toString(size));
    }

    @Override
    public void setEnemyListDataSource (ListDataSource source) {
        this.enemyBank.setListDataSource(source);
    }

    @Override
    public void deleteEnemy () {
        enemyEditorView.clearView();
        //System.out.println("Getting here in enemy authoring view");
    }

    @Override
    public void updateBank (List<Integer> ids) {
        this.enemyBank.updateBank(ids);
        //System.out.println(ids.size());
        // enemyEffectView.clearView();
    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        int currentIndex = this.enemyBank.getIndexForItemWithID(id);
        Integer nearestID = this.enemyBank.getIDForItemAtIndex(currentIndex - 1);
        if (nearestID == null) {
            nearestID = this.enemyBank.getIDForItemAtIndex(currentIndex + 1);
        }
        return nearestID;
    }

}
