package engine;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import engine.observer.Observable;

public interface Manager<E extends Type> extends VisitorManager<MethodData<Object>>, VisitableManager<MethodData<Object>>, Observable<MethodData<Object>>, BindableManager<E> {

    int addEntry (E entry);

    void removeEntry (int id);
    
    E getEntity(int id);

    void applyToAllEntities (Consumer<E> entry);
    
    List<Integer> getEntityIds();
    
    Map<Integer, E> getEntities ();
    
    void setEntities(Map<Integer, E> entities);
    
    int getMaxId();

}
