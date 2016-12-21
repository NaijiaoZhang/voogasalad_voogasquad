package engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import engine.effect.ReflectionException;
import engine.observer.AbstractObservable;
import engine.observer.ObservableMap;
import engine.observer.ObservableMapProperty;



public abstract class AbstractTypeManager<E extends Type> extends AbstractObservable<MethodData<Object>>
        implements Manager<E> {
    private static final String INVALID_REFLECTION_CALL = "Invalid reflection call";
    
    private ObservableMap<Integer, E> data;

    protected AbstractTypeManager () {
        this.data = new ObservableMapProperty<Integer, E>(new HashMap<Integer, E>());
    }

    @Override
    public int addEntry (E entry) {
        data.put(entry.getId(), entry);
        return entry.getId();
    }

    @Override
    public void removeEntry (int id) {
        data.remove(id);
        notifyObservers(new MethodObjectData<Object>("RemoveEntry", id));
    }

    @Override
    public void applyToAllEntities (Consumer<E> entry) {
        data.getProperty().values().stream().forEach(entry);
    }

    @Override
    public void addEntitiesListener (BiConsumer<Map<Integer, E>, Map<Integer, E>> listener) {
        data.addListener(listener);
    }

    @Override
    public List<Integer> getEntityIds () {
        return Collections.unmodifiableList(new ArrayList<Integer>(data.getProperty().keySet()));
    }
    
    @Override
    public void setEntities(Map<Integer, E> entities) {
    	this.data.setProperty(entities);
        //this.data = new ObservableMapProperty<Integer, E>(entities);
    }
    
    @Override
    public int getMaxId() {
        return getEntityIds().isEmpty() ? -1 : Collections.max(getEntityIds());
    }

    // protected <U> U getFromEntity(Supplier<U> getter) {
    // return getter.get();
    // }
    //
    // protected <U> void setForEntity(Consumer<U> setter, U newValue) {
    // setter.accept(newValue);
    // //notifyObservers(activeId);
    // }

    /*
     * public <U> Consumer<U> setForActiveEntity(Consumer<U> setter, U newValue) {
     * //Apply Type::setName to activeEntity
     * Consumer<U> blahtest = e - setter.accept(newValue);; //.setName(c); // Type::setName;
     * List<E> tester = new ArrayList<E>();
     * tester.forEach(setter);
     * Consumer<U> activeFunc = c -> getActiveEntity()::setter;
     * Consumer<AbstractTypeManager> eblah = c -> c.setForActiveEntity(getActiveEntity()::setter)
     * }
     */

    @Override // TODO - hide in interface
    public E getEntity (int index) {
        return data.getProperty().get(index);
    }

    /*
     * public void activate(int ... ids) {
     * activeEntities.clear();
     * Arrays.asList(ids).stream().map(data::get).forEach(activeEntities::add);
     * }
     * 
     * protected void applyToActive(Consumer<E> function) {
     * activeEntities.stream().forEach(function);
     * }
     */
    // Method downPolymorphic = object.getClass().getMethod("visit",
    // new Class[] { object.getClass() });
    //
    // if (downPolymorphic == null) {
    // defaultVisit(object);
    // } else {
    // downPolymorphic.invoke(this, new Object[] {object});
    // }
    // TODO - error might occur due to taking in a VisitableManager
    // TODO - get specific interface
    @Override
    public <U extends VisitableManager<MethodData<Object>>> void visitManager (U visitableManager,
                                                                          MethodData<Object> dataMethod) throws ReflectionException {
        try {
            Method visitMethod =
                    this.getClass().getMethod("visit" + dataMethod.getMethod(),
                                              new Class[] { visitableManager.getClass().getInterfaces()[0], Integer.class });
            visitMethod.invoke(this, new Object[] { visitableManager, dataMethod.getValue() });
        }
        catch (NoSuchMethodException | IllegalArgumentException  | InvocationTargetException e) {
            // This means that the class does not depend on the visitor and so does not have the subsequent handling methods (Not an Error)
            // This allows for the class to dynamically handle additional visitable objects, without having to make a method for each one in very visitor
        	return;
        }
        catch (SecurityException | IllegalAccessException e) {
                throw new ReflectionException(e, INVALID_REFLECTION_CALL);
        }
    }

    @Override
    public <U extends VisitorManager<MethodData<Object>>> void accept (U visitor,
                                                                  MethodData<Object> methodData) {
        visitor.visitManager(this, methodData);
    }
    
    @Override
    public Map<Integer, E> getEntities() {
        return data.getProperty();
    }

}
