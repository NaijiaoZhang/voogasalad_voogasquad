package authoring.editorview.weapon.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.editorview.weapon.WeaponSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class DeleteWeapon implements WeaponSetView {

    private WeaponAuthoringViewDelegate delegate;
    private Button deleteWeaponButton;

    public DeleteWeapon (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return deleteWeaponButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        deleteWeaponButton =
                ButtonFactory.makeButton(labelsResource.getString("Delete"),
                                         e -> delegate.onUserPressedDeleteWeapon());
        deleteWeaponButton.setPrefWidth(280);
    }

}
