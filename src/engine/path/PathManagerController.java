package engine.path;

import java.util.List;
import java.util.function.Consumer;


import authoring.editorview.path.PathUpdateView;
import engine.ManagerController;

public interface PathManagerController extends ManagerController<PathManager, PathBuilder, Path, PathUpdateView>{

    boolean setNewPathCoordinate (int pathID, int x, int y);
    
    boolean removePathCoordinate (int pathID, int x, int y);

    void setType(int pathID, String type);
    
    void setNumberofColumns (int pathID, int columns);
    
    void setNumberofRows (int pathID, int rows);
                    
    List<Coordinate<Integer>> getPathCoordinates (int pathID);
    
    String getType(int pathID);
    
    int getNumberofColumns (int pathID);
    
    int getNumberofRows (int pathID);
    
    void setSquareGridDimensions (int pathID, int size);
    
    List<Integer> getAvailablePaths ();
    
    void addAvailablePathListener(Consumer<List<Integer>> listener);
    
}
