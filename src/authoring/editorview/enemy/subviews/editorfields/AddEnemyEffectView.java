package authoring.editorview.enemy.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.editorview.enemy.EnemySetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class AddEnemyEffectView implements EnemySetView {

    private EnemyAuthoringViewDelegate delegate;
    private Button addEnemyEffectButton;

    public AddEnemyEffectView (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return addEnemyEffectButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        addEnemyEffectButton =
                ButtonFactory.makeButton(labelsResource.getString("AddEffect"),
                                         e -> delegate.onUserPressedAddEffect());
        addEnemyEffectButton.setPrefWidth(280);
    }

}
