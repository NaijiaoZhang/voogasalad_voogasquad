package authoring.editorview.collisioneffects;

/**
 * 
 * @author Kayla Schulz
 *
 */
public interface EffectAuthoringViewDelegate {

    public void onUserSelectedAvailableClass (String selectedClass);

    public void onUserEnteredEffectName (String name);

    public void onUserEnteredCondition (String condition);

    public void onUserSelectedEffect (int effectID);

    public void onUserEnteredEffectText (String effect);
}
