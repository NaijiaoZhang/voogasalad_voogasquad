package engine.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObservableListProperty<E> extends ObservableObjectProperty<List<E>> implements ObservableList<E> {

    public ObservableListProperty(List<E> value) {
        super(value);
    }
    
    public ObservableListProperty() {
        super(new ArrayList<E>());
    }
    
    @Override
    public void add(E value) {
        getProperty().add(value);
        notifyListenersAndObservers(Collections.unmodifiableList(getProperty()), Collections.unmodifiableList(getProperty()));
    }

    @Override
    public void remove(E value) {
        getProperty().removeIf(a -> a.equals(value));
        notifyListenersAndObservers(Collections.unmodifiableList(getProperty()), Collections.unmodifiableList(getProperty()));
    }

    @Override
    public void clear () {
        getProperty().clear();
        notifyListenersAndObservers(Collections.unmodifiableList(getProperty()), Collections.unmodifiableList(getProperty()));
    }
    
}
