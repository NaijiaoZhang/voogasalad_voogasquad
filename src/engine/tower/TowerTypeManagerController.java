package engine.tower;

import java.util.Collections;
import java.util.List;
import com.oracle.webservices.internal.api.databinding.Databinding.Builder;
import authoring.editorview.tower.TowerUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.effect.EffectManagerController;
import engine.effect.EffectTypeManagerController;


public class TowerTypeManagerController
        extends AbstractTypeManagerController<TowerManager, TowerBuilder, Tower, TowerUpdateView>
        implements TowerManagerController {
    
    private EffectManagerController abilityEffectManagerController;
    
    public TowerTypeManagerController (ManagerMediator managerMediator) {
        super(new TowerTypeManager(), new TowerTypeBuilder(), managerMediator);
        abilityEffectManagerController = new EffectTypeManagerController(managerMediator, getTypeManager().getEffectManager());
    }

    @Override
    public double getTowerBuyPrice (int towerID) {
        return getTypeManager().getEntity(towerID).getCost();
    }

    @Override
    public double getTowerSellPrice (int towerID) {
        return getTypeManager().getEntity(towerID).getSellAmount();
    }

    @Override
    public int getTowerUnlockLevel (int towerID) {
        return getTypeManager().getEntity(towerID).getUnlockLevel();
    }

    @Override
    public List<Integer> getTowerUpgrades (int towerID) {
        return Collections.unmodifiableList(getTypeManager().getEntity(towerID).getUpgrades());
    }

    @Override
    public List<Integer> getTowerChosenWeapons (int towerID) {
        return Collections.unmodifiableList(getTypeManager().getEntity(towerID).getWeapons());
    }

    @Override
    public List<Integer> getTowerAbilities (int towerID) {
        return Collections.unmodifiableList(getTypeManager().getEntity(towerID).getAbilities());
    }

    @Override
    public void setTowerBuyPrice (int towerID, double towerBuyPrice) {
        getTypeManager().getEntity(towerID).setCost(towerBuyPrice);
    }

    @Override
    public void setTowerSellPrice (int towerID, double towerSellPrice) {
        getTypeManager().getEntity(towerID).setSellAmount(towerSellPrice);
    }

    @Override
    public void setTowerUnlockLevel (int towerID, int towerLevel) {
        getTypeManager().getEntity(towerID).setUnlockLevel(towerLevel);
    }

    @Override
    public void setTowerChosenAbility (int towerID, int towerAbility) {
        getTypeManager().getEntity(towerID).addAbility(towerAbility);
    }

    @Override
    public void removeTowerChosenAbility (int towerID, int towerAbility) {
        getTypeManager().getEntity(towerID).removeAbility(towerAbility);
    }

    @Override
    public void setTowerChosenWeapon (int towerID, int towerChosenWeaponID) {
        getTypeManager().getEntity(towerID).addWeapon(towerChosenWeaponID);
    }

    @Override
    public void removeTowerWeapon (int towerID, int towerChosenWeaponID) {
        getTypeManager().getEntity(towerID).removeWeapon(towerChosenWeaponID);
    }

    // TODO - edit createNewTower to work with both versions
    @Override
    public int createTowerUpgrade (TowerUpdateView towerUpdater, int parentTowerID) {
        copyWithoutId(parentTowerID);
        return getTypeManager().addUpgrade(constructType(towerUpdater), parentTowerID);
    }

    @Override
    public void removeTowerUpgrade (int parentTowerID, int childTowerID) {
        getTypeManager().removeUpgrade(childTowerID, parentTowerID);
    }

    @Override
    protected TowerBuilder constructTypeProperties (TowerUpdateView towerUpdater,
                                                    TowerBuilder typeBuilder) {
        return typeBuilder
                .addWeaponsListener( (oldValue, newValue) -> towerUpdater
                        .updateTowerWeaponBank(Collections.unmodifiableList(newValue)))
                .addAbilitiesListener( (oldValue, newValue) -> towerUpdater
                        .updateTowerAbilityBank(Collections.unmodifiableList(newValue)))
                .addUpgradesListener( (oldValue, newValue) -> towerUpdater
                        .updateTowerUpgradeBank(Collections.unmodifiableList(newValue)))
                .addCostListener( (oldValue, newValue) -> towerUpdater
                        .updateTowerBuyPriceDisplay(newValue))
                .addSellAmountListener( (oldValue, newValue) -> towerUpdater
                        .updateTowerSellPriceDisplay(newValue))
                .addUnlockLevelListener( (oldValue, newValue) -> towerUpdater
                        .updateUnlockLevelDisplay(newValue));
    }

    @Override
    public EffectManagerController getEffectManagerController () {
        return abilityEffectManagerController;
    }
}
