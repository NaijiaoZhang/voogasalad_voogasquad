package engine.effect;

import engine.Type;
import engine.effect.depreciated.EffectParticipant;
import engine.observer.ObservableProperty;

public interface Effect extends Type{
    
    String getTriggerClass();
    
    String getTriggerConditionGroovy();
    
    String getEffectGroovy();
    
    void setTriggerClass(String triggerClass);
    
    void setTriggerConditionGroovy(String triggerConditionGroovy);
    
    void setEffectGroovy(String effectGroovy);

}
