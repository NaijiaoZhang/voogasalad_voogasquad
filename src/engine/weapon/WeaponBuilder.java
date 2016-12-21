package engine.weapon;

import java.util.List;
import engine.TypeBuilder;
import engine.tower.TowerBuilder;

public interface WeaponBuilder extends TypeBuilder<Weapon, WeaponBuilder>, BindableWeapon { //TODO - Add bindable interface

    WeaponBuilder buildTargets (Integer ... targets);
    
    WeaponBuilder buildTargets (List<Integer> targets);
    
    WeaponBuilder buildEffects (Integer ... effects);
    
    WeaponBuilder buildEffects (List<Integer> effects);
    
    WeaponBuilder buildReloadTime (double reloadTime);

    WeaponBuilder buildTrajectory (String trajectory);

    WeaponBuilder buildSpeed (double speed);

    WeaponBuilder buildRange (double range);

}
