package gameplayer.model.effect;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import engine.effect.Effect;
import engine.observer.ObservableProperty;

public class GameEffect {
//
//    private ObservableProperty<String> name;
//    private ObservableProperty<String> imagePath;
//    private ObservableProperty<Double> size;
//    private ObservableProperty<String> triggerClass;
//    private ObservableProperty<String> triggerConditionGroovy;
//    private ObservableProperty<String> effectGroovy;
    
    private final String triggerName;
    private final String encompassingClassName;
    private String name;
    private String imagePath;
    private Double size;
    private String triggerClass;
    private String triggerConditionGroovy;
    private String effectGroovy; 
    private GroovyExecutor groovyExecutor;
    
    public GameEffect(Effect effect, GroovyExecutor groovyExecutor, String triggerName, String encompassingClassName) {
        this.groovyExecutor = groovyExecutor;
        this.name = effect.getName();
        this.imagePath = effect.getImagePath();
        this.size = effect.getSize();
        this.triggerClass = effect.getTriggerClass();
        this.triggerConditionGroovy = effect.getTriggerConditionGroovy();
        this.effectGroovy = effect.getEffectGroovy();
        this.triggerName = triggerName;
        this.encompassingClassName = encompassingClassName;
    }
    
    public void execute() {
        if(executeCondition()) {
            executeEffect();
        }
    }
    
    private boolean executeCondition() {
        return groovyExecutor.execute(triggerConditionGroovy, Boolean.class);
    }
    
    private void executeEffect() {
        groovyExecutor.execute(effectGroovy);
    }
    
    public void addTrigger(Object value) {
        groovyExecutor.addVariable(triggerName, value);
    }
    
    public void addEncompassingClass(Object value) {
        groovyExecutor.addVariable(encompassingClassName, value);
    }
    
    public String getTriggerClass(){
    	return this.triggerClass;
    }
    
}
