package authoring.editorview.level;

import java.util.List;
import java.util.ResourceBundle;
import engine.level.wave.WaveString;
import authoring.editorview.ListDataSource;
import authoring.editorview.level.subviews.LevelBank;
import authoring.editorview.level.subviews.LevelEditorView;
import authoring.editorview.level.subviews.editorfields.AddLevelEffectView;
import authoring.editorview.level.subviews.editorfields.CreateNewWaveView;
import authoring.editorview.level.subviews.editorfields.DeleteLevel;
import authoring.editorview.level.subviews.editorfields.LevelNameView;
import authoring.editorview.level.subviews.editorfields.LevelRewardsView;
import authoring.editorview.level.subviews.editorfields.LevelTransitionTimeField;
import authoring.editorview.level.subviews.editorfields.PreviewLevelView;
import authoring.editorview.level.subviews.editorfields.WaveTableView;
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
public class LevelAuthoringView implements LevelUpdateView {

    private LevelAuthoringViewDelegate delegate;

    private GridPane levelView;
    private LevelRewardsView levelRewardsView;
    private LevelNameView levelNameView;
    private LevelBank levelBank;
    private PreviewLevelView previewLevelView;
    private LevelTransitionTimeField transitionTimeField;
    private WaveTableView waveTableView;
    private AddLevelEffectView addLevelEffect;
    private LevelEditorView levelEditorView;
    private CreateNewWaveView createWaveView;
    private DeleteLevel deleteLevel;

    private ResourceBundle levelsResource =
            ResourceBundle.getBundle("resources/GameAuthoringLevels");

    LevelAuthoringView (int width, int height) {
        this.levelView = new GridPane();

        this.levelRewardsView = new LevelRewardsView(levelsResource);
        this.levelNameView = new LevelNameView(levelsResource);
        this.transitionTimeField = new LevelTransitionTimeField(levelsResource);
        this.waveTableView = new WaveTableView(levelsResource, width);
        this.previewLevelView = new PreviewLevelView();
        this.addLevelEffect = new AddLevelEffectView(levelsResource);
        this.createWaveView = new CreateNewWaveView(levelsResource);
        this.deleteLevel = new DeleteLevel(levelsResource);

        this.levelBank = new LevelBank();
        this.levelEditorView =
                new LevelEditorView(levelRewardsView, levelNameView, transitionTimeField,
                                    waveTableView, previewLevelView, addLevelEffect,
                                    deleteLevel, createWaveView);
        setLevelView();
    }

    @Override
    public Node getInstanceAsNode () {
        return levelView;
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
        levelNameView.setDelegate(delegate);
        levelRewardsView.setDelegate(delegate);
        transitionTimeField.setDelegate(delegate);
        waveTableView.setDelegate(delegate);
        addLevelEffect.setDelegate(delegate);
        levelBank.setDelegate(delegate);
        createWaveView.setDelegate(delegate);
        deleteLevel.setDelegate(delegate);
    }

    private void setLevelView () {
        ColumnConstraints bankColumn = new ColumnConstraints();
        bankColumn.setMinWidth(150);

        ColumnConstraints editorColumn = new ColumnConstraints();
        editorColumn.setPrefWidth(400);

        ColumnConstraints previewColumn = new ColumnConstraints();

        RowConstraints fullRow = new RowConstraints();

        fullRow.setMinHeight(700);

        levelView.getColumnConstraints().addAll(bankColumn, editorColumn, previewColumn);
        levelView.getRowConstraints().add(fullRow);

        levelView.add(levelBank.getInstanceAsNode(), 0, 0);
        levelView.add(levelEditorView.getInstanceAsNode(), 1, 0);
    }

    @Override
    public void updateNameDisplay (String name) {
        levelNameView.updateName(name);
        // also level chooser

    }

    @Override
    public void updateRewardScore (double winScore) {
        levelRewardsView.setRewardScore(Double.toString(winScore));
    }

    @Override
    public void updateRewardHealth (double winHealth) {
        levelRewardsView.setRewardHealth(Double.toString(winHealth));
    }

    @Override
    public void updateRewardMoney (double winMoney) {
        levelRewardsView.setRewardMoney(Double.toString(winMoney));
    }

    @Override
    public void updateTransitionTime (double time) {
        transitionTimeField.updateTransitionTimeField(Double.toString(time));
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        // Don't worry about this right now

    }

    @Override
    public void updateSizeDisplay (double size) {
        // Don't worry about this right now
    }

    @Override
    public void updateBank (List<Integer> ids) {
        this.levelBank.updateBank(ids);
    }

    @Override
    public void updateLevelOptions (List<Integer> levelOptions) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLevelListDataSource (ListDataSource source) {
        this.levelBank.setListDataSource(source);
    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateWaves (List<WaveString> waves) {
        waveTableView.updateWaveTableView(waves);
    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        int currentIndex = this.levelBank.getIndexForItemWithID(id);
        Integer nearestID = this.levelBank.getIDForItemAtIndex(currentIndex - 1);
        if (nearestID == null) {
            nearestID = this.levelBank.getIDForItemAtIndex(currentIndex + 1);
        }
        return nearestID;
    }

}
