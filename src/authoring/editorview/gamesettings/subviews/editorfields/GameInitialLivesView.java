package authoring.editorview.gamesettings.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.DialogueBoxFactory;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class GameInitialLivesView implements GameSettingsSetView {

    private TextField initialLivesField;
    private int initialLives;
    private GameSettingsAuthoringViewDelegate delegate;
    private GridPane root;
    private ResourceBundle settingsResource;

    public GameInitialLivesView (ResourceBundle resource) {
        this.settingsResource = resource;
        createField();
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void createField () {
        initialLivesField = TextFieldFactory.makeTextField("",
                                                           e -> submitInitialLives(initialLivesField
                                                                   .getText()));

        initialLivesField.setPrefWidth(105); // TODO magic number
        root = GridFactory.createRowWithLabelandNode(settingsResource.getString("InitialLives"),
                                                     initialLivesField, 125);
    }

    public void updateInitialLives (int lives) {
        initialLives = lives;
        initialLivesField.setText(Integer.toString(lives));
    }

    private void submitInitialLives (String livesString) {
        try {
            initialLives = Integer.parseInt(livesString);
            delegate.onUserEnteredGameLives(initialLives);
        }
        catch (NumberFormatException e) {
            updateInitialLives(initialLives);
            Alert inputError =
                    DialogueBoxFactory.createErrorDialogueBox(
                                                              "The number of lives must be an integer.",
                                                              "Input error");
            // TODO move to resource file
        }
    }

}
