package engine.path;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import authoring.editorview.IUpdateView;
import authoring.editorview.path.PathSetView;
import authoring.editorview.path.PathUpdateView;
import authoring.editorview.tower.TowerUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.tower.Tower;


public class PathTypeManagerController
        extends AbstractTypeManagerController<PathManager, PathBuilder, Path, PathUpdateView> implements PathManagerController {

    public PathTypeManagerController (ManagerMediator managerMediator) {
        super(new PathTypeManager(), new PathTypeBuilder(), managerMediator);
    }

    @Override
    public void setNumberofColumns (int pathID, int columns) {
        getTypeManager().getEntity(pathID).setGridColumns(columns);
    }

    @Override
    public void setNumberofRows (int pathID, int rows) {
        getTypeManager().getEntity(pathID).setGridRows(rows);
    }
    
    @Override
    public void setType (int pathID, String type) {
        getTypeManager().getEntity(pathID).clearCoordinates();
        getTypeManager().getEntity(pathID).setType(PathOption.valueOf(type));        
    }
    
    @Override
    public boolean setNewPathCoordinate (int pathID, int x, int y) {
        GridCoordinate newCoordinate = new GridCoordinate(x, y);
        return handleRequest(validatePath(pathID, getPathOption(pathID)::validateAddition, newCoordinate), a -> a.getEntity(pathID).addCoordinate(newCoordinate));
    }
    
    private boolean validatePath(int pathID, BiFunction<List<Coordinate<Integer>>, GridCoordinate, Boolean> operation,  GridCoordinate newCoordinate) {
        return operation.apply(getTypeManager().getEntity(pathID).getCoordinates(), newCoordinate);
    }
    
    private PathOption getPathOption(int pathID) {
        return getTypeManager().getEntity(pathID).getType();
    }
//    private boolean validatePathAddition(int pathID, GridCoordinate gridCoordinate) {
//        List<Coordinate<Integer>> coordinates = getTypeManager().getEntity(pathID).getCoordinates();
//        if(coordinates.size()==0){
//            return true;
//        }
//        Coordinate<Integer> lastCoordinate = coordinates.get(coordinates.size()-1);
//        return gridCoordinate.isCardinalTo(lastCoordinate);
//    }
    
//    private boolean validatePathRemoval(int pathID, int x, int y) {
//        List<Coordinate<Integer>> coordinates = getTypeManager().getEntity(pathID).getCoordinates();
//        Coordinate<Integer> lastCoordinate = coordinates.get(coordinates.size()-1);
//        return lastCoordinate.getX() == x && lastCoordinate.getY() == y;  
//    }
    
    @Override
    public boolean removePathCoordinate (int pathID, int x, int y) {
        GridCoordinate newCoordinate = new GridCoordinate(x, y);
        return handleRequest(validatePath(pathID, getPathOption(pathID)::validateRemoval, newCoordinate), a-> a.getEntity(pathID).removeCoordinate(newCoordinate));
//        List<Coordinate<Integer>> coordinates = getTypeManager().getEntity(pathID).getCoordinates();
//        boolean isValidRemove  = coordinates.get(coordinates.size()-1).getX() == x && coordinates.get(coordinates.size()-1).getY() == y;
//        if(isValidRemove){
//            getTypeManager().getEntity(pathID).removeCoordinate(new GridCoordinate(x, y));
//        }
//        //System.out.println(getTypeManager().getEntity(pathID).getCoordinates().size());
//        return isValidRemove; // TODO - validate this
    }
    
    @Override
    public List<Integer> getAvailablePaths () {
        return Collections.unmodifiableList(getTypeManager().getAvailablePaths());
    }
    
    @Override
    public void setSquareGridDimensions (int pathID, int size) {
        if(size < getTypeManager().getEntity(pathID).getGridRows()) {
            getTypeManager().getEntity(pathID).getCoordinates().clear();
        }

        setNumberofColumns(pathID, size);
        setNumberofRows(pathID, size);
    }

    
    @Override
    public int getNumberofColumns (int pathID) {
        return getTypeManager().getEntity(pathID).getGridColumns();
    }

    @Override
    public int getNumberofRows (int pathID) {
        return getTypeManager().getEntity(pathID).getGridRows();
    }

    @Override
    public String getType (int pathID) {
        return getTypeManager().getEntity(pathID).getType().name();        
    }
    
    @Override
    public List<Coordinate<Integer>> getPathCoordinates (int pathID) {
        return Collections.unmodifiableList(getTypeManager().getEntity(pathID).getCoordinates());
    }
    
    
    @Override
    protected PathBuilder constructTypeProperties (PathUpdateView updateView,
                                                   PathBuilder typeBuilder) {
        return typeBuilder
                .addCoordinatesListener( (oldValue, newValue) -> updateView
                        .updatePathCoordinates(Collections.unmodifiableList(newValue)))
                .addGridRowsListener( (oldValue, newValue) -> updateView
                        .updateGridDimensions(newValue))
                .addTypeListener((oldValue, newValue) -> updateView
                        .updateType(newValue.name()));
    }

    @Override
    public void addAvailablePathListener(Consumer<List<Integer>> listener) {
        getTypeManager().addAvailablePathListener((oldValue, newValue) -> listener.accept(newValue));
    }
}
