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
public class TowerSizeField implements TowerSetView {

    private GridPane root;
    private TextField towerSizeField;
    private TowerAuthoringViewDelegate delegate;

    public TowerSizeField (ResourceBundle labelsResource) {
        towerSizeField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"),
                                               e -> delegate.onUserEnteredTowerSize(towerSizeField
                                                       .getText()));
        towerSizeField.setPrefWidth(230);
        root = GridFactory.createRowWithLabelandNode(
                                                     labelsResource.getString("Size"),
                                                     towerSizeField,
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

    public void updateTowerSize (String towerSize) {
        towerSizeField.setText(towerSize);
    }

}
