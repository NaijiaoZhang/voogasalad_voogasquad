package engine.tower;

import java.util.List;
import java.util.function.BiConsumer;
import engine.BindableType;


public interface BindableTower extends BindableType<TowerBuilder> {

    TowerBuilder addWeaponsListener (BiConsumer<List<Integer>, List<Integer>> listener);
    
    TowerBuilder addAbilitiesListener (BiConsumer<List<Integer>, List<Integer>> listener);

    TowerBuilder addUpgradesListener (BiConsumer<List<Integer>, List<Integer>> listener);
    
    TowerBuilder addCostListener (BiConsumer<Double, Double> listener);

    TowerBuilder addSellAmountListener (BiConsumer<Double, Double> listener);

    TowerBuilder addUnlockLevelListener (BiConsumer<Integer, Integer> listener);

}
