package authoring.editorview.enemy.subviews;

import java.util.List;
import authoring.editorview.ImageBank;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz, Andrew Bihl
 *
 */
public class EnemyImageBank extends ImageBank {
    private EnemyAuthoringViewDelegate delegate;

    // TODO: Keep mapping in array of enemy IDs to list indices in order to be able to delete and
    // maintain order

    public EnemyImageBank () {
        super();
        Button newEnemyButton = ButtonFactory.makeButton("New Enemy", e -> {
            delegate.onUserPressedCreateEnemy();
        });
        newEnemyButton.setPrefWidth(130);
        this.items.add(newEnemyButton);
        this.CONTENT_OFFSET = 1;
    }

    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void updateEnemyBank (List<Integer> activeEnemies) {
        super.updateBank(activeEnemies);
    }

    @Override
    protected void userSelectedRow (int index) {
        int selectedEnemy = this.itemIDs.get(index);
        if (selectedEnemy != -1)
            this.delegate.onUserSelectedEnemy(selectedEnemy);
    }
}
