package engine.weapon;

import java.util.List;
import authoring.editorview.weapon.WeaponUpdateView;
import engine.ManagerController;
import engine.effect.EffectManagerController;


/**
 * Passes edits made to the weapon by the designer to the game engine.
 * 
 * @author Andrew Bihl
 */
public interface WeaponManagerController extends ManagerController<WeaponManager, WeaponBuilder, Weapon, WeaponUpdateView> {

    public void setWeaponRange (int weaponID, double weaponRange);

    public void setWeaponFireRate (int weaponID, double weaponFireRate);

    public void setWeaponSpeed (int weaponID, double weaponSpeed);

    public void setWeaponCollisionEffect (int weaponID, int weaponCollisionEffectID);

    public void setWeaponTrajectory (int weaponID, String weaponTrajectory);

    public void setNewWeaponTargetEnemy (int weaponID, int enemyID);

    public void removeWeaponTargetEnemy (int weaponID, int enemyID);

    public double getWeaponRange (int weaponID);

    public double getWeaponReloadTime (int weaponID);

    public double getWeaponSpeed (int weaponID);

    public List<Integer> getWeaponCollisionEffect (int weaponID);

    public String getWeaponTrajectory (int weaponID);

    public List<Integer> getTargetEnemies (int weaponID);
    
    EffectManagerController getEffectManagerController();

}
