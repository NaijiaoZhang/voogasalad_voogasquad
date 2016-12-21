package engine.path;

import java.util.List;
import engine.Type;


public interface Path extends Type {

    PathOption getType ();

    void setType (PathOption type);
    
    void addCoordinate (Coordinate<Integer> coordinate);

    void removeCoordinate (Coordinate<Integer> coordinate);

    void clearCoordinates();
    
    List<Coordinate<Integer>> getCoordinates ();
    
    int getGridRows();
    
    void setGridRows(int gridRows);
    
    int getGridColumns();
    
    void setGridColumns(int gridColumns);

}
