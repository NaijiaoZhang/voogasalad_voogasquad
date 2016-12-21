package authoring.editorview.weapon.subviews;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import authoring.editorview.PhotoFileChooser;
import authoring.editorview.weapon.WeaponSetView;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.editorview.weapon.subviews.editorfields.AddWeaponEffectView;
import authoring.editorview.weapon.subviews.editorfields.DeleteWeapon;
import authoring.editorview.weapon.subviews.editorfields.WeaponFireRateField;
import authoring.editorview.weapon.subviews.editorfields.WeaponImageView;
import authoring.editorview.weapon.subviews.editorfields.WeaponNameField;
import authoring.editorview.weapon.subviews.editorfields.WeaponPathField;
import authoring.editorview.weapon.subviews.editorfields.WeaponRangeField;
import authoring.editorview.weapon.subviews.editorfields.WeaponSizeField;
import authoring.editorview.weapon.subviews.editorfields.WeaponSpeedField;
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
 *
 */
public class WeaponEditorView extends PhotoFileChooser implements WeaponSetView {

    private VBox vboxView;
    private AnchorPane rootBuffer;
    private WeaponAuthoringViewDelegate delegate;

    private static final double BUFFER = 10.0;
    private int editorWidth;

    private ResourceBundle labelsResource;
    private File chosenFile;
    private WeaponNameField weaponName;
    private WeaponSpeedField weaponSpeed;
    private WeaponFireRateField weaponFireRate;
    private WeaponRangeField weaponRange;
    private WeaponPathField weaponPath;
    private WeaponSizeField weaponSize;
    private AddWeaponEffectView addWeaponEffect;
    private DeleteWeapon deleteWeapon;

    private ResourceBundle dialogueBoxResource;

    public WeaponEditorView (WeaponNameField weaponName,
                             WeaponSpeedField weaponSpeed,
                             WeaponFireRateField weaponFireRate,
                             WeaponRangeField weaponRange,
                             WeaponPathField weaponPath,
                             WeaponImageView weaponImage,
                             WeaponSizeField weaponSize,
                             AddWeaponEffectView addWeaponEffect,
                             DeleteWeapon deleteWeapon,
                             ResourceBundle labelsResource,
                             ResourceBundle dialogueBoxResource,
                             int columnWidth)
        throws IOException {
        this.labelsResource = labelsResource;
        this.dialogueBoxResource = dialogueBoxResource;
        this.weaponName = weaponName;
        this.weaponSpeed = weaponSpeed;
        this.weaponFireRate = weaponFireRate;
        this.weaponRange = weaponRange;
        this.weaponPath = weaponPath;
        this.addWeaponEffect = addWeaponEffect;
        this.weaponSize = weaponSize;
        this.editorWidth = columnWidth;
        this.deleteWeapon = deleteWeapon;

        vboxView = new VBox(10);

        rootBuffer = new AnchorPane();
        rootBuffer.getChildren().add(vboxView);

        buildViewComponents();
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void buildViewComponents () throws IOException {

        AnchorPane.setLeftAnchor(vboxView, BUFFER);
        AnchorPane.setTopAnchor(vboxView, BUFFER);

        rootBuffer
                .setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID,
                                                       CornerRadii.EMPTY, new BorderWidths(0.5))));
        rootBuffer
                .setBackground(new Background(new BackgroundFill(Color.rgb(235, 235, 235),
                                                                 CornerRadii.EMPTY, Insets.EMPTY)));

        Button changeImage = ButtonFactory.makeButton(labelsResource.getString("Image"),
                                                      e -> {
                                                          try {
                                                              selectFile(labelsResource
                                                                      .getString("Photos"),
                                                                         labelsResource
                                                                                 .getString("NewWeapon"));
                                                          }
                                                          catch (IOException e1) {
                                                              DialogueBoxFactory
                                                                      .createErrorDialogueBox(dialogueBoxResource
                                                                              .getString("UnableToOpen"),
                                                                                              dialogueBoxResource
                                                                                                      .getString("TryAgain"));
                                                          }
                                                      });
        changeImage.setPrefWidth(editorWidth - 2 * ((int) BUFFER));
        vboxView.getChildren().add(changeImage);
        vboxView.getChildren().add(weaponName.getInstanceAsNode());
        vboxView.getChildren().add(weaponSize.getInstanceAsNode());
        vboxView.getChildren().add(weaponFireRate.getInstanceAsNode());
        vboxView.getChildren().add(weaponSpeed.getInstanceAsNode());
        vboxView.getChildren().add(weaponRange.getInstanceAsNode());
        vboxView.getChildren().add(weaponPath.getInstanceAsNode());
        vboxView.getChildren().add(addWeaponEffect.getInstanceAsNode());
        vboxView.getChildren().add(deleteWeapon.getInstanceAsNode());
    }

    @Override
    public Node getInstanceAsNode () {
        return rootBuffer;
    }

    public void createNewWeapon () {
        // Call all of the other update methods to get the default values
    }

    @Override
    public void openFileChooser (FileChooser chooseFile) {
        chosenFile = chooseFile.showOpenDialog(new Stage());
        if (chosenFile != null) {
            delegate.onUserEnteredWeaponImagePath(chosenFile.toURI().getPath());
        }
        // if not null -> get imageFilePath and update the instance variable
        // then loadImage through the created method above
    }

}
