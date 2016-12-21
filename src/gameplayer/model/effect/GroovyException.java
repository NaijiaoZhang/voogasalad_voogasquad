package gameplayer.model.effect;

/**
 * This utility provides convenience methods for dynamically calling methods and allocating classes. 
 * 
 * It simplifies some of Java's reflection API and fixes some issues.
 * 
 * @author Robert C. Duvall
 * @author Sean Hudson
 */
public class GroovyException extends RuntimeException{
    // for serialization
    private static final long serialVersionUID = 1L;
    
    /**
     * Create an exception based on an issue in our code.
     */
    public GroovyException (String message, Object ... args) {
        super(format(message, args));
    }
    
    /**
     * Create an exception based on a caught exception with a different message.
     */
    public GroovyException (Throwable cause, String message, Object ... args) {
        super(format(message, args), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public GroovyException (Throwable cause) {
        super(cause);
    }
    
    // remove duplicate code, also placeholder for future improvements (like logging)
    private static String format (String message, Object ... args) {
        return String.format(message, args);
    }
    
}
