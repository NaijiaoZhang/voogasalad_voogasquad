package engine.ability;

import authoring.editorview.IUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.ability.AbilityManager;

public class AbilityTypeManagerController extends AbstractTypeManagerController<AbilityManager, AbilityBuilder, Ability, IUpdateView> implements AbilityManagerController {
    
    public AbilityTypeManagerController(ManagerMediator managerMediator) {
        super(new AbilityTypeManager(), new AbilityTypeBuilder(), managerMediator);
    }

    @Override
    protected AbilityBuilder constructTypeProperties (IUpdateView viewFiller,
                                                      AbilityBuilder typeBuilder) {
        return typeBuilder;
//                .addEffectListener((oldValue, newValue) -> viewFiller
//                                   .updateEffect(newValue))
//                .addRateListener((oldValue, newValue) -> viewFiller
//                                   .updateRate(newValue));
    }

    @Override
    public void setRate (int abilityID, double abilityRate) {
        getTypeManager().getEntity(abilityID).setRate(abilityRate);
    }

    @Override
    public void setEffect (int abilityID, String abilityEffect) {
        getTypeManager().getEntity(abilityID).setEffect(abilityEffect);        
    }

    @Override
    public double getRate (int abilityID) {
        return getTypeManager().getEntity(abilityID).getRate();        
    }

    @Override
    public String getEffect (int abilityID) {
        return getTypeManager().getEntity(abilityID).getEffect();        
    }
}