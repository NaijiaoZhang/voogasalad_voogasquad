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
public class WeaponRangeField implements WeaponSetView {

    private GridPane root;
    private TextField weaponRangeField;
    private WeaponAuthoringViewDelegate delegate;

    public WeaponRangeField (ResourceBundle labelsResource) {
        createField(labelsResource);
    }

    private void createField (ResourceBundle labelsResource) {
        weaponRangeField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"), e -> delegate
                        .onUserEnteredWeaponRange(weaponRangeField.getText()));
        weaponRangeField.setPrefWidth(130);
        root = GridFactory.createRowWithLabelandNode(
                                                     labelsResource.getString("Range"),
                                                     weaponRangeField,
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

    public void updateWeaponRange (String weaponRange) {
        weaponRangeField.setText(weaponRange);
    }

}
