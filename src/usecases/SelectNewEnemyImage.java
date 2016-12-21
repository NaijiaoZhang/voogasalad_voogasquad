package usecases;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import authoring.editorview.enemy.EnemyUpdateView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class SelectNewEnemyImage {
    private File myFile;
    private ResourceBundle myExtensionBundle;
    private static final int ENEMY_SIZE = 40;
    private Map<Integer, File> myEnemyImages;
    private int count;
    private void openFileChooser (FileChooser chooseFile) {
        myFile = chooseFile.showOpenDialog(new Stage());
        if (myFile != null) {
            loadSelectedImage(myFile);
        }
    }
    private void selectFile () {
        List<String> extensionList = myExtensionBundle.keySet().stream()
                .map(s -> {
                    return myExtensionBundle.getString(s);
                })
                .collect(Collectors.toList());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image Files:", extensionList);
        fileChooser.getExtensionFilters().add(extFilter);
        openFileChooser(fileChooser);
    }
    private void loadSelectedImage (File aFile) {
        myFile = aFile;
        ImageView myEnemyImageView;
        myEnemyImageView = new ImageView(new Image(myFile.toURI().toString()));
        myEnemyImageView.setFitHeight(ENEMY_SIZE);
        myEnemyImageView.setFitWidth(ENEMY_SIZE);
        myEnemyImages.put(count, myFile);
    }
}