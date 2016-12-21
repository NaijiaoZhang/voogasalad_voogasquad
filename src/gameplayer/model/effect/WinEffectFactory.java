package gameplayer.model.effect;

import engine.effect.EffectData;
import engine.effect.Enemy;
import gameplayer.model.GamePlayData;


public class WinEffectFactory extends AbstractEffectFactory {
    public static final String TRIGGER_NAME = "winTrigger";
    public static final String ENCOMPASSING_CLASS_NAME = "gamePlayData";

    @EffectData
    protected Object winTrigger;
    @EffectData
    protected Object gamePlayData;

    @EffectData
    protected GamePlayData data;

    public WinEffectFactory (GamePlayData data) {
        super(TRIGGER_NAME, ENCOMPASSING_CLASS_NAME);
        this.data = data;
        loadInSpecificValues();

    }
}
