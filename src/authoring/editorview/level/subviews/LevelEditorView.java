package authoring.editorview.level.subviews;

import java.io.File;
import java.io.IOException;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.editorview.level.LevelSetView;
import authoring.editorview.level.subviews.editorfields.AddLevelEffectView;
import authoring.editorview.level.subviews.editorfields.CreateNewWaveView;
import authoring.editorview.level.subviews.editorfields.DeleteLevel;
import authoring.editorview.level.subviews.editorfields.LevelNameView;
import authoring.editorview.level.subviews.editorfields.LevelRewardsView;
import authoring.editorview.level.subviews.editorfields.LevelTransitionTimeField;
import authoring.editorview.level.subviews.editorfields.PreviewLevelView;
import authoring.editorview.level.subviews.editorfields.WaveTableView;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * 
 * @author Kayla Schulz
 * @author Diane Hadley
 *
 */
public class LevelEditorView extends PhotoFileChooser implements LevelSetView {
    private static final double BUFFER = 10.0;

    private LevelAuthoringViewDelegate delegate;
    private LevelRewardsView levelRewardsView;
    private LevelNameView levelNameView;
    LevelTransitionTimeField transitionTimeField;
    WaveTableView waveTableView;
    PreviewLevelView previewLevelView;
    AddLevelEffectView addLevelEffect;
    private CreateNewWaveView createWaveView;
    private DeleteLevel deleteLevelView;

    private VBox vbox;
    private AnchorPane rootBuffer;
    private File chosenFile;

    public LevelEditorView (LevelRewardsView levelRewardsView,
                            LevelNameView levelNameView,
                            LevelTransitionTimeField transitionTimeField,
                            WaveTableView waveTableView,
                            PreviewLevelView previewLevelView,
                            AddLevelEffectView addLevelEffect,
                            DeleteLevel deleteLevelView,
                            CreateNewWaveView createWaveView) {
        this.levelRewardsView = levelRewardsView;
        this.levelNameView = levelNameView;
        this.transitionTimeField = transitionTimeField;
        this.waveTableView = waveTableView;
        this.previewLevelView = previewLevelView;
        this.addLevelEffect = addLevelEffect;
        this.createWaveView = createWaveView;
        this.deleteLevelView = deleteLevelView;

        vbox = new VBox(10);
        rootBuffer = new AnchorPane();
        rootBuffer.getChildren().add(vbox);
        buildViewComponents();
    }

    private void buildViewComponents () {
        AnchorPane.setLeftAnchor(vbox, BUFFER);
        AnchorPane.setTopAnchor(vbox, BUFFER);

        rootBuffer
                .setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                                                       CornerRadii.EMPTY, new BorderWidths(0.5))));
        rootBuffer
                .setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235),
                                                                 CornerRadii.EMPTY, Insets.EMPTY)));

        vbox.getChildren().addAll(levelRewardsView.getInstanceAsNode(),
                                  levelNameView.getInstanceAsNode(),
                                  transitionTimeField.getInstanceAsNode(),
                                  deleteLevelView.getInstanceAsNode(),
                                  createWaveView.getInstanceAsNode(),
                                  waveTableView.getInstanceAsNode(),
                                  previewLevelView.getInstanceAsNode());
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            // System.out.println(chosenFile.toURI().getPath());
            delegate.onUserEnteredLevelImagePath(chosenFile.toURI().getPath());
        }
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

}
