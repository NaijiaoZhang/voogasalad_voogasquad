package engine;

import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.level.Level;
import engine.observer.Observable;
import engine.observer.Observer;
import engine.path.Path;
import engine.tower.Tower;
import engine.weapon.Weapon;


public interface ManagerMediator extends Observer<Observable<MethodData<Object>>, MethodData<Object>> {//Observer<Manager<? extends Type>, MethodData<Object>> {

    void addManager(Manager<? extends Type> manager);

    void removeManager(Manager<? extends Type> manager);
    
    <R> R getManager (Class<R> key);

}
