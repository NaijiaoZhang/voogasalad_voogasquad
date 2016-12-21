package authoring.editorview.collisioneffects.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.EditorNameView;
import authoring.editorview.collisioneffects.EffectAuthoringViewDelegate;
import authoring.editorview.collisioneffects.EffectSetView;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;

/**
 * 
 * @author Kayla Schulz
 *
 */
public class EffectNameField extends EditorNameView implements EffectSetView {

    private EffectAuthoringViewDelegate delegate;

    public EffectNameField (ResourceBundle labelsResource) {
        super(labelsResource);
        nameTextField.setPrefWidth(500);
    }

    @Override
    public void setDelegate (EffectAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField =
                TextFieldFactory.makeTextField(resource.getString("EnterString"),
                                               e -> delegate.onUserEnteredEffectName(nameTextField
                                                       .getText()));
        nameTextField.setPrefWidth(105);
        root = GridFactory.createRowWithLabelandNode(resource.getString("NameTextField"),
                                                     nameTextField, 125);
    }

}
