package authoring.editorview;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;


/**
 * 
 * @author Kayla Schulz
 *
 */
public abstract class PhotoFileChooser {

    private File chosenFile;

    private static final String RESOURCE_FILE_NAME = "resources/PhotoFileChooser";
    protected ResourceBundle photoFileResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    public PhotoFileChooser () {

    }

    public File getChosenFile () {
        return chosenFile;
    }

    public void selectFile (String fieldText, String chooserTitle) throws IOException {
        FileChooser choose = new FileChooser();
        choose.setTitle(chooserTitle);
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter(fieldText, "*.png", "*.jpg", "*.gif", "*.jpeg");
        choose.getExtensionFilters().add(extFilter);
        openFileChooser(choose);
    }

    public abstract void openFileChooser (FileChooser chooseFile) throws IOException;
}
