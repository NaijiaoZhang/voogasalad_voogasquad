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
public class TowerBuyPriceField implements TowerSetView {

    private GridPane root;
    private TextField towerBuyField;
    private TowerAuthoringViewDelegate delegate;

    public TowerBuyPriceField (ResourceBundle labelsResource) {
        towerBuyField =
                TextFieldFactory.makeTextField(labelsResource.getString("EnterInt"),
                                               e -> delegate
                                                       .onUserEnteredTowerBuyPrice(towerBuyField
                                                               .getText()));
        towerBuyField.setPrefWidth(230);
        root = GridFactory.createRowWithLabelandNode(
                                                     labelsResource.getString("BuyPrice"),
                                                     towerBuyField,
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

    public void updateTowerBuyPrice (String towerBuyPrice) {
        towerBuyField.setText(towerBuyPrice);
    }

}
