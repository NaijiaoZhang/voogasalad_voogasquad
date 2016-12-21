package engine.ability;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import engine.AbstractTypeBuilder;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;
import engine.tower.Tower;
import engine.tower.TowerType;
import engine.weapon.Weapon;

public class AbilityTypeBuilder extends AbstractTypeBuilder<Ability, AbilityBuilder> implements AbilityBuilder, AbilityInitializer {
    
     public static final String DEFAULT_NAME = "New Weapon";
     public static final String DEFAULT_IMAGE_PATH = "Images.penguin.jpg";
     public static final double DEFAULT_SIZE = 1;
     public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";
     public static final double DEFAULT_RATE = 2;
     public static final String DEFAULT_EFFECT = "";
     
     
     private ObservableProperty<Double> rate;
     private ObservableProperty<String> effect;
     
     public AbilityTypeBuilder() {
         super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
     }
    
    @Override
    public AbilityBuilder buildRate(double rate) {
        this.rate.setProperty(rate);
        return this;
    }
    
    @Override
    public AbilityBuilder buildEffect(String effect) {
        this.effect.setProperty(effect);
        return this;
    }
    
    @Override
    protected Ability create () {
        return new AbilityType(this);
    }

    @Override
    public ObservableProperty<Double> getRate () {
        return rate;
    }

    @Override
    public ObservableProperty<String> getEffect () {
        return effect;
    }

    @Override
    protected void restoreTypeDefaults () {
        this.rate = new ObservableObjectProperty<Double>(DEFAULT_RATE);
        this.effect = new ObservableObjectProperty<String>(DEFAULT_EFFECT);
    }

    @Override
    protected AbilityBuilder getThis () {
        return this;
    }
    
    @Override
    public AbilityBuilder addFireRateListener(BiConsumer<Double, Double> listener) {
        rate.addListener(listener);
        return this;
    }
    
    @Override
    public AbilityBuilder addEffectListener(BiConsumer<String, String> listener) {
        effect.addListener(listener);
        return this;
    }

    @Override
    protected AbilityBuilder copyType (Ability type) {
        return this
                .buildEffect(type.getEffect())
                .buildRate(type.getRate());
    }
    
}
