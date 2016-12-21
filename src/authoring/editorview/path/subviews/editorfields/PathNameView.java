package authoring.editorview.path.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.path.PathSetView;
import authoring.editorview.EditorNameView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;


public class PathNameView extends EditorNameView implements PathSetView {

    private PathAuthoringViewDelegate delegate;

    public PathNameView (ResourceBundle pathResource) {
        super(pathResource);
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField = TextFieldFactory.makeTextField("",
                                                       e -> delegate
                                                               .onUserEnteredPathName(nameTextField
                                                                       .getText()));
        nameTextField.setMaxWidth(155);
        root =
                GridFactory.createRowWithLabelandNode(resource.getString("NameTextField"),
                                                      nameTextField, 125);

    }

}
