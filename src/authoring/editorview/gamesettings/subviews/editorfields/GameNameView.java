package authoring.editorview.gamesettings.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.EditorNameView;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;


/**
 * 
 * @author Diane Hadley
 * @author Kayla Schulz
 *
 */

public class GameNameView extends EditorNameView implements GameSettingsSetView {

    private GameSettingsAuthoringViewDelegate delegate;

    public GameNameView (ResourceBundle settingsResource) {
        super(settingsResource);

    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void makeNameTextField () {
        nameTextField =
                TextFieldFactory.makeTextField("", e -> delegate
                        .onUserEnteredGameName(nameTextField.getText()));
        nameTextField.setMaxWidth(105);

        root =
                GridFactory.createRowWithLabelandNode(resource.getString("GameName"), nameTextField,
                                                      125);

    }

}
