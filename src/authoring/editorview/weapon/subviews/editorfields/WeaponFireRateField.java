package authoring.editorview.weapon.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.weapon.WeaponSetView;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WeaponFireRateField implements WeaponSetView {

    private GridPane root;
    private TextField weaponFireRateField;
    private WeaponAuthoringViewDelegate delegate;

    public WeaponFireRateField (ResourceBundle labelsResource) {
        createField(labelsResource);
    }

    private void createField (ResourceBundle labelsResource) {
        weaponFireRateField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"), e -> delegate
                        .onUserEnteredWeaponFireRate(weaponFireRateField.getText()));
        weaponFireRateField.setPrefWidth(130);
        root = GridFactory.createRowWithLabelandNode(
                                                     labelsResource.getString("FireRate"),
                                                     weaponFireRateField,
                                                     150);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public void updateWeaponFireRate (String weaponFireRate) {
        weaponFireRateField.setText(weaponFireRate);
    }

}
