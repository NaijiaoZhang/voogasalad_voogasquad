package engine;

import java.util.function.BiConsumer;

public interface BindableType<R extends BindableType<R>> {

    R addNameListener (BiConsumer<String, String> listener);

    R addImagePathListener (BiConsumer<String, String> listener);

    R addSizeListener (BiConsumer<Double, Double> listener);
    
    R addSoundListener (BiConsumer<String, String> listener);
}
