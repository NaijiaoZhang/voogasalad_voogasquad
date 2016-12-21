package engine.observer;

import java.util.Map;


public class ObservableMapProperty<E, V> extends ObservableObjectProperty<Map<E, V>>
        implements ObservableMap<E, V> {

    public ObservableMapProperty (Map<E, V> value) {
        super(value);
    }

    @Override
	public void put(E key, V value) {
	    getProperty().put(key, value);
	    notifyListenersAndObservers(getProperty(), getProperty());
}

    @Override
    public void remove (E key) {
        getProperty().remove(key);
        notifyListenersAndObservers(getProperty(), getProperty());
    }

}
