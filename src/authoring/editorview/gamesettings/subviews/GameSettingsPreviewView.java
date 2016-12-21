package authoring.editorview.gamesettings.subviews;

import java.io.File;
import java.util.List;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameSettingsPreviewView implements GameSettingsSetView {

    private Group root;
    private Group pathRoot;
    private ImageView gameImageView;
    private GameSettingsAuthoringViewDelegate delegate;

    public GameSettingsPreviewView (int size) {
        root = new Group();
        pathRoot = new Group();
        formatImageView(size);
        root.getChildren().add(pathRoot);
    }

    private void formatImageView (int size) {
        gameImageView = new ImageView();
        gameImageView.setFitWidth(size);
        gameImageView.setFitHeight(size);
        root.getChildren().add(gameImageView);
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public void updateGameImagePath (String imagePath) {
        File imageFile = new File(imagePath);
        Image image = new Image(imageFile.toURI().toString());
        gameImageView.setImage(image);
    }

    public void updateGridDimensions (int size) {
        pathRoot.getChildren().clear();
    }

    public void updatePathList (List<Integer> pathList) {
    	pathRoot.getChildren().clear();
        for (Integer pathID : pathList) {
            GameSettingsPathPreview pathPreview = new GameSettingsPathPreview(
                                                                              delegate.getPathCoordinates(pathID),
                                                                              delegate.getPathImage(pathID),
                                                                              delegate.getPathDimensions(pathID));
            pathRoot.getChildren().add(pathPreview.getInstanceAsNode());
        }
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;

    }

}
