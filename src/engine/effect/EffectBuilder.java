package engine.effect;

import java.lang.reflect.Method;
import java.util.List;
import engine.TypeBuilder;
import engine.effect.depreciated.EffectFunction;
import engine.tower.BindableTower;
import engine.tower.Tower;
import engine.tower.TowerBuilder;


public interface EffectBuilder extends TypeBuilder<Effect, EffectBuilder>, BindableEffect { //TODO - Add bindable interface

    EffectBuilder buildTriggerClass (String triggerClass);

    EffectBuilder buildTriggerConditionGroovy (String triggerConditionGroovy);

    EffectBuilder buildEffectGroovy (String effectGroovy);

}
