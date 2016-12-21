package authoring.editorview.enemy.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.editorview.enemy.EnemySetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class DeleteEnemy implements EnemySetView {

    private EnemyAuthoringViewDelegate delegate;
    private Button deleteEnemyButton;

    public DeleteEnemy (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return deleteEnemyButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        deleteEnemyButton =
                ButtonFactory.makeButton(labelsResource.getString("Delete"),
                                         e -> delegate.onUserPressedDeleteEnemy());
        deleteEnemyButton.setPrefWidth(280);
    }

}
