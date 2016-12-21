package gameplayer.model.effect;

import java.util.Map;
import java.util.Map.Entry;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GroovyExecutor {
    private static final String INCORRECT_RETURN_TYPE = "Groovy command did not return an instance of %s";
    private static final String INVALID_COMMAND = "Invalid Groovy command %s";
    private ScriptEngine engine;
        
    public GroovyExecutor() {
        this.engine = new ScriptEngineManager().getEngineByName("groovy");
    }
    
    public Object execute(String groovyCommand) throws GroovyException {
        try {
            return engine.eval(groovyCommand);
            }

        catch (ScriptException e) {
            throw new GroovyException(e, INVALID_COMMAND, groovyCommand);
        }
    }
    
    public <E> E execute(String groovyCommand, Class<E> returnType) throws GroovyException {
        try {
            Object blah = execute(groovyCommand);
            returnType.cast(blah);
            return returnType.cast(execute(groovyCommand));
        }
        catch (ClassCastException e) {
            throw new GroovyException(e, INCORRECT_RETURN_TYPE, returnType);
        }
    }
    
    public void addVariables(Map<String, Object> variableMap) {
        variableMap.entrySet().forEach(a -> addVariable(a.getKey(), a.getValue()));
    }
    
    public void addVariable(String key, Object value) {
        engine.put(key, value);
    }
}
