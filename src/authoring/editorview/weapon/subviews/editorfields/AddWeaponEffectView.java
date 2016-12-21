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
public class AddWeaponEffectView implements WeaponSetView {

    private WeaponAuthoringViewDelegate delegate;
    private Button addWeaponEffectButton;

    public AddWeaponEffectView (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return addWeaponEffectButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        addWeaponEffectButton =
                ButtonFactory.makeButton(labelsResource.getString("AddEffect"),
                                         e -> delegate.onUserPressedAddEffect());
        addWeaponEffectButton.setPrefWidth(280);
    }

}
