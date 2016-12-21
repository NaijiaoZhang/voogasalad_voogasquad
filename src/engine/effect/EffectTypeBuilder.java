package engine.effect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import engine.AbstractTypeBuilder;
import engine.effect.depreciated.BindableEffectParticipant;
import engine.effect.depreciated.EffectFunction;
import engine.effect.depreciated.EffectParticipant;
import engine.effect.depreciated.EffectTypeParticipant;
import engine.observer.ObservableList;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;


public class EffectTypeBuilder extends AbstractTypeBuilder<Effect, EffectBuilder> implements EffectBuilder, EffectInitializer {
    
    public static final String DEFAULT_NAME = "New Effect";
    public static final String DEFAULT_IMAGE_PATH = "Images/butterfly.png";
    public static final double DEFAULT_SIZE = 1;
    public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";
    public static final String DEFAULT_TRIGGER_CLASS = "gameplayer.model.enemy.Enemy";

    public static final String DEFAULT_TRIGGER_CONDITION_GROOVY = "5 == 5";
    public static final String DEFAULT_EFFECT_GROOVY = "collider.setHealth(collider.getHealth() - 2)";


    private ObservableProperty<String> triggerClass;
    private ObservableProperty<String> triggerConditionGroovy;
    private ObservableProperty<String> effectGroovy;

    
    public EffectTypeBuilder () {
        super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
    }


    @Override
    public EffectBuilder addTriggerClassListener (BiConsumer<String, String> listener) {
        triggerClass.addListener(listener);
        return this;
    }


    @Override
    public EffectBuilder addTriggerConditionGroovyListener (BiConsumer<String, String> listener) {
        triggerConditionGroovy.addListener(listener);
        return this;
    }


    @Override
    public EffectBuilder addEffectGroovyListener (BiConsumer<String, String> listener) {
        effectGroovy.addListener(listener);
        return this;
    }


    @Override
    public EffectBuilder removeTriggerClassListener (BiConsumer<String, String> listener) {
        triggerClass.removeListener(listener);
        return this;
    }


    @Override
    public EffectBuilder removeTriggerConditionGroovyListener (BiConsumer<String, String> listener) {
        triggerConditionGroovy.removeListener(listener);
        return this;
    }


    @Override
    public EffectBuilder removeEffectGroovyListener (BiConsumer<String, String> listener) {
        effectGroovy.removeListener(listener);
        return this;
    }


    @Override
    public ObservableProperty<String> getTriggerClass () {
        return triggerClass;
    }


    @Override
    public ObservableProperty<String> getTriggerConditionGroovy () {
        return triggerConditionGroovy;
    }


    @Override
    public ObservableProperty<String> getEffectGroovy () {
        return effectGroovy;
    }


    @Override
    public EffectBuilder buildTriggerClass (String triggerClass) {
        this.triggerClass.setProperty(triggerClass);
        return this;
    }


    @Override
    public EffectBuilder buildTriggerConditionGroovy (String triggerConditionGroovy) {
        this.triggerConditionGroovy.setProperty(triggerConditionGroovy);
        return this;
    }


    @Override
    public EffectBuilder buildEffectGroovy (String effectGroovy) {
        this.effectGroovy.setProperty(effectGroovy);
        return this;
    }


    @Override
    protected Effect create () {
        return new EffectType(this);
    }


    @Override
    protected void restoreTypeDefaults () {
        this.triggerClass = new ObservableObjectProperty<String>(DEFAULT_TRIGGER_CLASS);
        this.triggerConditionGroovy = new ObservableObjectProperty<String>(DEFAULT_TRIGGER_CONDITION_GROOVY);
        this.effectGroovy = new ObservableObjectProperty<String>(DEFAULT_EFFECT_GROOVY);
    }


    @Override
    protected EffectBuilder getThis () {
        return this;
    }


    @Override
    protected EffectBuilder copyType (Effect type) {
        return this
                .buildEffectGroovy(type.getEffectGroovy())
                .buildTriggerClass(type.getTriggerClass())
                .buildTriggerConditionGroovy(type.getTriggerConditionGroovy());
    }

}