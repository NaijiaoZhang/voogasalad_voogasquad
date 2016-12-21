package engine.level;

import engine.TypeInitializer;
import engine.level.wave.WaveManager;
import engine.observer.ObservableList;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public interface LevelInitializer extends TypeInitializer {
    ObservableList<Integer> getPaths ();

    ObservableObjectProperty<WaveManager> getWaves ();

    ObservableProperty<Double> getRewardHealth ();

    ObservableProperty<Double> getRewardMoney ();

    ObservableProperty<Double> getRewardScore ();

    ObservableProperty<Double> getDurationInSeconds ();

    ObservableProperty<Double> getLevelTime ();
}
