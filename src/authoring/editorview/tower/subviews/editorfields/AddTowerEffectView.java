package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.editorview.tower.TowerSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class AddTowerEffectView implements TowerSetView {

    private TowerAuthoringViewDelegate delegate;
    private Button addTowerEffectButton;

    public AddTowerEffectView (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return addTowerEffectButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        addTowerEffectButton =
                ButtonFactory.makeButton(labelsResource.getString("AddEffect"),
                                         e -> delegate.onUserPressedAddEffect());
    }

}
