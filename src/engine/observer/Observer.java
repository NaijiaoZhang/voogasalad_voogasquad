package engine.observer;

//E = Object that extends Observable, U = value that Observable will pass, R = More specific class that implements observable so the Observer can use its methods as oppose to just the Observable class
public interface Observer<E extends Observable<U>, U> {
    
    <R extends E> void update(R observable, U value);
}
