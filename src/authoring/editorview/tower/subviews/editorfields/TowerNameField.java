package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.EditorNameView;
import authoring.editorview.tower.TowerSetView;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TowerNameField extends EditorNameView implements TowerSetView {

    private TowerAuthoringViewDelegate delegate;

    public TowerNameField (ResourceBundle labelsResource) {
        super(labelsResource);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField =
                TextFieldFactory.makeTextField(resource.getString("EnterString"),
                                               e -> delegate.onUserEnteredTowerName(nameTextField
                                                       .getText()));
        nameTextField.setPrefWidth(230);
        root =
                GridFactory.createRowWithLabelandNode(resource.getString("NameTextField"),
                                                      nameTextField, 150);

    }

}
