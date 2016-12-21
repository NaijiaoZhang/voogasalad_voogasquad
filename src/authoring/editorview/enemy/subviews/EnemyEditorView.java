package authoring.editorview.enemy.subviews;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.enemy.EnemyAuthoringViewDelegate;
import authoring.editorview.enemy.EnemySetView;
import authoring.editorview.enemy.subviews.editorfields.EnemyHealthField;
import authoring.editorview.enemy.subviews.editorfields.EnemyImageView;
import authoring.editorview.enemy.subviews.editorfields.EnemyNameField;
import authoring.editorview.enemy.subviews.editorfields.EnemyRewardMoneyField;
import authoring.editorview.enemy.subviews.editorfields.EnemyRewardPointsField;
import authoring.editorview.enemy.subviews.editorfields.EnemySizeField;
import authoring.editorview.enemy.subviews.editorfields.AddEnemyEffectView;
import authoring.editorview.enemy.subviews.editorfields.DeleteEnemy;
import authoring.editorview.enemy.subviews.editorfields.EnemyDamageField;
import authoring.editorview.enemy.subviews.editorfields.EnemySpeedField;
import authoring.utilityfactories.ButtonFactory;
import authoring.utilityfactories.DialogueBoxFactory;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

public class EnemyEditorView extends PhotoFileChooser implements EnemySetView {

    private AnchorPane rootBuffer;
    private VBox vbox;
    private EnemyAuthoringViewDelegate delegate;
    private File chosenFile;

    private DeleteEnemy deleteEnemy;
    private EnemyImageView enemyImage;
    private EnemyNameField enemyName;
    private EnemySpeedField enemySpeed;
    private EnemyDamageField enemyDamage;
    private EnemyHealthField enemyHealth;
    private EnemyRewardMoneyField enemyRewardMoney;
    private EnemyRewardPointsField enemyRewardPoints;
    private EnemySizeField enemySize;
    private AddEnemyEffectView addEnemyEffect;

    private ResourceBundle labelsResource;
    private final String ENEMY_EFFECT_RESOURCE_PATH = "resources/GameAuthoringEnemy";
    private static final double BUFFER = 10.0;

    public EnemyEditorView (EnemyImageView enemyImage,
                            EnemyNameField enemyName,
                            EnemySpeedField enemySpeed,
                            EnemyDamageField enemyDamage,
                            EnemyHealthField enemyHealth,
                            EnemyRewardMoneyField enemyRewardMoney,
                            EnemyRewardPointsField enemyRewardPoints,
                            EnemySizeField enemySize,
                            DeleteEnemy deleteEnemy,
                            AddEnemyEffectView addEnemyEffect) {
        rootBuffer = new AnchorPane();
        vbox = new VBox(10);
        rootBuffer.getChildren().add(vbox);

        this.enemyImage = enemyImage;
        this.enemyName = enemyName;
        this.enemySpeed = enemySpeed;
        this.enemyDamage = enemyDamage;
        this.enemyHealth = enemyHealth;
        this.enemyRewardMoney = enemyRewardMoney;
        this.enemyRewardPoints = enemyRewardPoints;
        this.enemySize = enemySize;
        this.deleteEnemy = deleteEnemy;
        this.addEnemyEffect = addEnemyEffect;

        labelsResource = ResourceBundle.getBundle(ENEMY_EFFECT_RESOURCE_PATH);

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

        Button imageButton = ButtonFactory.makeButton(labelsResource.getString("Image"),
                                                      e -> {
                                                          try {
                                                              selectFile("Select new enemy image",
                                                                         "Photos: ");
                                                          }
                                                          catch (IOException e1) {
                                                              DialogueBoxFactory
                                                                      .createErrorDialogueBox("Unable to open file chooser",
                                                                                              "Try again");
                                                          }
                                                      });
        imageButton.setPrefWidth(280);
        vbox.getChildren().addAll(
                                  imageButton,
                                  enemyName.getInstanceAsNode(),
                                  enemySize.getInstanceAsNode(),
                                  enemySpeed.getInstanceAsNode(),
                                  enemyDamage.getInstanceAsNode(),
                                  enemyHealth.getInstanceAsNode(),
                                  enemyRewardMoney.getInstanceAsNode(),
                                  enemyRewardPoints.getInstanceAsNode(),
                                  addEnemyEffect.getInstanceAsNode(),
                                  deleteEnemy.getInstanceAsNode());
    }

    public void clearView () {
        vbox.getChildren().clear();
    }

    @Override
    public void setDelegate (EnemyAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            delegate.onUserEnteredEnemyImagePath(chosenFile.toURI().getPath());
        }
    }
}
