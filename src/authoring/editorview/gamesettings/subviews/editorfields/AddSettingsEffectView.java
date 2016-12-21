package authoring.editorview.gamesettings.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class AddSettingsEffectView implements GameSettingsSetView {

    private GameSettingsAuthoringViewDelegate delegate;
    private Button addSettingsEffectButton;

    public AddSettingsEffectView (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return addSettingsEffectButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        addSettingsEffectButton =
                ButtonFactory.makeButton(labelsResource.getString("AddEffect"),
                                         e -> delegate.onUserPressedAddEffect());
        addSettingsEffectButton.setPrefWidth(230);
    }
    
}
