package authoring.editorview.path;

import authoring.editorview.EditorViewController;
import authoring.editorview.ListCellData;
import authoring.editorview.ListDataSource;
import engine.path.PathManagerController;


public class PathAuthoringViewController extends EditorViewController
        implements PathAuthoringViewDelegate, ListDataSource {

    private PathUpdateView pathView;
    private PathManagerController pathDataSource;
    private int activeID;

    public PathAuthoringViewController (int editorWidth, int editorHeight) {
        this.pathView = PathAuthoringViewFactory.build(editorWidth, editorHeight);
        pathView.setDelegate(this);
        pathView.setPathListDataSource(this);
        this.view = pathView;
    }

    public void setPathDataSource (PathManagerController source) {
        this.pathDataSource = source;
        this.pathDataSource.addTypeBankListener(this.pathView);
        onUserEnteredCreatePath();
    }

    @Override
    public void onUserEnteredGridDimensions (int dimensions) {
        pathDataSource.setSquareGridDimensions(activeID, dimensions);
    }

    @Override
    public void onUserEnteredPathImage (String pathImagePath) {
        pathDataSource.setImagePath(activeID, pathImagePath);

    }

    @Override
    public void onUserEnteredPathName (String pathName) {
        pathDataSource.setName(activeID, pathName);
    }

    @Override
    public void onUserEnteredPathType (String pathType) {
        pathDataSource.setType(activeID, pathType);

    }

    @Override
    public void onUserEnteredCreatePath () {
        activeID = pathDataSource.createType(pathView);
        refreshView();
    }

    @Override
    public void onUserEnteredEditPath (int pathID) {
        activeID = pathID;
        refreshView();
    }

    @Override
    public boolean onUserEnteredAddPathCoordinate (int x, int y) {
        return pathDataSource.setNewPathCoordinate(activeID, x, y);

    }

    @Override
    public boolean onUserEnteredRemovePathCoordinate (int x, int y) {
        return pathDataSource.removePathCoordinate(activeID, x, y);
    }

    @Override
    public void onUserEnteredDeletePath () {
        pathDataSource.deleteType(activeID);

    }

    @Override
    public ListCellData getCellDataForSubject (int id) {
        ListCellData cellData = new ListCellData();
        cellData.setName(pathDataSource.getName(id));
        cellData.setImagePath(pathDataSource.getImagePath(id));
        cellData.setId(id);
        return cellData;
    }

    @Override
    public void refreshView () {
        pathView.updateImagePathDisplay(pathDataSource.getImagePath(activeID));
        pathView.updateGridDimensions(pathDataSource.getNumberofRows(activeID));
        pathView.updatePathCoordinates(pathDataSource.getPathCoordinates(activeID));
        pathView.updateNameDisplay(pathDataSource.getName(activeID));
        pathView.updateType(pathDataSource.getType(activeID));
        pathView.updatePath();

    }

}
