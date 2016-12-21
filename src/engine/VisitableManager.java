package engine;

import engine.observer.Observable;

//U = object that is being passed in, E = some Visiting Manager
public interface VisitableManager<E>{

    <U extends VisitorManager<E>> void accept (U visitor, E value);
}
