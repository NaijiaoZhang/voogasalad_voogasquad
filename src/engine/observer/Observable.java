package engine.observer;

//U = value that is passed back
public interface Observable<U> {

    void addObserver (Observer<Observable<U>,U> observer);

    void deleteObserver (Observer<Observable<U>, U> observer);

    void notifyObservers (U value);
}
