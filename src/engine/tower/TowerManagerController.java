package engine.tower;

import java.util.List;
import authoring.editorview.tower.TowerUpdateView;
import engine.ManagerController;
import engine.effect.EffectManagerController;


/**
 * @author Sean Hudson
 * @author Andrew Bihl
 * @author Kayla Schulz
 *
 */
public interface TowerManagerController
        extends ManagerController<TowerManager, TowerBuilder, Tower, TowerUpdateView> {

    public double getTowerBuyPrice (int towerID);

    public double getTowerSellPrice (int towerID);

    public int getTowerUnlockLevel (int towerID);

    public List<Integer> getTowerUpgrades (int towerID);

    public List<Integer> getTowerChosenWeapons (int towerID);

    public List<Integer> getTowerAbilities (int towerID);

    public void setTowerBuyPrice (int towerID, double towerBuyPrice);

    public void setTowerSellPrice (int towerID, double towerSellPrice);

    public void setTowerUnlockLevel (int towerID, int towerLevel);

    public void setTowerChosenAbility (int towerID, int towerAbility);

    public void removeTowerChosenAbility (int towerID, int towerAbility);

    public void setTowerChosenWeapon (int towerID, int towerChosenWeaponID);

    public void removeTowerWeapon (int towerID, int towerChosenWeaponID);

    public int createTowerUpgrade (TowerUpdateView towerUpdater, int parentTowerID);

    public void removeTowerUpgrade (int parentTowerID, int childTowerID);

    public EffectManagerController getEffectManagerController();
}
