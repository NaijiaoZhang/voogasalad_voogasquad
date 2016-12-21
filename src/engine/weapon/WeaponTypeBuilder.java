package engine.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import engine.AbstractTypeBuilder;
import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;
import engine.weapon.Weapon;

public class WeaponTypeBuilder extends AbstractTypeBuilder<Weapon, WeaponBuilder> implements WeaponBuilder, WeaponInitializer {
    
     public static final String DEFAULT_NAME = "New Weapon";
     public static final String DEFAULT_IMAGE_PATH = "Images/penguin.jpg";
     public static final double DEFAULT_SIZE = 1;
     public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";
     public static final List<Integer> DEFAULT_WEAPONS = Arrays.stream(new Integer[]{}).collect(Collectors.toList());
     public static final double DEFAULT_FIRE_RATE = 15;
     public static final String DEFAULT_TRAJECTORY = "Straight";
     public static final List<Integer> DEFAULT_EFFECTS = Arrays.stream(new Integer[]{0,1}).collect(Collectors.toList());
     public static final double DEFAULT_SPEED = 10;
     public static final double DEFAULT_RANGE = 50;
     
     private ObservableList<Integer> targets;
     private ObservableProperty<Double> reloadTime;
     private ObservableProperty<String> trajectory;
     private ObservableList<Integer> effects;
     private ObservableProperty<Double> speed;
     private ObservableProperty<Double> range;
     
     public WeaponTypeBuilder() {
         super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
     }
     
     @Override
     public WeaponBuilder buildEffects (Integer ... effects) {
         return buildTargets(effects);
     }
    
     @Override
     public WeaponBuilder buildTargets (Integer ... targets) {
         return buildTargets(Arrays.stream(targets).collect(Collectors.toList()));
     }

     @Override
     public WeaponBuilder buildTargets (List<Integer> targets) {
         this.targets.setProperty(targets);
         return this;
     }
     
    @Override
    public WeaponBuilder buildReloadTime(double fireRate) {
        this.reloadTime.setProperty(fireRate);
        return this;
    }
    
    @Override
    public WeaponBuilder buildTrajectory(String trajectory) {
        this.trajectory.setProperty(trajectory);
        return this;
    }
    
    @Override
    public WeaponBuilder buildEffects(List<Integer> effect) {
        this.effects.setProperty(effect);
        return this;
    }

    @Override
    public WeaponBuilder buildSpeed(double speed) {
        this.speed.setProperty(speed);
        return this;
    }
    
    @Override
    public WeaponBuilder buildRange(double range) {
        this.range.setProperty(range);
        return this;
    }
    
    @Override
    protected Weapon create () {
        return new WeaponType(this);
    }

    @Override
    public ObservableList<Integer> getTargets () {
        return targets;
    }
    
    @Override
    public ObservableProperty<Double> getReloadTime () {
        return reloadTime;
    }

    @Override
    public ObservableProperty<String> getTrajectory () {
        return trajectory;
    }

    @Override
    public ObservableList<Integer> getEffect () {
        return effects;
    }
    
    @Override
    public ObservableProperty<Double> getSpeed () {
        return speed;
    }
    
    @Override
    public ObservableProperty<Double> getRange () {
        return range;
    }

    @Override
    protected void restoreTypeDefaults () {
        this.targets = new ObservableListProperty<Integer>(DEFAULT_WEAPONS);
        this.reloadTime = new ObservableObjectProperty<Double>(DEFAULT_FIRE_RATE);
        this.trajectory = new ObservableObjectProperty<String>(DEFAULT_TRAJECTORY);
        this.effects = new ObservableListProperty<Integer>(DEFAULT_EFFECTS);
        this.speed = new ObservableObjectProperty<Double>(DEFAULT_SPEED);
        this.range = new ObservableObjectProperty<Double>(DEFAULT_RANGE);
    }

    @Override
    protected WeaponBuilder getThis () {
        return this;
    }
    

    @Override
    public WeaponBuilder addTargetsListener (BiConsumer<List<Integer>, List<Integer>> listener) {
        targets.addListener(listener);
        return this;
    }
    
    @Override
    public WeaponBuilder addReloadTimeListener(BiConsumer<Double, Double> listener) {
        reloadTime.addListener(listener);
        return this;
    }
    
    @Override
    public WeaponBuilder addTrajectoryListener(BiConsumer<String, String> listener) {
        trajectory.addListener(listener);
        return this;
    }
    
    @Override
    public WeaponBuilder addEffectsListener(BiConsumer<List<Integer>, List<Integer>> listener) {
        effects.addListener(listener);
        return this;
    }

    @Override
    public WeaponBuilder addSpeedListener(BiConsumer<Double, Double> listener) {
        speed.addListener(listener);
        return this;
    }
    
    @Override
    public WeaponBuilder addRangeListener(BiConsumer<Double, Double> listener) {
        range.addListener(listener);
        return this;
    }

    @Override
    protected WeaponBuilder copyType (Weapon type) {
        return this
        .buildEffects(type.getEffects())
        .buildReloadTime(type.getReloadTime())
        .buildRange(type.getRange())
        .buildSpeed(type.getSpeed())
        .buildTrajectory(type.getTrajectory())
        .buildTargets(type.getTargets());  
    }
    
}
