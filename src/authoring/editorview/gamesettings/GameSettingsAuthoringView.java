package authoring.editorview.gamesettings;

import java.util.List;
import authoring.editorview.ListDataSource;
import authoring.editorview.gamesettings.subviews.GameSettingsEditorView;
import authoring.editorview.gamesettings.subviews.GameSettingsPreviewView;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


/**
 * 
 * @author Kayla Schulz
 * @author Diane Hadley
 *
 */
public class GameSettingsAuthoringView implements GameSettingsUpdateView, GameSettingsSetView {

    private GridPane gameSettingsView;

    private GameSettingsEditorView gameSettingsEditor;
    private GameSettingsPreviewView gameSettingsPreview;

    private static final int EDITOR_SIZE = 250;
    private static final int PREVIEW_SIZE = 700;

    public GameSettingsAuthoringView (int width, int height) {
        gameSettingsView = new GridPane();
        this.gameSettingsEditor = new GameSettingsEditorView(EDITOR_SIZE);
        this.gameSettingsPreview = new GameSettingsPreviewView(PREVIEW_SIZE);
        buildView();
    }

    private void buildView () {

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setMinWidth(EDITOR_SIZE);

        ColumnConstraints previewColumn = new ColumnConstraints();
        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(PREVIEW_SIZE);

        gameSettingsView.getColumnConstraints().addAll(editorColumn, previewColumn);
        gameSettingsView.getRowConstraints().add(fullRow);

        gameSettingsView.add(gameSettingsEditor.getInstanceAsNode(), 0, 0);
        gameSettingsView.add(gameSettingsPreview.getInstanceAsNode(), 1, 0);
    }

    @Override
    public Node getInstanceAsNode () {
        return gameSettingsView;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        gameSettingsEditor.setDelegate(delegate);
        gameSettingsPreview.setDelegate(delegate);

    }

    @Override
    public void updateNameDisplay (String name) {
        gameSettingsEditor.updateName(name);
    }

    @Override
    public void updateNumberofLives (int lives) {
        gameSettingsEditor.updateInitialLives(lives);
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        gameSettingsEditor.updateGameImagePath(imagePath); // TODO why?
        gameSettingsPreview.updateGameImagePath(imagePath);

    }

    @Override
    public void updateInitialMoney (int money) {
        gameSettingsEditor.updateInitialMoney(money);
    }

    @Override
    public void updateWinningConditions (List<String> winningConditions) {

    }

    @Override
    public void updateLosingConditions (List<String> losingConditions) {
        // TODO Auto-generated method stub
    }

    @Override
    public void setGameSettingsListDataSource (ListDataSource source) {
        // TODO ?
    }

    @Override
    public void updateSizeDisplay (double size) {
    }

    @Override
    public void updateDeleteEntity (String entityID) {
    }

    @Override
    public void updateBank (List<Integer> ids) {
    }

    @Override
    public void updateGridSize (int size) {
        gameSettingsEditor.updateGridDimensions(size);
        gameSettingsPreview.updateGridDimensions(size);

    }

    @Override
    public void updatePathList (List<Integer> pathList) {
        gameSettingsEditor.updatePathList(pathList);
        gameSettingsPreview.updatePathList(pathList);
    }

    @Override
    public void updatePathType (String pathType) {
        gameSettingsEditor.updatePathType(pathType);
    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        return null;
    }

    @Override
    public void updateAvailablePaths (List<Integer> availablePathList) {
        gameSettingsEditor.updateAvailablePathList(availablePathList);

    }

}
