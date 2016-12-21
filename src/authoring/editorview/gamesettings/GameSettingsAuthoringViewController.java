package authoring.editorview.gamesettings;

import java.util.List;
import authoring.editorview.EditorViewController;
import authoring.editorview.collisioneffects.EffectAuthoringViewController;
import engine.effect.EffectManagerController;
import engine.level.LevelManagerController;
import engine.path.Coordinate;
import engine.path.PathManagerController;
import engine.settings.GameModeManagerController;


public class GameSettingsAuthoringViewController extends EditorViewController
        implements GameSettingsAuthoringViewDelegate {

    private GameSettingsUpdateView gameView;
    private GameModeManagerController gameSettingsDataSource;
    private PathManagerController pathDataSource;
    private LevelManagerController levelDataSource;
    private EffectManagerController effectDataSource;
    private int activeID = 0;

    public GameSettingsAuthoringViewController (int editorWidth, int editorHeight) {
        gameView = GameSettingsAuthoringViewFactory.build(editorWidth, editorHeight);
        gameView.setDelegate(this);
        this.view = gameView;
    }

    public void setGameSettingsDataSource (GameModeManagerController source,
                                           PathManagerController pathSource,
                                           LevelManagerController levelSource) {
        this.gameSettingsDataSource = source;
        this.pathDataSource = pathSource;
        this.levelDataSource = levelSource;
        effectDataSource = gameSettingsDataSource.getEffectManagerController();
        this.gameSettingsDataSource.addTypeBankListener(this.gameView);
        this.pathDataSource.addAvailablePathListener(a -> gameView.updateAvailablePaths(a));

        createNewGame();
    }

    private void createNewGame () {
        gameSettingsDataSource.createType(this.gameView);
        refreshView();
    }

    @Override
    public void onUserEnteredGameLives (int lives) {
        this.gameSettingsDataSource.setNumberofLives(activeID, lives);
    }

    @Override
    public void onUserEnteredGameName (String name) {
        this.gameSettingsDataSource.setName(activeID, name);

    }

    @Override
    public void onUserEnteredGameImage (String imagePath) {
        this.gameSettingsDataSource.setImagePath(activeID, imagePath);

    }

    @Override
    public void onUserEnteredGameMoney (int money) {
        this.gameSettingsDataSource.setMoney(activeID, money);

    }

    @Override
    public void onUserEnteredWinningConditions (String winConditions) {
        this.gameSettingsDataSource.addWinningCondition(activeID, winConditions);

    }

    @Override
    public void onUserEnteredLosingConditions (String loseConditions) {
        this.gameSettingsDataSource.addLosingCondition(activeID, loseConditions);

    }

    @Override
    public String getPathName (int pathID) {
        return this.pathDataSource.getName(pathID);

    }

    @Override
    public void onUserEnteredGameGridSize (int size) {
        this.gameSettingsDataSource.setGridSize(activeID, size);

    }

    @Override
    public void onUserEnteredGamePathType (String type) {
        this.gameSettingsDataSource.setPathType(activeID, type);

    }

    @Override
    public void onUserEnteredPath (int pathID) {
        this.gameSettingsDataSource.addPath(activeID, pathID);

    }

    @Override
    public void refreshView () {
        gameView.updateNameDisplay(gameSettingsDataSource.getName(activeID));
        gameView.updateImagePathDisplay(gameSettingsDataSource.getImagePath(activeID));
        gameView.updateNumberofLives(gameSettingsDataSource.getNumberofLives(activeID));
        gameView.updateInitialMoney(gameSettingsDataSource.getMoney(activeID));
        gameView.updateGridSize(gameSettingsDataSource.getGridSize(activeID));
        gameView.updateLosingConditions(gameSettingsDataSource.getLosingConditons(activeID));
        gameView.updateWinningConditions(gameSettingsDataSource.getWinningConditions(activeID));
        gameView.updatePathType(gameSettingsDataSource.getPathType(activeID));
    }

    @Override
    public String getPathImage (int pathID) {
        return this.pathDataSource.getImagePath(pathID);
    }

    @Override
    public List<Integer> getAvailablePathList () {
        return this.pathDataSource.getAvailablePaths();
    }

    @Override
    public int getPathDimensions (int pathID) {
        return this.pathDataSource.getNumberofRows(pathID);
    }

    @Override
    public List<Coordinate<Integer>> getPathCoordinates (int pathID) {
        return this.pathDataSource.getPathCoordinates(pathID);
    }

    @Override
    public void onUserEnteredRemovePath (int pathID) {
        this.gameSettingsDataSource.removePath(activeID, pathID);

    }

    public List<Integer> getLevelIDs () {
        return this.levelDataSource.getCreatedTypeIds();
    }

    public String getLevelName (int levelID) {
        return this.levelDataSource.getName(levelID);
    }

    @Override
    public void onUserPressedAddEffect () {
        EffectAuthoringViewController effectAuthoringView =
                new EffectAuthoringViewController(effectDataSource);
        effectDataSource.createType(effectAuthoringView.getEffectAuthoringView());
        effectAuthoringView.setEffectOptions(effectDataSource.getCreatedTypeIds());
        effectAuthoringView.setAvailClasses(effectDataSource.getAvailableClasses());
        effectAuthoringView.setAvailDataObjects(effectDataSource.getAvailableDataObjects());
        effectAuthoringView.openEffectView();
    }

}
