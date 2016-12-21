package engine.level.wave;

import engine.TypeInitializer;
import engine.observer.ObservableProperty;


public interface WaveInitializer extends TypeInitializer {
    ObservableProperty<Integer> getEnemyID ();

    ObservableProperty<Integer> getPathID ();

    ObservableProperty<Integer> getEnemyCount ();

    ObservableProperty<Double> getStartTime ();

    ObservableProperty<Double> getFrequency ();

}
