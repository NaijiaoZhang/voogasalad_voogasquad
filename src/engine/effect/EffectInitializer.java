package engine.effect;

import engine.TypeInitializer;
import engine.observer.ObservableProperty;


public interface EffectInitializer extends TypeInitializer {

    ObservableProperty<String> getTriggerClass ();

    ObservableProperty<String> getTriggerConditionGroovy ();

    ObservableProperty<String> getEffectGroovy ();

}
