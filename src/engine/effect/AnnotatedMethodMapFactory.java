package engine.effect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import com.sun.accessibility.internal.resources.accessibility;

public class AnnotatedMethodMapFactory extends AbstractAnnotatedFactory<Method>{
    public static final Class<? extends Annotation> ANNOTATION_TYPE = EffectMethod.class;
    

    @Override
    protected Collection<Method> applySearch (Reflections reflections) {
        return reflections.getMethodsAnnotatedWith(ANNOTATION_TYPE);
    }
    
    @Override
    public Map<Class<?>, List<Method>> create (String packageName) {
        return create(ANNOTATION_TYPE, packageName, new MethodAnnotationsScanner());
    }
    
//    private Class<?> getLeafClass(List<Class<?>> classHierarchy) {
//        classHierarchy.removeAll(classHierarchy.stream().filter(a -> (a.getSuperclass() != null))
//        .map(a -> a.getSuperclass()).collect(Collectors.toList()));
//        if(classHierarchy.size() == 0) {
//            return null;
//        }
//        return classHierarchy.get(0);
//    }
//    
//    private Class<?> getAbstractMostClass(Class<?> clazz) {
//        if (clazz.getSuperclass() != null) {
//            return getAbstractMostClass(clazz.getSuperclass());
//        }
//        return clazz;
//    }
    
    
//  @Override
//  public <T extends Annotation> List<Method> generateAnnotatedMethods(Class<?> annotatedClass, Class<T> annotationType) {
//      return Stream.of(annotatedClass.getMethods()).filter(a -> a.isAnnotationPresent(annotationType)).collect(Collectors.toList());
//  }
//    public <E,V> void printTest(Map<E, V> printMap) {
//        printMap.values().forEach(a -> a.forEach(b -> System.out.println(b.getName())));
//    }
    
//    public static void main(Object[] args) {
//
//    }

}
