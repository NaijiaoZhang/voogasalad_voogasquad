package gameplayer.model.effect;

import java.lang.reflect.Field;
import java.util.function.BiConsumer;
import engine.effect.EffectData;
import engine.effect.Enemy;
import gameplayer.model.GamePlayData;

public class CollisionEffectFactory extends AbstractEffectFactory {
    public static final String TRIGGER_NAME = "collider";
    public static final String ENCOMPASSING_CLASS_NAME = "myself";

    @EffectData
    protected Object collider;
    
    @EffectData
    protected Object myself;
    
   @EffectData
    protected GamePlayData data;
    
    public CollisionEffectFactory (GamePlayData data) {
        super(TRIGGER_NAME, ENCOMPASSING_CLASS_NAME);
        this.data = data;
        loadInSpecificValues();
        
    }

}
