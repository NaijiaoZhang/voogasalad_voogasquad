package authoring.editorview.collisioneffects.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.TextFieldView;
import authoring.editorview.collisioneffects.EffectAuthoringViewDelegate;
import authoring.editorview.collisioneffects.EffectSetView;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.control.TextField;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class EffectConditionField extends TextFieldView implements EffectSetView {

    private TextField conditionField;
    private EffectAuthoringViewDelegate delegate;

    public EffectConditionField (ResourceBundle resource) {
        super(resource);
        conditionField.setPrefWidth(500);
    }

    @Override
    public void setDelegate (EffectAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void updateField (String newData) {
        conditionField.setText(newData);
    }

    @Override
    protected void makeTextField (ResourceBundle labelsResource) {
        conditionField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterString"),
                                               e -> delegate
                                                       .onUserEnteredCondition(conditionField
                                                               .getText()));
        root =
                GridFactory.createRowWithLabelandNode(labelsResource.getString("Condition"),
                                                      conditionField, 125);
    }

}
