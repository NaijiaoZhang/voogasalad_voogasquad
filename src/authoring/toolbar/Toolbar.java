package authoring.toolbar;

import java.io.File;
import engine.FileAggregator;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * 
 * @author Diane Hadley
 * @author Kayla Schulz
 *
 */
public class Toolbar implements IToolbar {

    private ToolBar toolbar;

    private Button saveButton;

    private Button loadButton;

    private Button previewButton;

    public Toolbar (int aWidth, int aHeight) {

        saveButton = new Button("Save");
        loadButton = new Button("Load");
        previewButton = new Button("Preview");
        this.toolbar = new ToolBar(
                                   saveButton,
                                   loadButton,
                                   previewButton);

    }

    @Override
    public void setOnPressedLoad (EventHandler<MouseEvent> e) {
        loadButton.setOnMouseClicked(e);
    }

    @Override
    public Node getInstanceAsNode () {
        return toolbar;
    }

    @Override
    public void setOnPressedSave (EventHandler<MouseEvent> e) {
        saveButton.setOnMouseClicked(e);
    }

    public String loadFile () {
        FileChooser choose = new FileChooser();
        choose.setTitle("Load game");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
        choose.getExtensionFilters().add(extFilter);
        return choose.showOpenDialog(new Stage()).getPath();

    }

    
    
    public void saveFile (String content) {
        FileChooser choose = new FileChooser();
        choose.setTitle("Save game");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");
        choose.getExtensionFilters().add(extFilter);
        File newFile = choose.showSaveDialog(new Stage());
        FileAggregator.defaultInstance().writeXMLFile(newFile, content);
    }
    
    
//    public void saveFile (String content) {
//        FileChooser choose = new FileChooser();
//        choose.setTitle("Save game");
//        // FileChooser.ExtensionFilter extFilter =
//        // new FileChooser();
//        // choose.getExtensionFilters().add(extFilter);
//        File gameFile = choose.showSaveDialog(new Stage());
//        if (gameFile != null) {
//            FileAggregator.defaultInstance().createGameFolder(gameFile, content);
//        }
//    }

    @Override
    public void setOnPressedPreview (EventHandler<MouseEvent> e) {
        // TODO Auto-generated method stub

    }

}
