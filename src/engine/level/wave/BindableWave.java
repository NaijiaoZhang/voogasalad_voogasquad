package engine.level.wave;

import java.util.function.BiConsumer;
import engine.BindableType;


public interface BindableWave extends BindableType<WaveBuilder> {
    WaveBuilder addEnemyIDListener (BiConsumer<Integer, Integer> listener);

    WaveBuilder addPathIDListener (BiConsumer<Integer, Integer> listener);

    WaveBuilder addEnemyCountListener (BiConsumer<Integer, Integer> listener);

    WaveBuilder addStartTimeListener (BiConsumer<Double, Double> listener);

    WaveBuilder addFrequencyListener (BiConsumer<Double, Double> listener);
    
}
