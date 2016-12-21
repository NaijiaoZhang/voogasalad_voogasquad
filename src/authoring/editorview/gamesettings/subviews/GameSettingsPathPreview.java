package authoring.editorview.gamesettings.subviews;

import java.io.File;
import java.util.List;
import authoring.editorview.INodeView;
import engine.path.Coordinate;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameSettingsPathPreview implements INodeView {

    private List<Coordinate<Integer>> pathCoordinates;
    private Image image;
    private Group root;

    private int dimensions;
    private static final int SIZE = 700;

    public GameSettingsPathPreview (
                                    List<Coordinate<Integer>> pathCoordinates,
                                    String imagePath,
                                    int dimensions) {
        this.root = new Group();
        this.pathCoordinates = pathCoordinates;
        this.dimensions = dimensions;
        File file = new File(imagePath);
        this.image = new Image(file.toURI().toString());
        drawPath();
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    private void drawPath () {
        int cellSize = SIZE / dimensions;
        for (Coordinate<Integer> coordinate : pathCoordinates) {
            ImageView iv = new ImageView(image);
            iv.setFitHeight(cellSize);
            iv.setFitWidth(cellSize);
            iv.setLayoutX(coordinate.getX() * cellSize);
            iv.setLayoutY(coordinate.getY() * cellSize);
            root.getChildren().add(iv);
        }
    }

}
