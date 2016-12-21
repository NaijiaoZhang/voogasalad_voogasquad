package engine.tower;

import java.util.List;
import engine.TypeInitializer;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableProperty;
import engine.weapon.Weapon;


public interface TowerInitializer extends TypeInitializer {

    ObservableList<Integer> getWeapons ();

    ObservableList<Integer> getAbilities ();
    
    ObservableList<Integer> getUpgrades ();

    ObservableProperty<Double> getCost ();

    ObservableProperty<Double> getSellAmount ();

    ObservableProperty<Integer> getUnlockLevel ();

}
