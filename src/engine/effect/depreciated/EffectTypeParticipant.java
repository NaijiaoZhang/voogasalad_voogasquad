package engine.effect.depreciated;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;

public class EffectTypeParticipant implements BindableEffectParticipant {
    private ObservableProperty<Class<?>> participantClass;
    private ObservableList<EffectFunction> participantConditions;
    private ObservableProperty<Method> participantMethod;

    
    EffectTypeParticipant(Class<?> participantClass, List<EffectFunction> participantConditions, Method participantMethod) {
        this.participantClass = new ObservableObjectProperty<Class<?>>(participantClass);
        this.participantConditions = new ObservableListProperty<EffectFunction>(participantConditions);
        this.participantMethod = new ObservableObjectProperty<Method>(participantMethod);
    }
    
    @Override
    public void addParticipantClassListener(BiConsumer<Class<?>, Class<?>> listener) {
            participantClass.addListener(listener);
    }
    
    @Override
    public void addParticipantConditionsListener(BiConsumer<List<EffectFunction>, List<EffectFunction>> listener) {
        participantConditions.addListener(listener);
    }
    
    @Override
    public void addParticipantMethodListener(BiConsumer<Method, Method> listener) {
        participantMethod.addListener(listener);
    }
    
    @Override
    public void removeParticipantClassListener(BiConsumer<Class<?>, Class<?>> listener) {
        participantClass.removeListener(listener);
}

    @Override
    public void removeParticipantConditionsListener(BiConsumer<List<EffectFunction>, List<EffectFunction>> listener) {
        participantConditions.removeListener(listener);
    }
    
    @Override
    public void removeParticipantMethodListener(BiConsumer<Method, Method> listener) {
        participantMethod.removeListener(listener);
    }

    @Override
    public void setParticipantClass (Class<?> participantClass) {
        this.participantClass.setProperty(participantClass);
    }

    @Override
    public void addParticipantCondition (EffectFunction participantConditions) {
        this.participantConditions.add(participantConditions);
    }

    @Override
    public void removeParticipantCondition (EffectFunction participantConditions) {
        this.participantConditions.remove(participantConditions);
    }
    
    @Override
    public void setParticipantConditions (List<EffectFunction> participantConditions) {
        this.participantConditions.setProperty(participantConditions);
    }
    
    @Override
    public void setParticipantMethod (Method participantMethod) {
        this.participantMethod.setProperty(participantMethod);
    }

    @Override
    public Class<?> getParticipantClass () {
        return participantClass.getProperty();
    }

    @Override
    public List<EffectFunction> getParticipantConditions () {
        return participantConditions.getProperty();
    }

    @Override
    public Method getParticipantMethod () {
        return participantMethod.getProperty();
    }
}
