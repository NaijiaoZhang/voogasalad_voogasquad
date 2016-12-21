package engine.path;

import java.util.List;
import java.util.function.BiConsumer;
import engine.BindableType;


public interface BindablePath extends BindableType<PathBuilder>{

    PathBuilder addTypeListener (BiConsumer<PathOption, PathOption> listener);

    PathBuilder addCoordinatesListener (BiConsumer<List<Coordinate<Integer>>, List<Coordinate<Integer>>> listener);
    
    PathBuilder addGridRowsListener (BiConsumer<Integer, Integer> listener);

    PathBuilder addGridColumnsListener (BiConsumer<Integer, Integer> listener);
    
}
