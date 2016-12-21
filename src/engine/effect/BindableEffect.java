package engine.effect;


import java.util.function.BiConsumer;


public interface BindableEffect {

    EffectBuilder addTriggerClassListener (BiConsumer<String, String> listener);

    EffectBuilder addTriggerConditionGroovyListener (BiConsumer<String, String> listener);

    EffectBuilder addEffectGroovyListener (BiConsumer<String, String> listener);

    EffectBuilder removeTriggerClassListener (BiConsumer<String, String> listener);

    EffectBuilder removeTriggerConditionGroovyListener (BiConsumer<String, String> listener);

    EffectBuilder removeEffectGroovyListener (BiConsumer<String, String> listener);

}
