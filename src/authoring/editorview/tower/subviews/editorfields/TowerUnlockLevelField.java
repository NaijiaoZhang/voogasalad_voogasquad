package authoring.editorview.tower.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.tower.TowerSetView;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TowerUnlockLevelField implements TowerSetView {

    private GridPane root;
    private TextField towerLevelField;
    private TowerAuthoringViewDelegate delegate;

    public TowerUnlockLevelField (ResourceBundle labelsResource) {
        towerLevelField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"),
                                               e -> delegate
                                                       .onUserEnteredTowerUnlockLevel(towerLevelField
                                                               .getText()));
        towerLevelField.setPrefWidth(230);
        root = GridFactory.createRowWithLabelandNode(
                                                     labelsResource.getString("UnlockLevel"),
                                                     towerLevelField,
                                                     150);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public void updateTowerUnlockLevel (String towerLevel) {
        towerLevelField.setText(towerLevel);
    }

}
