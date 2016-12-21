package engine.effect.depreciated;

import java.lang.reflect.Method;
import java.util.List;


public interface EffectParticipant  {

    void setParticipantClass (Class<?> participantClass);

    void addParticipantCondition (EffectFunction participantConditions);

    void removeParticipantCondition (EffectFunction participantConditions);

    void setParticipantConditions (List<EffectFunction> participantConditions);

    void setParticipantMethod (Method participantMethod);

    Class<?> getParticipantClass ();

    List<EffectFunction> getParticipantConditions ();
    
    Method getParticipantMethod ();
}
