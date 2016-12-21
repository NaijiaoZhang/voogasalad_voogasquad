package engine.ability;

import java.util.function.BiConsumer;
import engine.BindableType;


public interface BindableAbility extends BindableType<AbilityBuilder> {

    AbilityBuilder addFireRateListener (BiConsumer<Double, Double> listener);

    AbilityBuilder addEffectListener (BiConsumer<String, String> listener);

}
