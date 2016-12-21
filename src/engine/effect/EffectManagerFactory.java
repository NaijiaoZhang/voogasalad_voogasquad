package engine.effect;

import gameplayer.model.effect.AbstractEffectFactory;

public class EffectManagerFactory {
    public static final String DEFAULT_PACKAGE_PATH = "gameplayer.model";
    AnnotatedDataMapFactory annotatedDataMapFactory;
    AnnotatedMethodMapFactory methodMapFactory;
    
    public EffectManagerFactory() {
        annotatedDataMapFactory = new AnnotatedDataMapFactory();
        methodMapFactory = new AnnotatedMethodMapFactory();
    }
    
    public EffectManager create(Class<? extends AbstractEffectFactory> effectFactory) {
        return create(effectFactory, DEFAULT_PACKAGE_PATH);
    }
    
    public EffectManager create(Class<? extends AbstractEffectFactory> effectFactory, String packagePath) {
        return new EffectTypeManager(methodMapFactory.create(packagePath), annotatedDataMapFactory.create(packagePath).get(effectFactory));
    }
    
}
