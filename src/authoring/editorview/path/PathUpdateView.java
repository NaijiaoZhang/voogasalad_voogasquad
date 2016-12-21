package authoring.editorview.path;

import java.util.List;
import authoring.editorview.IUpdateView;
import authoring.editorview.ListDataSource;
import engine.path.Coordinate;


public interface PathUpdateView extends PathSetView, IUpdateView {

    public void updateGridDimensions (int dimensions);

    public void updatePathCoordinates (List<Coordinate<Integer>> pathCoordinates);

    public void updateType (String pathType);

    public void updatePath ();

    public void setPathListDataSource (ListDataSource source);

}
