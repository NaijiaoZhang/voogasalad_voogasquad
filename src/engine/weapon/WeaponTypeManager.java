package engine.weapon;

import engine.AbstractTypeManager;
import engine.effect.EffectBuilder;
import engine.effect.EffectManager;
import engine.effect.EffectManagerFactory;
import engine.effect.EffectTypeBuilder;
import engine.effect.EffectTypeManager;
import engine.enemy.EnemyManager;
import gameplayer.model.effect.AbstractEffectFactory;
import gameplayer.model.effect.CollisionEffectFactory;

public class WeaponTypeManager extends AbstractTypeManager<Weapon> implements WeaponManager {
    
    public static final Class<? extends AbstractEffectFactory> DEFAULT_EFFECT_FACTORY = CollisionEffectFactory.class;

    private EffectManager weaponEffectManager;
    
    WeaponTypeManager() {
        weaponEffectManager = new EffectManagerFactory().create(DEFAULT_EFFECT_FACTORY);
        EffectBuilder effectFactory = new EffectTypeBuilder();
        weaponEffectManager.addEntry(effectFactory.build());
        weaponEffectManager.addEntry(effectFactory.build());
    }
    
    @Override
    public void visitRemoveEntry(EnemyManager manager, Integer index) {
        applyToAllEntities(a -> a.removeTarget(index));
    }

    @Override
    public EffectManager getWeaponEffectManager () {
        return weaponEffectManager;
    }
 
}