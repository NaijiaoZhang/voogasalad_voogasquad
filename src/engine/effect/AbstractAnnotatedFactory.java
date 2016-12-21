package engine.effect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanner;

public abstract class AbstractAnnotatedFactory<E extends Member> {
    
    protected Map<Class<?>, List<E>> create(Class<?> methodAnnotation, String packageName, Scanner scanner) {
        Reflections reflections = new Reflections(packageName, scanner);
        return applySearch(reflections).stream().collect(Collectors.groupingBy( a -> a.getDeclaringClass()));
    }
    
    public abstract Map<Class<?>, List<E>> create (String packageName);
    
    protected abstract Collection<E> applySearch(Reflections reflections);
}


//private Function<Class<U>, Collection<E>> grabber;
//private Function<Reflections, Collection<E>> grabber2;
//
//AbstractAnnotatedFactory(Function<Class<U>, Collection<E>> grabber) {
//  this.grabber = grabber;
//}
//Consumer<? extends Reflections> test =  Reflections::getMethodsAnnotatedWith;
//Function<? extends Annotation, Collection<U>> testFunc = Reflections::getMethodsAnnotatedWith;
//List<Reflections> testlist;
//testlist.forEach(Reflections::getMethodsAnnotatedWith);
//BiFunction<Consumer<Reflections>, ? extends Annotation, Collection<U>> tesinaf =  (a, b) -> a.accept(reflections);;
//blahtest.stream().forEach(Reflections::getMethodsAnnotatedWith);
//reflections.getMethodsAnnotatedWith(annotation).stream().collect(Collectors.toMap( a -> a.toString(), a -> a));
//BiFunction<Consumer<Reflections>, Function<Class<U>, Collection<E>>, Collection<E>> blaah = (a, b) -> a.andThen(after);
//Function<Reflections, Function<Class<U>, Collection<E>>> test = a -> a.
//test.apply(reflections)