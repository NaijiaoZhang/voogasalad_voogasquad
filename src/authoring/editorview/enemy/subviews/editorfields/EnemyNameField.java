package authoring.editorview.enemy.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.EditorNameView;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.editorview.enemy.EnemySetView;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class EnemyNameField extends EditorNameView implements EnemySetView {

    private EnemyAuthoringViewDelegate delegate;

    public EnemyNameField (ResourceBundle labelsResource) {
        super(labelsResource);
    }

    @Override
    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField =
                TextFieldFactory.makeTextField(resource.getString("EnterString"),
                                               e -> delegate.onUserEnteredEnemyName(nameTextField
                                                       .getText()));
        nameTextField.setPrefWidth(110);
        root = GridFactory.createRowWithLabelandNode(resource.getString("NameTextField"),
                                                     nameTextField, 170);
    }

}
