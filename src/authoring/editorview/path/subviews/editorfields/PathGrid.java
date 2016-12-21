package authoring.editorview.path.subviews.editorfields;

import java.io.File;
import java.util.List;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import engine.path.Coordinate;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;


public class PathGrid implements PathSetView {

    private int gridSize;
    private int gridDimensions;
    private int cellSize;
    private Image cellImage;
    private ImageView[][] pathGrid;
    private Rectangle[][] backgroundGrid;
    private List<Coordinate<Integer>> pathCoordinates;
    private PathAuthoringViewDelegate delegate;
    private Group root;
    private Group gridRoot;
    private Group backgroundRoot;

    public PathGrid (int size) {
        this.gridSize = size;
        this.root = new Group();
        this.gridRoot = new Group();
        this.backgroundRoot = new Group();
        root.getChildren().addAll(backgroundRoot, gridRoot);
        setPathUpdater();
    }

    private void setPathUpdater () {
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {  // http://stackoverflow.com/questions/27785917/javafx-mouseposition
            @Override
            public void handle (MouseEvent event) {
                double x = event.getSceneX();
                double y = event.getSceneY();
                updatePath(x, y);
            }
        });
    }

    private void updatePath (double x, double y) {
        int i = (int) (x - 500) / cellSize;
        int j = (int) (y - 190) / cellSize;
        if (!addCellToPath(i, j)) {
            removeCellFromPath(i, j);
        }
    }

    private boolean addCellToPath (int x, int y) {
        boolean validCoordinate = delegate.onUserEnteredAddPathCoordinate(x, y);
        if (validCoordinate) {
            pathGrid[x][y].setVisible(true);
        }
        return validCoordinate;
    }

    private void removeCellFromPath (int x, int y) {
        boolean validCoordinate = delegate.onUserEnteredRemovePathCoordinate(x, y);
        if (validCoordinate) {
            pathGrid[x][y].setVisible(false);
        }
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void setGridDimensions (int dimensions) {
        cellSize = gridSize / dimensions;
        this.gridDimensions = dimensions;
        if (pathCoordinates != null) {
            redrawPath();
        }
    }

    public List<Coordinate<Integer>> getPathCoordinates () {
        return pathCoordinates;
    }

    public void setPathCoordinates (List<Coordinate<Integer>> pathCoordinates) {
        this.pathCoordinates = pathCoordinates;
        redrawPath();
    }

    public void setCellImage (String imagePath) {

        File file = new File(imagePath);
        cellImage = new Image(file.toURI().toString());

        if (gridDimensions > 0 && pathCoordinates != null) {
            redrawPath();
        }
    }

    public void redrawPath () {
        clearBackground();
        clearPath();
        setBackground();
        setPath();
    }

    private void clearBackground () {
        backgroundRoot.getChildren().clear();
    }

    private void clearPath () {
        gridRoot.getChildren().clear();
    }

    private void setBackground () {
        cellSize = gridSize / gridDimensions;
        backgroundGrid = new Rectangle[gridDimensions][gridDimensions];
        for (int i = 0; i < gridDimensions; i++) {
            for (int j = 0; j < gridDimensions; j++) {

                Rectangle rect = new Rectangle();
                rect = formatRectangle(i, j, rect);
                setMouseHoverEffect(rect);
                backgroundGrid[i][j] = rect;
                backgroundRoot.getChildren().add(rect);
            }
        }
    }

    private Rectangle formatRectangle (int x, int y, Rectangle rect) {
        rect.setHeight(cellSize);
        rect.setWidth(cellSize);
        rect.setX(x * cellSize);
        rect.setY(y * cellSize);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.GRAY);
        rect.setStrokeType(StrokeType.INSIDE);
        rect.setStrokeWidth(0.5);
        return rect;
    }

    private void setMouseHoverEffect (Rectangle rect) {
        rect.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                Rectangle node = (Rectangle) event.getTarget();
                node.setFill(Color.LIGHTGRAY);
            }
        });
        rect.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                Rectangle node = (Rectangle) event.getTarget();
                node.setFill(Color.WHITE);
            }
        });
    }

    private void setPath () {
        pathGrid = new ImageView[gridDimensions][gridDimensions];
        for (int i = 0; i < gridDimensions; i++) {
            for (int j = 0; j < gridDimensions; j++) {
                setCell(i, j);
            }
        }
    }

    private void setCell (int x, int y) {
        boolean inPath = setInPath(x, y);
        setNode(x, y, inPath);
    }

    private boolean setInPath (int x, int y) {
        boolean inPath = false;
        for (Coordinate<Integer> coordinate : pathCoordinates) {
            if (coordinate.getX() == x && coordinate.getY() == y) {
                inPath = true;
                break;
            }
        }
        return inPath;
    }

    private void setNode (int x, int y, boolean inPath) {
        ImageView iv = new ImageView(cellImage);
        iv = formatImageView(x, y, iv);
        if (!inPath) {
            iv.setVisible(false);
        }
        pathGrid[x][y] = iv;
        gridRoot.getChildren().add(pathGrid[x][y]);
    }

    private ImageView formatImageView (int x, int y, ImageView iv) {
        iv.setFitHeight(cellSize);
        iv.setFitWidth(cellSize);
        iv.setX(x * cellSize);
        iv.setY(y * cellSize);
        return iv;
    }

}
