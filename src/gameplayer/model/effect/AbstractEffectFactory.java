package gameplayer.model.effect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import engine.effect.Effect;
import engine.effect.EffectData;

public abstract class AbstractEffectFactory {
    public static final Class<? extends Annotation> EFFECT_DATA = EffectData.class;
    
    private String triggerVariableName;
    private String encompasingClassVariableName;
    private Map<String, Object> effectAccessibleData;
    
    AbstractEffectFactory(String triggerVariableName, String encompasingClassVariableName) {
//        loadDataIntoMap();
        this.effectAccessibleData = new HashMap<String, Object>();
        this.triggerVariableName = triggerVariableName;
        this.encompasingClassVariableName = encompasingClassVariableName;
        loadEffectParameters(triggerVariableName, encompasingClassVariableName);
    }
    
    private void loadEffectParameters(String... parameterNames) {
        Stream.of(parameterNames).forEach(a -> effectAccessibleData.put(a, new Object()));
    }
    
    public GameEffect create(Effect effect) {
        GroovyExecutor groovyExecutor = new GroovyExecutor();
        addEffectAccessibleData(groovyExecutor);
        return new GameEffect(effect, groovyExecutor, triggerVariableName, encompasingClassVariableName);
    }
    
    public Collection<String> getVariableNames() {
        return effectAccessibleData.keySet();
    }
    
//    private void loadDataIntoMap() {
//        effectAccessibleData.put(TRIGGER_NAME, null);
//        effectAccessibleData.put(ENCOMPASSING_CLASS_NAME, null);
//    }
    
    private void addEffectAccessibleData(GroovyExecutor groovyExecutor) {
        effectAccessibleData.entrySet().forEach(a -> {groovyExecutor.addVariable(a.getKey(), a.getValue()); System.out.println(a.getKey());});
    }
    
    protected void loadInSpecificValues() {
        Stream.of(getClass().getDeclaredFields()).filter(a -> a.isAnnotationPresent(EFFECT_DATA)).forEach(this::loadSpecificDataIntoMap);
    }    
    
    protected void loadSpecificDataIntoMap(Field variableField) {
        try {
            effectAccessibleData.put(variableField.getName(), variableField.get(this));
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
            return;
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
    }
        
}
