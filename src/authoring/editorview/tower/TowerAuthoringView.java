package authoring.editorview.tower;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ListDataSource;
import authoring.editorview.tower.subviews.TowerEditorView;
import authoring.editorview.tower.subviews.TowerImageBank;
import authoring.editorview.tower.subviews.editorfields.AddTowerEffectView;
import authoring.editorview.tower.subviews.editorfields.DeleteTower;
import authoring.editorview.tower.subviews.editorfields.TowerAbilityBank;
import authoring.editorview.tower.subviews.editorfields.TowerBuyPriceField;
import authoring.editorview.tower.subviews.editorfields.TowerWeaponBank;
import authoring.editorview.tower.subviews.editorfields.TowerImageView;
import authoring.editorview.tower.subviews.editorfields.TowerNameField;
import authoring.editorview.tower.subviews.editorfields.TowerSellPriceField;
import authoring.editorview.tower.subviews.editorfields.TowerSizeField;
import authoring.editorview.tower.subviews.editorfields.TowerUnlockLevelField;
import authoring.editorview.tower.subviews.editorfields.TowerUpgradeBank;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * @author Kayla Schulz
 * @author Diane Hadley
 *
 */
public class TowerAuthoringView implements TowerUpdateView {
    @SuppressWarnings("unused")
    private TowerAuthoringViewDelegate delegate;
    private GridPane towerView;
    private TowerImageBank towerBank;
    private TowerNameField towerName;
    private TowerEditorView towerEditorView;
    private TowerImageView towerImage;
    private TowerBuyPriceField towerBuyPrice;
    private TowerSellPriceField towerSellPrice;
    private TowerUnlockLevelField towerUnlockLevel;
    private TowerAbilityBank towerAbility;
    private TowerWeaponBank towerWeaponBank;
    private TowerUpgradeBank towerUpgradeBank;
    private TowerSizeField towerSize;
    private AddTowerEffectView addTowerEffect;
    private DeleteTower deleteTower;

    private ResourceBundle labelsResource =
            ResourceBundle.getBundle("resources/GameAuthoringTower");
    private ResourceBundle dialogueBoxResource = ResourceBundle.getBundle("resources/DialogueBox");

    public TowerAuthoringView () {
        towerView = new GridPane();

        towerName = new TowerNameField(labelsResource);
        towerImage = new TowerImageView(labelsResource);
        towerBuyPrice = new TowerBuyPriceField(labelsResource);
        towerSellPrice = new TowerSellPriceField(labelsResource);
        towerUnlockLevel = new TowerUnlockLevelField(labelsResource);
        towerAbility = new TowerAbilityBank(labelsResource);
        towerWeaponBank = new TowerWeaponBank(labelsResource);
        towerUpgradeBank = new TowerUpgradeBank(labelsResource);
        towerSize = new TowerSizeField(labelsResource);
        addTowerEffect = new AddTowerEffectView(labelsResource);
        deleteTower = new DeleteTower(labelsResource);

        towerBank = new TowerImageBank();
        towerEditorView =
                new TowerEditorView(towerName, towerImage, towerBuyPrice, towerSellPrice,
                                    towerUnlockLevel, towerAbility, towerWeaponBank,
                                    towerUpgradeBank,
                                    towerSize, addTowerEffect, deleteTower,
                                    labelsResource, dialogueBoxResource);
        buildView();
    }

    private void buildView () {

        ColumnConstraints bankColumn = new ColumnConstraints();
        bankColumn.setMinWidth(150);

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setPrefWidth(400);

        ColumnConstraints previewColumn = new ColumnConstraints();

        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(700);

        towerView.getColumnConstraints().addAll(bankColumn, editorColumn, previewColumn);
        towerView.getRowConstraints().add(fullRow);

        towerView.add(towerBank.getInstanceAsNode(), 0, 0);
        towerView.add(towerEditorView.getInstanceAsNode(), 1, 0);
        towerView.add(towerImage.getInstanceAsNode(), 2, 0);
    }

    @Override
    public Node getInstanceAsNode () {
        return towerView;
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
        towerEditorView.setDelegate(delegate);
        towerBank.setDelegate(delegate);
        towerName.setDelegate(delegate);
        towerImage.setDelegate(delegate);
        towerBuyPrice.setDelegate(delegate);
        towerSellPrice.setDelegate(delegate);
        towerUnlockLevel.setDelegate(delegate);
        towerAbility.setDelegate(delegate);
        towerWeaponBank.setDelegate(delegate);
        towerUpgradeBank.setDelegate(delegate);
        towerSize.setDelegate(delegate);
        addTowerEffect.setDelegate(delegate);
        deleteTower.setDelegate(delegate);
    }

    @Override
    public void updateUnlockLevelDisplay (int towerLevel) {
        this.towerUnlockLevel.updateTowerUnlockLevel(Integer.toString(towerLevel));
    }

    @Override
    public void updateTowerBuyPriceDisplay (double towerBuyPrice) {
        this.towerBuyPrice.updateTowerBuyPrice(Double.toString(towerBuyPrice));
    }

    @Override
    public void updateTowerSellPriceDisplay (double towerSellPrice) {
        this.towerSellPrice.updateTowerSellPrice(Double.toString(towerSellPrice));
    }

    @Override
    public void updateTowerAbilityBank (List<Integer> towerAbility) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTowerUpgradeBank (List<Integer> towerUpgrades) {
        this.towerUpgradeBank.updateBank(towerUpgrades);
    }

    @Override
    public void updateTowerBank (List<Integer> createdTowers) {
        towerBank.updateTowerBank(createdTowers);
    }

    @Override
    public void updateNameDisplay (String name) {
        this.towerName.updateName(name);
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        this.towerImage.updateTowerImagePath(imagePath);
        this.towerBank.updateBank();
    }

    @Override
    public void updateSizeDisplay (double size) {
        this.towerSize.updateTowerSize(Double.toString(size));

    }

    @Override
    public void updateTowerWeaponBank (List<Integer> newValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteTower () {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBank (List<Integer> ids) {
        this.towerBank.updateBank(ids);
    }

    @Override
    public void setTowerListDataSource (ListDataSource source) {
        this.towerBank.setListDataSource(source);
        this.towerUpgradeBank.setListDataSource(source);
    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        int currentIndex = this.towerBank.getIndexForItemWithID(id);
        Integer nearestID = this.towerBank.getIDForItemAtIndex(currentIndex - 1);
        if (nearestID == null) {
            nearestID = this.towerBank.getIDForItemAtIndex(currentIndex + 1);
        }
        return nearestID;
    }

    @Override
    public void setWeaponOptions (List<String> weaponOptions) {
        towerWeaponBank.setOptions(weaponOptions);
    }
}
