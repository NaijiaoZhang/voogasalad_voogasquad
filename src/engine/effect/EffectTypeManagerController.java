package engine.effect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import authoring.editorview.collisioneffects.EffectUpdateView;
import authoring.editorview.enemy.EnemyUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;
import engine.tower.TowerTypeBuilder;
import engine.tower.TowerTypeManager;
import gameplayer.model.effect.AbstractEffectFactory;
import gameplayer.model.effect.CollisionEffectFactory;
import gameplayer.model.effect.GameEffect;
import gameplayer.model.effect.GroovyExecutor;


public class EffectTypeManagerController extends
        AbstractTypeManagerController<EffectManager, EffectBuilder, Effect, EffectUpdateView>
        implements EffectManagerController {
    public static final Class<? extends AbstractEffectFactory> DEFAULT_EFFECT_FACTORY = AbstractEffectFactory.class;
    
    public EffectTypeManagerController (ManagerMediator managerMediator, EffectManager effectTypeManager) {
        super(effectTypeManager, new EffectTypeBuilder(), managerMediator);
//        
//        EffectBuilder efb = new EffectTypeBuilder();
//        Effect effectType = efb.buildTriggerConditionGroovy("collider.getHealth() == 50 && myself.getName() == 'Sean'").buildEffectGroovy("collider.setHealth(100)").build();
//        CollisionEffectFactory testFactory = new CollisionEffectFactory();
//        GameEffect gameEffect = testFactory.create(effectType);
//        
//        Enemy collider = new Enemy();
//        Enemy myself = new Enemy();
//        gameEffect.addEncompassingClass(myself); //Enemy constructor gameEffect.addEncompassingClass(this)
//        
//        gameEffect.addTrigger(collider);
//        
//        gameEffect.execute();
        
    }
    
    EffectTypeManagerController (ManagerMediator managerMediator) {
        this(managerMediator, new EffectManagerFactory().create(DEFAULT_EFFECT_FACTORY));
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectManagerController#getTriggers()
     */
    @Override
    public void addActiveClassListener(EffectUpdateView updateView) throws ClassNotFoundException{
        getTypeManager().addActiveClassListener((oldValue, newValue) -> {
                    updateView.updateTriggers(getAvailableClassMethods(newValue.getName()));
        });
    }
    
    @Override
    public List<String> getAvailableClasses () {
        return getTypeManager().getAnnotatedClasses().stream().map(Class::getName).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectManagerController#getTriggerMethods(java.lang.String)
     */
    @Override
    public List<String> getAvailableClassMethods (String trigger) {
        try {
            return getTypeManager().getAnnotatedClassMethods(Class.forName(trigger)).stream().map(a -> a.toGenericString())
                    .collect(Collectors.toList());
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectManagerController#setTrigger(int, java.lang.String)
     */
  

        // EffectType newEffect = new EffectType();
        // ObservableProperty<String> observableTrigger = new
        // ObservableObjectProperty<String>(trigger);
        // observableTrigger.addListener((oldValue, newValue) ->
        // effectView.updateTriggerConditions(effectManager.getAnnotatedClassMethods(newValue).stream().map(Method::getName).collect(Collectors.toList())
        // ));
        // newEffect.setTriggerClass(new ObservableObjectProperty<String>(trigger));
        // observableTrigger.setProperty(trigger);
        // enemy.addEffect(newEffect);
        // System.out.println(enemy.getEffectsSize());
        //
        //
        // return enemy.getEffectsSize() - 1;
        // effectManager.getAnnotatedClassMethods(trigger)
    

    @Override
    protected EffectBuilder constructTypeProperties (EffectUpdateView updateView,
                                                     EffectBuilder typeBuilder) {
        return typeBuilder;
//                .addTriggerClassListener( (oldValue, newValue) -> updateView
//                        .updateTriggerConditions(getTypeManager()
//                                .getAnnotatedClassMethods(newValue.getName()).stream()
//                                .map(a -> a.toString()).collect(Collectors.toList())));
    }

    @Override
    public void setAvailableClass (String selectedClass) {
        try {
            getTypeManager().setActiveClass(Class.forName(selectedClass));
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTrigger (int effectID) {
        return getTypeManager().getEntity(effectID).getTriggerClass();
    }

    @Override
    public String getCondition (int effectID) {
        return getTypeManager().getEntity(effectID).getTriggerConditionGroovy();
    }

    @Override
    public String getEffect (int effectID) {
        return getTypeManager().getEntity(effectID).getEffectGroovy();
    }

    @Override
    public void setTrigger (int effectID, String trigger) {
         getTypeManager().getEntity(effectID).setTriggerClass(trigger);
    }

    @Override
    public void setCondition (int effectID, String trigger) {
        getTypeManager().getEntity(effectID).setTriggerConditionGroovy(trigger);
    }

    @Override
    public void setEffect (int effectID, String trigger) {
        getTypeManager().getEntity(effectID).setEffectGroovy(trigger);
    }

    @Override
    public List<String> getAvailableDataObjects () {
        return getTypeManager().getAnnotatedDataObjects().stream().map( a -> a.getName()).collect(Collectors.toList());
    }

}
