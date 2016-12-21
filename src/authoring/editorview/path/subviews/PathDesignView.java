package authoring.editorview.path.subviews;

import java.util.List;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.editorview.path.subviews.editorfields.PathGrid;
import engine.path.Coordinate;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;


public class PathDesignView implements PathSetView {

    private static final int GRID_SIZE = 400;

    private AnchorPane rootBuffer;
    private Group root;

    private PathInstructionsView pathInstructionsView;
    private PathGrid pathGrid;

    public PathDesignView () {
        this.root = new Group();
        rootBuffer = new AnchorPane();
        rootBuffer.getChildren().add(root);

        AnchorPane.setLeftAnchor(root, 25.0);
        AnchorPane.setTopAnchor(root, 50.0);
        this.pathInstructionsView = new PathInstructionsView();
        this.pathGrid = new PathGrid(GRID_SIZE);

        Node instructions = pathInstructionsView.getInstanceAsNode();
        // instructions.setTranslateX(500);
        // instructions.setLayoutY(0);
        Node grid = pathGrid.getInstanceAsNode();
        grid.setLayoutX(25);
        grid.setLayoutY(100);

        root.getChildren().addAll(instructions, grid);
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        pathGrid.setDelegate(delegate);

    }

    public void redrawPath () {
        pathGrid.redrawPath();
    }

    public void updateImagePathDisplay (String imagePath) {
        pathGrid.setCellImage(imagePath);
    }

    public void updatePathCoordinates (List<Coordinate<Integer>> pathCoordinates) {
        pathGrid.setPathCoordinates(pathCoordinates);

    }

    public void updateGridDimensions (int dimensions) {
        pathGrid.setGridDimensions(dimensions);

    }

}
