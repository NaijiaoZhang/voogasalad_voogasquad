package engine.effect;

import java.util.List;
import authoring.editorview.IUpdateView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;


public interface EffectView extends IUpdateView {

    ComboBox<String> getTriggers ();

    ComboBox<String> getTriggerConditions ();

    void updateTriggers (List<String> methods);

    void updateTriggerConditions (List<String> methods);

    Group getNode ();

    Scene getScene ();
    

}
