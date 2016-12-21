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
public class TowerSellPriceField implements TowerSetView {

    private GridPane root;
    private TextField towerSellField;
    private TowerAuthoringViewDelegate delegate;

    public TowerSellPriceField (ResourceBundle labelsResource) {
        towerSellField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"),
                                               e -> delegate
                                                       .onUserEnteredTowerSellPrice(towerSellField
                                                               .getText()));
        towerSellField.setPrefWidth(230);
        root =
                GridFactory.createRowWithLabelandNode(labelsResource.getString("SellPrice"),
                                                      towerSellField, 150);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    public void updateTowerSellPrice (String towerSellPrice) {
        towerSellField.setText(towerSellPrice);
    }

}
