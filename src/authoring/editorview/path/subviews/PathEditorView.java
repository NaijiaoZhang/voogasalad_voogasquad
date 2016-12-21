package authoring.editorview.path.subviews;

import java.util.ResourceBundle;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.editorview.path.subviews.editorfields.PathDimensionsView;
import authoring.editorview.path.subviews.editorfields.PathImageDisplayView;
import authoring.editorview.path.subviews.editorfields.PathImageView;
import authoring.editorview.path.subviews.editorfields.PathNameView;
import authoring.editorview.path.subviews.editorfields.PathTypeView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class PathEditorView implements PathSetView {

    private VBox root;
    private AnchorPane rootBuffer;
    private static final double BUFFER = 10.0;

    private PathImageView pathImageView;
    private PathNameView pathNameView;
    private PathDimensionsView pathDimensionsView;
    private PathImageDisplayView pathImageDisplayView;
    private PathTypeView pathTypeView;
    private DeletePathView deletePathView;

    public PathEditorView (int size, ResourceBundle pathResource) {
        rootBuffer = new AnchorPane();
        root = new VBox(10);

        this.pathImageView = new PathImageView();
        this.pathNameView = new PathNameView(pathResource);
        this.pathDimensionsView = new PathDimensionsView();
        this.pathImageDisplayView = new PathImageDisplayView(pathResource);
        this.pathTypeView = new PathTypeView();
        this.deletePathView = new DeletePathView();
        buildView();

    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        pathImageView.setDelegate(delegate);
        pathNameView.setDelegate(delegate);
        pathDimensionsView.setDelegate(delegate);
        pathTypeView.setDelegate(delegate);
        deletePathView.setDelegate(delegate);

    }

    private void buildView () {
        rootBuffer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root, BUFFER);
        AnchorPane.setTopAnchor(root, BUFFER);

        rootBuffer
                .setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                                                       CornerRadii.EMPTY, new BorderWidths(0.5))));
        rootBuffer
                .setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235),
                                                                 CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(
                                  pathNameView.getInstanceAsNode(),
                                  pathDimensionsView.getInstanceAsNode(),
                                  pathImageDisplayView.getInstanceAsNode(),
                                  pathImageView.getInstanceAsNode(),
                                  pathTypeView.getInstanceAsNode(),
                                  deletePathView.getInstanceAsNode()

        );

    }

    public void updatePathName (String pathName) {
        pathNameView.updateName(pathName);
    }

    public void updateGridDimensions (int dimensions) {
        pathDimensionsView.setGridDimensions(dimensions);

    }

    public void updatePathImagePath (String imagePath) {
        pathImageView.setPathImagePath(imagePath);
        pathImageDisplayView.updateImage(imagePath);
    }

    public void updataPathType (String pathType) {
        pathTypeView.updatePathType(pathType);
        updateView(pathType);
    }

    public void updateView (String pathType) {
        if (pathType == "Free" &&
            root.getChildren().contains(pathImageDisplayView.getInstanceAsNode()) &&
            root.getChildren().contains(pathImageView.getInstanceAsNode())) {
            root.getChildren().removeAll(
                                         pathImageDisplayView.getInstanceAsNode(),
                                         pathImageView.getInstanceAsNode());

        }
        else if (pathType == "Set" &&
                 !root.getChildren().contains(pathImageDisplayView.getInstanceAsNode()) &&
                 !root.getChildren().contains(pathImageView.getInstanceAsNode())) {
            root.getChildren().addAll(
                                      pathImageDisplayView.getInstanceAsNode(),
                                      pathImageView.getInstanceAsNode());

        }

    }

}
