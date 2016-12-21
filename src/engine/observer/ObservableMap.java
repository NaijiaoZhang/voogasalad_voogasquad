package engine.observer;
import java.util.Map;

public interface ObservableMap<E, V> extends ObservableProperty<Map<E,V>> {
	
	void put(E key, V value);
    void remove(E key);
}
