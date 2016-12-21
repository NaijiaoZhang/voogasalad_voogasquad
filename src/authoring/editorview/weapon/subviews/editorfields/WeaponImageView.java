package authoring.editorview.weapon.subviews.editorfields;

import java.io.File;
import java.util.ResourceBundle;
import authoring.editorview.weapon.WeaponSetView;
import authoring.editorview.weapon.WeaponAuthoringViewDelegate;
import authoring.utilityfactories.DialogueBoxFactory;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WeaponImageView implements WeaponSetView {

    private AnchorPane root;
    private WeaponAuthoringViewDelegate delegate;
    private ResourceBundle labelsResource;
    private String imagePath;
    private ImageView weaponImage;

    private final int CHARACTER_SIZE = 250;

    public WeaponImageView (ResourceBundle labelsResource) {
        this.labelsResource = labelsResource;
        weaponImage = new ImageView();
        weaponImage.setFitWidth(CHARACTER_SIZE);
        weaponImage.setFitHeight(CHARACTER_SIZE);
        root = new AnchorPane();
        root.getChildren().add(weaponImage);
        AnchorPane.setLeftAnchor(weaponImage, 125.0);
        AnchorPane.setTopAnchor(weaponImage, 175.0);
    }

    public void updateWeaponImagePath (String imagePath) {
        this.imagePath = imagePath;
        loadWeaponImage();
    }

    private void loadWeaponImage () {
        try {
            File file = new File(imagePath);
            Image image = new Image(file.toURI().toString());
            weaponImage.setImage(image);
        }
        catch (Exception e) {
            Image image2 =
                    new Image(getClass().getClassLoader().getResourceAsStream("questionmark.png"));
            weaponImage.setImage(image2);
            DialogueBoxFactory.createErrorDialogueBox("Could not load file",
                                                      "Try new photo");
        }
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (WeaponAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

}
