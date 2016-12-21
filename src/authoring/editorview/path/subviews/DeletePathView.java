package authoring.editorview.path.subviews;

import java.util.ResourceBundle;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class DeletePathView implements PathSetView {

    // TODO: similar to create path

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringPath";
    private ResourceBundle pathResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    private PathAuthoringViewDelegate delegate;
    private VBox root;

    public DeletePathView () {
        this.root = new VBox();
        buildViewComponents();
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void buildViewComponents () {
        Button deletePathButton =
                ButtonFactory.makeButton(pathResource.getString("DeletePathButton"),
                                         e -> {
                                             deletePath();
                                         });
        deletePathButton.setPrefWidth(280);
        root.getChildren().add(deletePathButton);
        deletePathButton.setFocusTraversable(false);
    }

    private void deletePath () {
        delegate.onUserEnteredDeletePath();

    }

}
