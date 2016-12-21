package engine.tower;

import java.util.List;
import engine.Type;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.weapon.Weapon;



public interface Tower extends Type {

    void addUpgrade (int upgrade);

    void removeUpgrade (int upgrade);
    
    List<Integer> getUpgrades ();

    void addWeapon (int weapon);

    void removeWeapon (int weapon);

    List<Integer> getWeapons ();

    void removeAbility (int ability);

    void addAbility (int ability);

    List<Integer> getAbilities ();

    double getCost ();

    void setCost (double cost);

    double getSellAmount ();

    void setSellAmount (double sellAmount);

    int getUnlockLevel ();

    void setUnlockLevel (int unlockLevel);

}
