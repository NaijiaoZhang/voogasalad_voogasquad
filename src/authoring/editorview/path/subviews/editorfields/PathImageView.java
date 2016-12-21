package authoring.editorview.path.subviews.editorfields;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import authoring.utilityfactories.DialogueBoxFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class PathImageView extends PhotoFileChooser implements PathSetView {

    private GridPane root;
    private String pathImagePath;

    private PathAuthoringViewDelegate delegate;

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringPath";
    private ResourceBundle pathResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    public PathImageView () {
        root = new GridPane();
        makeChooseImageButton();
    }

    @Override
    public Node getInstanceAsNode () {
        return root;

    }

    private void makeChooseImageButton () {
        Button setPathImageButton =
                ButtonFactory.makeButton(pathResource.getString("PathImageButton"),
                                         e -> {
                                             try {
                                                 selectFile("Photos: ", "Select new path image"); // TODO
                                                                                                  // resource
                                                                                                  // file
                                             }
                                             catch (IOException e1) {
                                                 Alert errorDialogueBox =
                                                         DialogueBoxFactory
                                                                 .createErrorDialogueBox("Invalid File",
                                                                                         "Error With File");

                                             }
                                         });
        setPathImageButton.setPrefWidth(280); // TODO magic number
        root.getChildren().add(setPathImageButton);
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        File chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            BufferedImage image = ImageIO.read(chosenFile);
            pathImagePath = chosenFile.toURI().getPath();
            delegate.onUserEnteredPathImage(pathImagePath);
        }
    }

    public String getPathImagePath () {
        return pathImagePath;
    }

    public void setPathImagePath (String imagePath) {
        this.pathImagePath = imagePath;
    }

    public void setDelegate (PathAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

}
