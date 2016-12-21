package engine.settings;

import engine.TypeInitializer;
import engine.observer.ObservableList;
import engine.observer.ObservableProperty;
import engine.path.PathOption;


public interface GameModeInitializer extends TypeInitializer {
    ObservableList<String> getWinningConditions ();

    ObservableList<String> getLosingConditions ();

    ObservableProperty<Integer> getInitalLives ();

    ObservableProperty<Integer> getInitialMoney ();

    ObservableProperty<String> getGameType ();

    ObservableProperty<PathOption> getPathType ();

    ObservableProperty<Integer> getGridSize ();

    ObservableList<Integer> getPaths ();

}
