package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.enemy.EnemyManager;
import engine.enemy.EnemyTypeManager;
import engine.level.Level;
import engine.observer.AbstractObservable;
import engine.observer.Observable;
import engine.observer.Observer;
import engine.path.Path;
import engine.tower.Tower;
import engine.tower.TowerTypeManager;
import engine.weapon.Weapon;
//TODO - maybe implement observer pattern with each manager as both observer/observable
//TODO - use generics
//TODO - override each manager's hashcode (to make it unique) or give managers an index (support multiple);
public class ManagerTypeMediator implements ManagerMediator{
    
    private Map<Class<?>, Manager<? extends Type>> allManagers;
   
    public ManagerTypeMediator() {
        allManagers = new HashMap<Class<?>, Manager<? extends Type>>();
    }
    
//    @Override
//    public <R extends Manager<? extends Type>> void update (R observable, MethodData<Object> value) {
//        allManagers.values().forEach(a -> a.visitManager(observable, value));
//    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R extends Observable<MethodData<Object>>> void update (R observable,
                                                                   MethodData<Object> value) {
        allManagers.values().forEach(a -> a.visitManager((VisitableManager<MethodData<Object>>) observable, value));        
    }
    
//    @Override
//    public <R extends Manager<? extends Type>> void update (R observable, MethodData<?> value) {
//        // TODO Auto-generated method stub
//        
//    }
    
    @Override
    public void addManager(Manager<? extends Type> manager) {
        allManagers.put(manager.getClass().getInterfaces()[0], manager);
    }
    
    @Override
    public void removeManager(Manager<? extends Type> manager) {
        allManagers.remove(manager.getClass());
    }

    @Override
    public <R> R getManager(Class<R> key) {
        return key.cast(allManagers.get(key));
    }

    
//    @Override
//    public Enemy getEnemy(int id) {
//        //BiFunction<Manager<Enemy>, Integer, Enemy> test = (a, b) -> a.getEntity(b);
//        //allManagers.forEach(a -> getter.get());
//        ///Manager<Enemy> test = new EnemyTypeManager(null);
//        //Function<? super Manager<? extends Type>> test;
//        Consumer<Integer> test = Manager<Enemy>::getEntity;
//        //Function<? super Manager<? extends Type>, E>;
//        //getEntry(Manager<Enemy>::getActiveEntity);
//        //allManagers.stream().map(mapper)
//        
//        return enemyManager.getEntity(id);
//    }
//    
//    //return E
//    public <E> void getEntry(Function<Manager<? extends Type>, E> func) {
//        //Function<? super Manager<? extends Type>, ?> getter = a -> a.getEntity(id);   //Manager<? extends Type>::getActiveId;
//        //allManagers.forEach(getter);
//        Consumer<? super Manager<? extends Type>> test = Manager::getActiveEntity;
//        Function<Manager<?>, ?> testParams = a -> a.getEntity(10);
//        //Consumer<? super Manager<? extends Type>> testParams = Manager::getEntity;
//        //Consumer<? extends Manager<? extends Type>> test = EnemyManager::getActiveEntity;
//        allManagers.add(new TowerTypeManager(null));
//        List<Manager<Enemy>> listTest = new ArrayList<Manager<Enemy>>();
//        listTest.add(new EnemyTypeManager(null));
//        //List<Manager<Enemy>> listTest = new ArrayList<EnemyManager>();
//        //List<Number> polyTest = new ArrayList<Integer>();
//        allManagers.stream().forEach(test);
//        allManagers.stream().forEach(test); //.map(Manager<Enemy>::getActiveEntity).reduce((a, b) -> b).get();
//        
//    }
//    
//    
//    //TODO - Ask Duvall how to get around this. Chain of command variation
//    public <E extends Type, R> R applyToAll(Function<Manager<E>, R> func, Class<Manager<E>> receivingManager) {
//        Iterator<Manager<? extends Type>> iter = allManagers.iterator();
//        Manager<? extends Type> managerWildCard = null;
//        while(iter.hasNext()) {
//            if(iter.next().getClass().equals(receivingManager)) {
//                Manager<E> manager = receivingManager.cast(managerWildCard);
//                return func.apply(manager);
//            }
//           
////            try {
////               Manager<E> manager = clazz.cast(managerWildCard);
////               func.apply(manager);
////            }
////            catch (ClassCastException e) {
////                managerWildCard = iter.next();
////            }
//        }
//        return null;
//    }
//    
////    private <E extends Type> E testHelper(Manager<E> manager) {
////        return manager.getActiveEntity();
////    }
////    
////    private <E extends Manager<?>, R> R getEntityHelper(E manager, Function<E, R> func) {
////        manager.getClass();
////        return func.apply(manager);
////    }
//    
//    public static void main(String[] args) {
//        ManagerMediator testManager = new ManagerTypeMediator();
//        Manager<Enemy> testM = new EnemyTypeManager(null);
//        Consumer<? super Manager<? extends Type>> test = Manager::getActiveEntity;
//        //Function<? super Manager<? extends Type>, ?> getter = a -> a.getEntity(10);
//        System.out.println(test.toString());
//        //System.out.println(getter.toString());
//        System.out.println(testM);
//        VisitableManager<MethodData<Integer>> blah = new EnemyTypeManager(null);
//        System.out.print(blah.getClass().getName());
//        
//    }
//
//    //TODO - might have to get rid of the wild card
//    
//
//    
}
