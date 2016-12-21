package authoring.editorview.tower.subviews;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.tower.TowerSetView;
import authoring.editorview.tower.TowerAuthoringViewDelegate;
import authoring.editorview.tower.subviews.editorfields.AddTowerEffectView;
import authoring.editorview.tower.subviews.editorfields.DeleteTower;
import authoring.editorview.tower.subviews.editorfields.TowerAbilityBank;
import authoring.editorview.tower.subviews.editorfields.TowerBuyPriceField;
import authoring.editorview.tower.subviews.editorfields.TowerWeaponBank;
import authoring.editorview.tower.subviews.editorfields.TowerImageView;
import authoring.editorview.tower.subviews.editorfields.TowerNameField;
import authoring.editorview.tower.subviews.editorfields.TowerSellPriceField;
import authoring.editorview.tower.subviews.editorfields.TowerSizeField;
import authoring.editorview.tower.subviews.editorfields.TowerUnlockLevelField;
import authoring.editorview.tower.subviews.editorfields.TowerUpgradeBank;
import authoring.utilityfactories.ButtonFactory;
import authoring.utilityfactories.DialogueBoxFactory;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
 *
 */
public class TowerEditorView extends PhotoFileChooser implements TowerSetView {

    private TowerAuthoringViewDelegate delegate;
    private TowerNameField towerName;
    private TowerImageView towerImage;
    private TowerBuyPriceField towerBuyPrice;
    private TowerSellPriceField towerSellPrice;
    private TowerUnlockLevelField towerUnlockLevel;
    private TowerAbilityBank towerAbility;
    private TowerWeaponBank towerChooseWeapon;
    private TowerUpgradeBank towerUpgrade;
    private TowerSizeField towerSize;
    private AddTowerEffectView addTowerEffect;
    private DeleteTower deleteTower;

    private VBox vbox;
    private ScrollPane completeView;
    private AnchorPane rootBuffer;
    private File chosenFile;

    private static final double BUFFER = 10.0;

    private ResourceBundle labelsResource;
    private ResourceBundle dialogueBoxResource;

    public TowerEditorView (TowerNameField towerName,
                            TowerImageView towerImage,
                            TowerBuyPriceField towerBuyPrice,
                            TowerSellPriceField towerSellPrice,
                            TowerUnlockLevelField towerUnlockLevel,
                            TowerAbilityBank towerAbility,
                            TowerWeaponBank towerChooseWeapon,
                            TowerUpgradeBank towerUpgrade,
                            TowerSizeField towerSize,
                            AddTowerEffectView addTowerEffect,
                            DeleteTower deleteTower,
                            ResourceBundle labelsResource,
                            ResourceBundle dialogueBoxResource) {

        this.labelsResource = labelsResource;
        this.dialogueBoxResource = dialogueBoxResource;

        this.towerName = towerName;
        this.towerImage = towerImage;
        this.towerBuyPrice = towerBuyPrice;
        this.towerSellPrice = towerSellPrice;
        this.towerUnlockLevel = towerUnlockLevel;
        this.towerAbility = towerAbility;
        this.towerChooseWeapon = towerChooseWeapon;
        this.towerUpgrade = towerUpgrade;
        this.towerSize = towerSize;
        this.addTowerEffect = addTowerEffect;
        this.deleteTower = deleteTower;

        vbox = new VBox(10);
        rootBuffer = new AnchorPane();
        rootBuffer.getChildren().add(vbox);
        // completeView = new ScrollPane();
        // completeView.setContent(rootBuffer);

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

        vbox.getChildren().add(towerImage.getInstanceAsNode());
        Button imageButton = ButtonFactory.makeButton(labelsResource.getString("Image"),
                                                      e -> {
                                                          try {
                                                              selectFile(labelsResource
                                                                      .getString("Image"),
                                                                         labelsResource
                                                                                 .getString("Photos"));
                                                          }
                                                          catch (IOException e1) {
                                                              DialogueBoxFactory
                                                                      .createErrorDialogueBox(dialogueBoxResource
                                                                              .getString("UnableToOpen"),
                                                                                              dialogueBoxResource
                                                                                                      .getString("TryAgain"));
                                                          }
                                                      });
        imageButton.setPrefWidth(380);
        vbox.getChildren().addAll(
                                  imageButton,
                                  towerName.getInstanceAsNode(),
                                  towerSize.getInstanceAsNode(),
                                  towerBuyPrice.getInstanceAsNode(),
                                  towerSellPrice.getInstanceAsNode(),
                                  towerUnlockLevel.getInstanceAsNode(),
                                  addTowerEffect.getInstanceAsNode(),
                                  deleteTower.getInstanceAsNode());

        vbox.getChildren().add(towerAbility.getInstanceAsNode());
        vbox.getChildren().add(towerChooseWeapon.getInstanceAsNode());
        vbox.getChildren().add(towerUpgrade.getInstanceAsNode());
    }

    @Override
    public void setDelegate (TowerAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) throws IOException {
        chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            // System.out.println(chosenFile.toURI().getPath());
            delegate.onUserEnteredTowerImagePath(chosenFile.toURI().getPath());
        }
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

}
