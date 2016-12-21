package authoring.editorview.path;

import authoring.editorview.path.subviews.PathBank;
import authoring.editorview.path.subviews.PathDesignView;
import authoring.editorview.path.subviews.PathEditorView;
import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ListDataSource;
import engine.path.Coordinate;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class PathAuthoringView implements PathUpdateView {

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringPath";
    private ResourceBundle pathResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    private static final int EDITOR_SIZE = 300;
    private static final int AUTHORING_HEIGHT = 700;

    private PathEditorView pathEditor;
    private PathDesignView pathDesign;
    private PathBank pathBank;
    private GridPane pathView;

    public PathAuthoringView (int aWidth, int aHeight) {

        pathView = new GridPane();

        pathEditor = new PathEditorView(EDITOR_SIZE, pathResource);
        pathDesign = new PathDesignView();
        pathBank = new PathBank(pathResource);
        buildView();

    }

    @Override
    public Node getInstanceAsNode () {
        return pathView;
    }

    @Override
    public void setDelegate (PathAuthoringViewDelegate delegate) {
        pathEditor.setDelegate(delegate);
        pathDesign.setDelegate(delegate);
        pathBank.setDelegate(delegate);
    }

    private void buildView () {

        ColumnConstraints bankColumn = new ColumnConstraints();
        bankColumn.setMinWidth(150);

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setMinWidth(EDITOR_SIZE);

        ColumnConstraints previewColumn = new ColumnConstraints();

        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(AUTHORING_HEIGHT);

        pathView.getColumnConstraints().addAll(bankColumn, editorColumn, previewColumn);
        pathView.getRowConstraints().add(fullRow);

        pathView.add(pathBank.getInstanceAsNode(), 0, 0);
        pathView.add(pathEditor.getInstanceAsNode(), 1, 0);
        pathView.add(pathDesign.getInstanceAsNode(), 2, 0);
    }

    @Override
    public void updateGridDimensions (int dimensions) {
        pathEditor.updateGridDimensions(dimensions);
        pathDesign.updateGridDimensions(dimensions);

    }

    @Override
    public void updatePathCoordinates (List<Coordinate<Integer>> pathCoordinates) {
        pathDesign.updatePathCoordinates(pathCoordinates);
    }

    @Override
    public void updateNameDisplay (String name) {
        pathEditor.updatePathName(name);
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        pathEditor.updatePathImagePath(imagePath);
        pathDesign.updateImagePathDisplay(imagePath);
        pathBank.updateBank();
    }

    @Override
    public void updateType (String pathType) {
        //System.out.println(pathType);
        pathEditor.updataPathType(pathType);

    }

    @Override
    public void updatePath () {
        pathDesign.redrawPath();
    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateBank (List<Integer> ids) {
        pathBank.updateBank(ids);
    }

    @Override
    public void setPathListDataSource (ListDataSource source) {
        pathBank.setListDataSource(source);
    }

    @Override
    public void updateSizeDisplay (double size) {
    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        // TODO Auto-generated method stub
        return null;
    }

}
