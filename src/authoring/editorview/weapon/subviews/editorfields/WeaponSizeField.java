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
 * @author Diane Hadley
 *
 */
public class WeaponSizeField implements WeaponSetView {

    private GridPane root;
    private TextField weaponSizeField;
    private WeaponAuthoringViewDelegate delegate;

    public WeaponSizeField (ResourceBundle labelsResource) {
        createField(labelsResource);
    }

    private void createField (ResourceBundle labelsResource) {
        weaponSizeField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"), e -> delegate
                        .onUserEnteredWeaponSize(weaponSizeField.getText()));
        weaponSizeField.setPrefWidth(130);
        root =
                GridFactory.createRowWithLabelandNode(labelsResource.getString("Size"),
                                                      weaponSizeField, 150);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public void updateWeaponSize (String weaponSize) {
        weaponSizeField.setText(weaponSize);
    }

}
