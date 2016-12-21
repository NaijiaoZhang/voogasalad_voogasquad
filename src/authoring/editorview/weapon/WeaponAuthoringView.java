package authoring.editorview.weapon;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ListDataSource;
import authoring.editorview.weapon.subviews.WeaponEditorView;
import authoring.editorview.weapon.subviews.WeaponImageBank;
import authoring.editorview.weapon.subviews.editorfields.AddWeaponEffectView;
import authoring.editorview.weapon.subviews.editorfields.DeleteWeapon;
import authoring.editorview.weapon.subviews.editorfields.WeaponFireRateField;
import authoring.editorview.weapon.subviews.editorfields.WeaponImageView;
import authoring.editorview.weapon.subviews.editorfields.WeaponNameField;
import authoring.editorview.weapon.subviews.editorfields.WeaponPathField;
import authoring.editorview.weapon.subviews.editorfields.WeaponRangeField;
import authoring.editorview.weapon.subviews.editorfields.WeaponSizeField;
import authoring.editorview.weapon.subviews.editorfields.WeaponSpeedField;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WeaponAuthoringView implements WeaponUpdateView {
    private WeaponAuthoringViewDelegate delegate;
    private GridPane weaponView;
    private WeaponImageBank weaponBank;
    private WeaponEditorView weaponEditorView;
    private WeaponNameField weaponNameView;
    private WeaponSpeedField weaponSpeedView;
    private WeaponFireRateField weaponFireRateView;
    private WeaponRangeField weaponRangeView;
    private WeaponPathField weaponPathView;
    private WeaponImageView weaponImageView;
    private WeaponSizeField weaponSizeView;
    private AddWeaponEffectView addWeaponEffect;
    private DeleteWeapon deleteWeapon;

    private static final int EDITOR_WIDTH = 300;

    private int width;
    private int height;

    public WeaponAuthoringView (int width, int height) throws IOException {
        ResourceBundle labelsResource = ResourceBundle.getBundle("resources/GameAuthoringWeapon");
        ResourceBundle dialogueBoxResource = ResourceBundle.getBundle("resources/DialogueBox");

        this.width = width;
        this.height = height;

        weaponView = new GridPane();

        weaponBank = new WeaponImageBank(labelsResource);
        weaponNameView = new WeaponNameField(labelsResource);
        weaponSpeedView = new WeaponSpeedField(labelsResource);
        weaponRangeView = new WeaponRangeField(labelsResource);
        weaponFireRateView = new WeaponFireRateField(labelsResource);
        weaponPathView = new WeaponPathField(labelsResource);
        weaponImageView = new WeaponImageView(labelsResource);
        weaponSizeView = new WeaponSizeField(labelsResource);
        deleteWeapon = new DeleteWeapon(labelsResource);

        addWeaponEffect = new AddWeaponEffectView(labelsResource);
        weaponEditorView =

                new WeaponEditorView(weaponNameView, weaponSpeedView, weaponFireRateView,
                                     weaponRangeView,
                                     weaponPathView, weaponImageView, weaponSizeView,
                                     addWeaponEffect, deleteWeapon, labelsResource,
                                     dialogueBoxResource,
                                     EDITOR_WIDTH);
        buildView();
    }

    private void buildView () {

        ColumnConstraints bankColumn = new ColumnConstraints();
        bankColumn.setMinWidth(150);

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setPrefWidth(EDITOR_WIDTH);

        ColumnConstraints previewColumn = new ColumnConstraints();

        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(700);

        weaponView.getColumnConstraints().addAll(bankColumn, editorColumn, previewColumn);
        weaponView.getRowConstraints().add(fullRow);

        weaponView.add(weaponBank.getInstanceAsNode(), 0, 0);
        weaponView.add(weaponEditorView.getInstanceAsNode(), 1, 0);
        weaponView.add(weaponImageView.getInstanceAsNode(), 2, 0);
    }

    @Override
    public Node getInstanceAsNode () {
        return weaponView;
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
        weaponBank.setDelegate(delegate);
        weaponNameView.setDelegate(delegate);
        weaponSpeedView.setDelegate(delegate);
        weaponFireRateView.setDelegate(delegate);
        weaponRangeView.setDelegate(delegate);
        weaponEditorView.setDelegate(delegate);
        weaponPathView.setDelegate(delegate);
        weaponSizeView.setDelegate(delegate);
        addWeaponEffect.setDelegate(delegate);
        deleteWeapon.setDelegate(delegate);
    }

    @Override
    public void updateFireRateDisplay (double fireRate) {
        this.weaponFireRateView.updateWeaponFireRate(Double.toString(fireRate));
    }

    @Override
    public void updateSpeedDisplay (double speed) {
        this.weaponSpeedView.updateWeaponSpeed(Double.toString(speed));
    }

    @Override
    public void updateRangeDisplay (double range) {
        this.weaponRangeView.updateWeaponRange(Double.toString(range));
    }

    @Override
    public void updateWeaponBank (List<Integer> activeWeapons) {
        this.weaponBank.updateWeaponBank(activeWeapons);
    }

    @Override
    public void updateWeaponTrajectory (String path) {
        this.weaponPathView.updateWeaponPath(path);
    }

    @Override
    public void createNewWeapon () {
        // TODO Auto-generated method stub
        // This will set all default values for the weapon

    }

    @Override
    public void updateTargetEnemies (List<Integer> targetEnemies) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateNameDisplay (String name) {
        this.weaponNameView.updateName(name);

    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        this.weaponBank.updateBank();
        this.weaponImageView.updateWeaponImagePath(imagePath);
    }

    @Override
    public void updateSizeDisplay (double size) {
        this.weaponSizeView.updateWeaponSize(Double.toString(size));
    }

    @Override
    public void deleteWeapon () {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBank (List<Integer> ids) {
        this.weaponBank.updateBank(ids);
    }

    @Override
    public void setWeaponListDataSource (ListDataSource source) {
        // TODO Auto-generated method stub
        this.weaponBank.setListDataSource(source);
    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        int currentIndex = this.weaponBank.getIndexForItemWithID(id);
        Integer nearestID = this.weaponBank.getIDForItemAtIndex(currentIndex - 1);
        if (nearestID == null) {
            nearestID = this.weaponBank.getIDForItemAtIndex(currentIndex + 1);
        }
        return nearestID;
    }

}
