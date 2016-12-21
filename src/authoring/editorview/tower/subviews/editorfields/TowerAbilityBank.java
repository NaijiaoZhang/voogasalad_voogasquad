package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.tower.TowerSetView;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TowerAbilityBank implements TowerSetView {

    private HBox towerAbilityBox;
    @SuppressWarnings("unused")
    private TowerAuthoringViewDelegate delegate;
    // private ResourceBundle labelsResource;

    public TowerAbilityBank (ResourceBundle labelsResource) {
        // this.labelsResource = labelsResource;
        towerAbilityBox = new HBox(5);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return towerAbilityBox;
    }

    public void updateTowerAbility (String towerAbility) {
        // towerAbilityBox.setValue(towerAbility);
    }

}
