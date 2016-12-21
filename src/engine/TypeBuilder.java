package engine;

import java.util.function.BiConsumer;

public interface TypeBuilder<E extends Type, R extends TypeBuilder<E, R>> extends BindableType<R> {

    boolean setNextId(int id);
    
    R buildName (String name);

    R buildImagePath (String imagePath);

    R buildSize (double size);
    
    R buildId (int id);
    
    R buildSound (String SoundPath);
    
    R copy(E type);

    E build ();

}
