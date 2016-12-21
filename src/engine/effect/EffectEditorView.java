package engine.effect;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;


public class EffectEditorView implements EffectView {
    Group node;
    Scene scene;
    ComboBox<String> triggers = new ComboBox<String>();
    ComboBox<String> triggerConditions = new ComboBox<String>();

    EffectEditorView (int size) {
        this.node = new Group();
        this.scene = new Scene(node, size, size);

        this.triggers =
                new ComboBox<String>(FXCollections.observableArrayList(new ArrayList<String>()));

        this.triggerConditions =
                new ComboBox<String>(FXCollections.observableArrayList(new ArrayList<String>()));

        node.getChildren().add(triggers);
        node.getChildren().add(triggerConditions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#getTriggers()
     */
    @Override
    public ComboBox<String> getTriggers () {
        return triggers;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#getTriggerConditions()
     */
    @Override
    public ComboBox<String> getTriggerConditions () {
        return triggerConditions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#updateTriggers(java.util.List)
     */
    @Override
    public void updateTriggers (List<String> methods) {
        triggers.getItems().setAll(methods);
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#updateTriggerConditions(java.util.List)
     */
    @Override
    public void updateTriggerConditions (List<String> methods) {
        triggerConditions.getItems().setAll(methods);
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#getNode()
     */
    @Override
    public Group getNode () {
        return node;
    }

    /*
     * (non-Javadoc)
     * 
     * @see engine.effect.EffectView#getScene()
     */
    @Override
    public Scene getScene () {
        return scene;
    }

    @Override
    public void updateNameDisplay (String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSizeDisplay (double size) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBank (List<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

	@Override
	public Integer getNearestAvailableItemID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
