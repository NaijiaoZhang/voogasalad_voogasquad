package engine;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import engine.weapon.WeaponBuilder;

public interface BindableManager<E> {

    void addEntitiesListener (BiConsumer<Map<Integer, E>, Map<Integer, E>> listener);
    
}
