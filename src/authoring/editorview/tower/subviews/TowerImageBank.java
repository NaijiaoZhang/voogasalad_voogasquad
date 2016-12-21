package authoring.editorview.tower.subviews;

import java.util.List;
import authoring.editorview.ImageBank;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz, Andrew Bihl
 *
 */
public class TowerImageBank extends ImageBank {

    private TowerAuthoringViewDelegate delegate;

    public TowerImageBank () {
        super();
        Button createTowerButton =
                ButtonFactory.makeButton("New Tower",
                                         e -> {
                                             delegate.onUserPressedCreateNewTower();
                                         });
        createTowerButton.setPrefWidth(130);
        items.add(createTowerButton);
        this.CONTENT_OFFSET = 1;
    }

    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void updateTowerBank (List<Integer> createdTowers) {
        super.updateBank(createdTowers);
    }

    @Override
    protected void userSelectedRow (int index) {
        int selectedTowerID = this.itemIDs.get(index);
        if (selectedTowerID != -1)
            this.delegate.onUserSelectedTower(selectedTowerID);
    }

}
