package authoring.editorview;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * 
 * @author Kayla Schulz
 *
 */
public abstract class TextFieldView implements INodeView {

    protected GridPane root;

    protected ResourceBundle resource;

    public TextFieldView (ResourceBundle resource) {
        this.resource = resource;
        makeTextField(resource);
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public abstract void updateField (String newData);

    protected abstract void makeTextField (ResourceBundle labelsResource);

}
