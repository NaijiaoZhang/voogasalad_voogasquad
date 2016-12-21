package engine.effect.depreciated;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;


public interface BindableEffectParticipant extends EffectParticipant{

    void addParticipantClassListener (BiConsumer<Class<?>, Class<?>> listener);

    void addParticipantConditionsListener (BiConsumer<List<EffectFunction>, List<EffectFunction>> listener);

    void addParticipantMethodListener (BiConsumer<Method, Method> listener);

    void removeParticipantClassListener (BiConsumer<Class<?>, Class<?>> listener);

    void removeParticipantConditionsListener (BiConsumer<List<EffectFunction>, List<EffectFunction>> listener);

    void removeParticipantMethodListener (BiConsumer<Method, Method> listener);

}
