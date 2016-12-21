package engine.path;

import java.util.List;
import engine.TypeBuilder;
import engine.weapon.BindableWeapon;
import engine.weapon.Weapon;
import engine.weapon.WeaponBuilder;
import engine.weapon.WeaponInitializer;


public interface PathBuilder extends TypeBuilder<Path, PathBuilder>, BindablePath { //TODO - Add bindable interface

    PathBuilder buildType (PathOption type);

    PathBuilder buildCoordinates (List<Coordinate<Integer>> coordinates);
    
    PathBuilder buildCoordinates (Coordinate<Integer>... coordinates);
    
    PathBuilder buildGridSizeRows (Integer gridRows);
    
    PathBuilder buildGridSizeColumns (Integer gridColumns);

}
