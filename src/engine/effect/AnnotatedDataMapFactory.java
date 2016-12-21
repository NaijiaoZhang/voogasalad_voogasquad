package engine.effect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;

public class AnnotatedDataMapFactory extends AbstractAnnotatedFactory<Field>{
    public static final Class<? extends Annotation> ANNOTATION_TYPE = EffectData.class;
    

    @Override
    protected Collection<Field> applySearch (Reflections reflections) {
        return reflections.getFieldsAnnotatedWith(ANNOTATION_TYPE);
    }


    @Override
    public Map<Class<?>, List<Field>> create (String packageName) {
        return create(ANNOTATION_TYPE, packageName, new FieldAnnotationsScanner());
    }
    
    
    

    
}
