package engine.weapon;

import java.util.List;
import java.util.function.BiConsumer;
import engine.BindableType;


public interface BindableWeapon extends BindableType<WeaponBuilder> {

    WeaponBuilder addTargetsListener (BiConsumer<List<Integer>, List<Integer>> listener);
    
    WeaponBuilder addReloadTimeListener (BiConsumer<Double, Double> listener);

    WeaponBuilder addTrajectoryListener (BiConsumer<String, String> listener);

    WeaponBuilder addEffectsListener (BiConsumer<List<Integer>, List<Integer>> listener);

    WeaponBuilder addSpeedListener(BiConsumer<Double, Double> listener);

    WeaponBuilder addRangeListener (BiConsumer<Double, Double> listener);

}
