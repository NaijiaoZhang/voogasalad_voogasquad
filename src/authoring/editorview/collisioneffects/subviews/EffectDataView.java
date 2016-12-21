package authoring.editorview.collisioneffects.subviews;

import authoring.editorview.collisioneffects.EffectAuthoringViewDelegate;
import authoring.editorview.collisioneffects.EffectSetView;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectConditionField;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectField;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectNameField;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class EffectDataView implements EffectSetView {

    private EffectAuthoringViewDelegate delegate;
    private BorderPane effectDataPane;
    private EffectNameField nameField;
    private EffectConditionField conditionField;
    private EffectField effectField;

    public EffectDataView (EffectNameField nameField,
                           EffectConditionField conditionField,
                           EffectField effectField) {
        effectDataPane = new BorderPane();

        this.nameField = nameField;
        this.conditionField = conditionField;
        this.effectField = effectField;
        setPane();
    }

    @Override
    public Node getInstanceAsNode () {
        return effectDataPane;
    }

    @Override
    public void setDelegate (EffectAuthoringViewDelegate delegate) {
        this.delegate = delegate;
        nameField.setDelegate(delegate);
        conditionField.setDelegate(delegate);
        effectField.setDelegate(delegate);
    }

    private void setPane () {
        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(nameField.getInstanceAsNode(),
                                  conditionField.getInstanceAsNode(),
                                  effectField.getInstanceAsNode());
        effectDataPane.setCenter(vbox);
    }

}
