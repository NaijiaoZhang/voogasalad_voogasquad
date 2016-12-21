package authoring.editorview.gamesettings.subviews;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.editorview.gamesettings.subviews.editorfields.AddSettingsEffectView;
import authoring.editorview.gamesettings.subviews.editorfields.GameImageView;
import authoring.editorview.gamesettings.subviews.editorfields.GameInitialLivesView;
import authoring.editorview.gamesettings.subviews.editorfields.GameInitialMoneyView;
import authoring.editorview.gamesettings.subviews.editorfields.GameNameView;
import authoring.editorview.gamesettings.subviews.editorfields.GamePathDimensionsView;
import authoring.editorview.gamesettings.subviews.editorfields.GamePathTypeView;
import authoring.editorview.gamesettings.subviews.editorfields.GamePathView;
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


public class GameSettingsEditorView implements GameSettingsSetView {

    private VBox root;
    private AnchorPane rootBuffer;
    private GameNameView gameNameView;
    private GamePathTypeView gamePathTypeView;
    private GameImageView gameImageView;
    private GameInitialLivesView gameInitialLivesView;
    private GameInitialMoneyView gameInitialMoneyView;
    private GamePathDimensionsView gamePathDimensionsView;
    private GamePathView gamePathView;
    private AddSettingsEffectView settingsEffectView;

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringSettings";
    private ResourceBundle settingsResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    private static final double BUFFER = 10.0;

    public GameSettingsEditorView (int size) {

        this.root = new VBox(10);

        this.rootBuffer = new AnchorPane();
        this.gameNameView = new GameNameView(settingsResource);
        this.gameImageView = new GameImageView(settingsResource);
        this.gamePathTypeView = new GamePathTypeView();
        this.gameInitialLivesView = new GameInitialLivesView(settingsResource);
        this.gameInitialMoneyView = new GameInitialMoneyView(settingsResource);
        this.gamePathDimensionsView = new GamePathDimensionsView();
        this.gamePathView = new GamePathView(settingsResource);
        this.settingsEffectView = new AddSettingsEffectView(settingsResource);
        buildView();
    }

    private void buildView () {
        rootBuffer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root, BUFFER);
        AnchorPane.setTopAnchor(root, BUFFER);

        rootBuffer
                .setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                                                       CornerRadii.EMPTY, new BorderWidths(0.5))));
        rootBuffer
                .setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235),
                                                                 CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(
                                  gameNameView.getInstanceAsNode(),
                                  gameImageView.getInstanceAsNode(),
                                  gameInitialLivesView.getInstanceAsNode(),
                                  gameInitialMoneyView.getInstanceAsNode(),
                                  settingsEffectView.getInstanceAsNode(),
                                  gamePathTypeView.getInstanceAsNode(),
                                  gamePathDimensionsView.getInstanceAsNode(),
                                  gamePathView.getInstanceAsNode());
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        gameNameView.setDelegate(delegate);
        gameImageView.setDelegate(delegate);
        gameInitialMoneyView.setDelegate(delegate);
        gameInitialLivesView.setDelegate(delegate);
        gamePathTypeView.setDelegate(delegate);
        gamePathView.setDelegate(delegate);
        gamePathDimensionsView.setDelegate(delegate);
        settingsEffectView.setDelegate(delegate);
    }

    public void updateName (String name) {
        gameNameView.updateName(name);
    }

    public void updateInitialLives (int lives) {
        gameInitialLivesView.updateInitialLives(lives);
    }

    public void updateGameImagePath (String imagePath) {
        gameImageView.updateGameImagePath(imagePath);
    }

    public void updateInitialMoney (int money) {
        gameInitialMoneyView.updateInitialMoney(money);
    }

    public void updatePathType (String pathType) {
        gamePathTypeView.updatePathType(pathType);

    }

    public void updateGridDimensions (int size) {
    	gamePathView.clearPathList();
        gamePathDimensionsView.setGridDimensions(size);
        
    }

    public void updateAvailablePathList (List<Integer> availablePathList) {
        gamePathView.setAvailablePathList(availablePathList);
    }

    public void updatePathList (List<Integer> pathList) {
        gamePathView.setPathList(pathList);

    }

}
