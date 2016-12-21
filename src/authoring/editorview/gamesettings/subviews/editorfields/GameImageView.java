package authoring.editorview.gamesettings.subviews.editorfields;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.ButtonFactory;
import authoring.utilityfactories.DialogueBoxFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class GameImageView extends PhotoFileChooser implements GameSettingsSetView {

    private HBox root;
    private String gameImagePath;
    private GameSettingsAuthoringViewDelegate delegate;

    public GameImageView (ResourceBundle settingsResource) {
        root = new HBox();
        makeChooseImageButton(settingsResource);
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public String getGameImagePath () {
        return gameImagePath;
    }

    public void updateGameImagePath (String imagePath) {
        this.gameImagePath = imagePath;
    }

    private void makeChooseImageButton (ResourceBundle settingsResource) {
        Button setBackgroundImageButton =
                ButtonFactory.makeButton(settingsResource.getString("BackgroundImageButton"),
                                         e -> {
                                             try {
                                                 selectFile(photoFileResource.getString("Photos"),
                                                            photoFileResource
                                                                    .getString("SelectImage"));
                                             }
                                             catch (IOException e1) {
                                                 Alert errorDialogueBox =
                                                         DialogueBoxFactory.createErrorDialogueBox(
                                                                                                   photoFileResource
                                                                                                           .getString("InvalidFile"),
                                                                                                   photoFileResource
                                                                                                           .getString("Error"));
                                                 errorDialogueBox.show();
                                             }
                                         });
        setBackgroundImageButton.setPrefWidth(230); // TODO: magic number
        root.getChildren().add(setBackgroundImageButton);
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        File chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            gameImagePath = chosenFile.toString();
            delegate.onUserEnteredGameImage(gameImagePath);
        }
    }

}
