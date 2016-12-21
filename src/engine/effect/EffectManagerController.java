package engine.effect;

import java.util.List;
import authoring.editorview.collisioneffects.EffectUpdateView;
import engine.ManagerController;


public interface EffectManagerController extends ManagerController<EffectManager, EffectBuilder, Effect, EffectUpdateView> {

    List<String> getAvailableClasses ();

    List<String> getAvailableClassMethods (String selectedClass);
    
    List<String> getAvailableDataObjects ();
    
    void setAvailableClass(String selectedClass);
    
    String getTrigger(int effectID);

    String getCondition(int effectID);
    
    String getEffect(int effectID);
    
    void setTrigger (int effectID, String trigger);
    
    void setCondition(int effectID, String trigger);
    
    void setEffect(int effectID, String trigger);
    
    void addActiveClassListener(EffectUpdateView updateView) throws ClassNotFoundException;

}
