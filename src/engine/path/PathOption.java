package engine.path;

import java.util.List;
import java.util.function.Predicate;

public enum PathOption {
        SET,
        FREE {
 
            public boolean validateAddition(List<Coordinate<Integer>> coordinates,  GridCoordinate newCoordinate) {
                return validatePath(coordinates, a -> !newCoordinate.equals(a) && coordinates.size() == 1 , true);
             }
            
            public boolean validateRemoval (List<Coordinate<Integer>> coordinates,  GridCoordinate newCoordinate) {
                return validatePath(coordinates, a -> coordinates.contains(newCoordinate),
                                    false);
            }
    };
    
    public boolean validateRemoval (List<Coordinate<Integer>> coordinates,  GridCoordinate newCoordinate) {
        return validatePath(coordinates, a -> a.equals(newCoordinate),
                            false);
    }
    
    public boolean validateAddition(List<Coordinate<Integer>> coordinates, GridCoordinate newCoordinate) {
        return validatePath(coordinates, a -> newCoordinate.isCardinalTo(a) , true);
     }
    
    private static Coordinate<Integer> getLastCoordinate(List<Coordinate<Integer>> coordinates) {
        return coordinates.get(coordinates.size()-1);
    }
    
    private static boolean validatePath(List<Coordinate<Integer>> coordinates, Predicate<Coordinate<Integer>> condition, boolean isAddition) {
        if(coordinates.size()==0){
            return isAddition;
        }
        return condition.test(getLastCoordinate(coordinates));

}
    }
