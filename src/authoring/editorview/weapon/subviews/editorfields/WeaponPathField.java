package authoring.editorview.weapon.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.weapon.WeaponSetView;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.utilityfactories.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WeaponPathField implements WeaponSetView {

    private ComboBox<Object> weaponPathBox;
    private WeaponAuthoringViewDelegate delegate;
    private ResourceBundle labelsResource;

    public WeaponPathField (ResourceBundle labelsResource) {
        this.labelsResource = labelsResource;
        ObservableList<Object> pathOptions = setList();
        createBox(pathOptions);
    }

    private ObservableList<Object> setList () {
        ObservableList<Object> pathOptions =
                FXCollections.observableArrayList("Straight", "Heat Follow");
        return pathOptions;
    }

    private void createBox (ObservableList<Object> pathOptions) {
        weaponPathBox =
                ComboBoxFactory.makeComboBox(labelsResource.getString("Path"), e -> delegate
                        .onUserEnteredWeaponTrajectory((String) weaponPathBox.getValue()),
                                             pathOptions);
        weaponPathBox.setPrefWidth(280);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return weaponPathBox;
    }

    public void updateWeaponPath (String weaponPath) {
        weaponPathBox.setValue(weaponPath);
    }

}
