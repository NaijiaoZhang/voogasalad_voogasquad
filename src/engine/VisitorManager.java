package engine;

//E = object that is being passed in, U = Some VisitableManager
public interface VisitorManager<E> {
    <U extends VisitableManager<E>> void visitManager(U visitableManager, E value);
}
