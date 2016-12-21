package authoring.editorview.level.subviews;

import java.util.List;
import authoring.editorview.ImageBank;
import authoring.editorview.ListCellData;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 * 
 * @author Ezra Lieblich
 * @author Kayla Schulz
 *
 */
public class LevelBank extends ImageBank {
    private LevelAuthoringViewDelegate delegate;

    public LevelBank () {
        super();
        Button newEnemyButton = ButtonFactory.makeButton("New Level", e -> {
            delegate.onUserEnteredCreateLevel();
        });
        this.items.add(newEnemyButton);
        this.CONTENT_OFFSET = 1;
    }

    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void updateEnemyBank (List<Integer> activeEnemies) {
        super.updateBank(activeEnemies);
    }

    @Override
    protected void userSelectedRow (int index) {
        int selectedLevel = this.itemIDs.get(index);
        if (selectedLevel != -1)
            this.delegate.onUserSelectedLevel(selectedLevel);
    }

    @Override
    protected Node createCellFromData (ListCellData data) {
        Label cell = new Label();
        String name = data.getName();
        if (name.equals(null) || name.length() < 1) {
            name = DEFAULT_SUBJECT_IMAGE_PATH;
        }
        cell.setText(name);
        return cell;
    }
}
