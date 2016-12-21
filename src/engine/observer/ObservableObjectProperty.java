package engine.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleDoubleProperty;

public class ObservableObjectProperty<U> extends AbstractObservable<U> implements ObservableProperty<U>{
    
    private List<BiConsumer<U, U>> listeners;
    private U property;

    public ObservableObjectProperty(U value) {
        this.property = value;
        this.listeners = new ArrayList<BiConsumer<U, U>>();
    }
    
    ObservableObjectProperty() {
        this(null);
    }
    
    @Override
    public U getProperty () {
        return property;
    }

    //TODO - create a temp
    @Override
    public void setProperty (U property) {
        U temp = this.property;
        this.property = property;
        notifyListenersAndObservers(temp, property);
    }
    
    @Override
    public void addListener (BiConsumer<U, U> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener (BiConsumer<U, U> listener) {
        listeners.remove(listener);
    }
    
    protected void notifyListenersAndObservers(U oldValue, U newValue) {
        listeners.forEach(a -> a.accept(oldValue, newValue));
        super.notifyObservers(newValue);
    }
    
    
    @Override
    public void notifyObservers (U value) {
        notifyListenersAndObservers(property, value);
    }
}
