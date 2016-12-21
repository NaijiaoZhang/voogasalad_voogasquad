package engine.effect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import engine.AbstractTypeManager;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;

public class EffectTypeManager extends AbstractTypeManager<Effect> implements EffectManager {
    //private Map<String, Class<?>> annotatedClasses;
    private Map<Class<?>, List<Method>> annotatedClassMethods; //Return type : methods of that type
    private List<Field> effectAccessibleData;
    private ObservableProperty<Class<?>> activeClass;
    
    EffectTypeManager(Map<Class<?>, List<Method>> annotatedClassMethods, List<Field> effectAccessibleData) {
        this.annotatedClassMethods = annotatedClassMethods;
        this.effectAccessibleData = effectAccessibleData;
        this.activeClass = new ObservableObjectProperty<Class<?>>(null);
    }
    
    @Override
    public void setActiveClass(Class<?> activeClass) {
        this.activeClass.setProperty(activeClass);
    }
    
    @Override
    public void addActiveClassListener (BiConsumer<Class<?>, Class<?>> listener) {
        activeClass.addListener(listener);
    }
    
    @Override
    public Collection<Class<?>> getAnnotatedClasses() {
        //return new ArrayList<String>(annotatedClassMethods.keySet());
        return annotatedClassMethods.keySet();
    }
    
    @Override
    public List<Method> getAnnotatedClassMethods(Class<?> className) {
        return annotatedClassMethods.get(className);
    }

    @Override
    public List<Field> getAnnotatedDataObjects () {
        return effectAccessibleData;
    }
    
    /* (non-Javadoc)
     * @see engine.effect.EffectManager#add(java.lang.Class)
     */
    
    //TODO - Stream this
//    public List<Method> getAnnotatedMethods(Class<?> annotatedClass) {
//        List<Method> annotatedMethods = new ArrayList<Method>();
//        annotatedClass.getAnnotationsByType(annotationClass)
//        for (Method m : annotatedClass.getMethods()) {   //.getAnnotationsByType(EffectMethod.class)) {
//            if (m.isAnnotationPresent(EffectMethod.class)) {
//               System.out.println(m.toString());
//               annotatedMethods.add(m);
//               //Method testM = new Method("engine.effect.Enemy.setyDirection(int)");
//            }
//         }
//        return annotatedMethods;
//    }
    
    /* (non-Javadoc)
     * @see engine.effect.EffectManager#generateAnnotatedMethods(java.lang.Class, java.lang.Class)
     */

    
    /* (non-Javadoc)
     * @see engine.effect.EffectManager#getAnnotatedClassMethods(java.lang.String)
     */
    
//    private <T extends Annotation> List<Method> getAnnotatedMethods(Class<?> annotatedClass, Class<T> annotationType, List<Method> annotatedMethods) {
//        annotatedMethods.addAll(Stream.of(annotatedClass.getMethods()).filter(a -> a.isAnnotationPresent(annotationType)).collect(Collectors.toList()));
//        Class<?> superclass = annotatedClass.getSuperclass();
//        if (superclass != null) {
//            return getAnnotatedMethods(superclass, annotationType, annotatedMethods);
//        } else {
//            return annotatedMethods;
//        }
//    }
    
    /* (non-Javadoc)
     * @see engine.effect.EffectManager#getAnnotatedClasses()
     */
    
//    public static void main (String[] args) {
//        EffectManager test = new EffectManager();
//        test.add(Enemy.class);
//        test.printTest();
//    }
}
