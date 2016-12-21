package engine.observer;

import java.util.ArrayList;
import java.util.List;

public class AbstractObservable<U> implements Observable<U> {
    
    private List<Observer<Observable<U>, U>> observers;
    
    protected AbstractObservable() {
        this.observers = new ArrayList<Observer<Observable<U>, U>>();
    }
    
    
    @Override
    public void addObserver (Observer<Observable<U>, U> observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver (Observer<Observable<U>, U> observer) {
        observers.remove(observer);
    }

    //TODO - maybe put on a new thread
    @Override
    public void notifyObservers (U value) {
        observers.forEach(a -> a.update(this, value)); 
    }

}
