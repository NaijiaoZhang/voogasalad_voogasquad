package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.editorview.tower.TowerSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class DeleteTower implements TowerSetView {
    private TowerAuthoringViewDelegate delegate;
    private Button deleteTowerButton;

    public DeleteTower (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return deleteTowerButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        deleteTowerButton =
                ButtonFactory.makeButton(labelsResource.getString("Delete"),
                                         e -> delegate.onUserPressedDeleteTower());
        deleteTowerButton.setPrefWidth(280);
    }

}
