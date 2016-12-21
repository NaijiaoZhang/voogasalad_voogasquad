package authoring.editorview.weapon.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.EditorNameView;
import authoring.editorview.weapon.WeaponSetView;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WeaponNameField extends EditorNameView implements WeaponSetView {

    private WeaponAuthoringViewDelegate delegate;

    public WeaponNameField (ResourceBundle labelsResource) {
        super(labelsResource);
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField =
                TextFieldFactory.makeTextField(resource.getString("EnterString"),
                                               e -> delegate.onUserEnteredWeaponName(nameTextField
                                                       .getText()));
        nameTextField.setPrefWidth(130);
        root =
                GridFactory.createRowWithLabelandNode(resource.getString("NameTextField"),
                                                      nameTextField, 150);

    }

}
