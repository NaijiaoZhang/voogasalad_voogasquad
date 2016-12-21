package authoring.editorview.weapon;

import java.util.List;
import authoring.editorview.IUpdateView;
import authoring.editorview.ListDataSource;


/**
 * The Weapon View interface will detail the components of the weapon creation. The interface
 * determines a portion of our internal API.
 * 
 * @author Andrew Bihl
 * @author Kayla Schulz
 *
 */
public interface WeaponUpdateView extends WeaponSetView, IUpdateView {

    public void updateFireRateDisplay (double fireRate);

    public void updateSpeedDisplay (double speed);

    public void updateRangeDisplay (double range);

    public void updateWeaponBank (List<Integer> activeWeapons);

    public void updateTargetEnemies (List<Integer> targetEnemies);

    public void updateWeaponTrajectory (String trajectory);

    public void createNewWeapon (); // This is the method where I will go and update e'rthing

    public void deleteWeapon ();

    public void setWeaponListDataSource (ListDataSource source);

}
