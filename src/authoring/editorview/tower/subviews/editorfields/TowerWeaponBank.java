package authoring.editorview.tower.subviews.editorfields;

import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.CheckComboBox;
import authoring.editorview.tower.TowerSetView;
import authoring.utilityfactories.ComboBoxFactory;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class TowerWeaponBank implements TowerSetView {

    private TowerAuthoringViewDelegate delegate;
    private ComboBox<Object> weaponComboBox;
    private ObservableList<Object> availableWeaponsList;

    public TowerWeaponBank (ResourceBundle labelsResource) {
        availableWeaponsList = FXCollections.observableArrayList();
        weaponComboBox = new ComboBox<Object>();
        weaponComboBox =
                ComboBoxFactory.makeComboBox("Choose Weapon", e -> delegate
                        .onUserEnteredTowerChosenWeapon(weaponComboBox.getValue().toString()),
                                             availableWeaponsList);
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return weaponComboBox;
    }

    public void updateTowerChosenWeapon (String chosenWeapon) {
    }

    public void setOptions (List<String> updateWeapons) {
        availableWeaponsList.clear();
        availableWeaponsList.addAll(updateWeapons);
    }

}
