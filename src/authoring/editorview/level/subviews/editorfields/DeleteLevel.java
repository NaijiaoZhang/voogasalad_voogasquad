package authoring.editorview.level.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.editorview.level.LevelSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class DeleteLevel implements LevelSetView {

    private LevelAuthoringViewDelegate delegate;
    private Button deleteLevelButton;

    public DeleteLevel (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return deleteLevelButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        deleteLevelButton =
                ButtonFactory.makeButton(labelsResource.getString("Delete"),
                                         e -> delegate.onUserPressedDeleteLevel());
        deleteLevelButton.setPrefWidth(280);
    }

}
