package authoring.editorview.path.subviews;

import java.util.ResourceBundle;
import authoring.editorview.INodeView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class PathInstructionsView implements INodeView {

    private VBox root;

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringPath";
    private ResourceBundle pathResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    public PathInstructionsView () {
        this.root = new VBox();
        addInstructionsText();

    }

    public Node getInstanceAsNode () {
        return root;

    }

    private void addInstructionsText () {
        Text instructions = new Text(pathResource.getString("PathBuilderInstructions"));
        instructions.setWrappingWidth(450);
        root.getChildren().add(instructions);
    }

}
