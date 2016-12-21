package authoring.editorview.gamesettings.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.DialogueBoxFactory;
import authoring.utilityfactories.GridFactory;
import authoring.utilityfactories.TextFieldFactory;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class GamePathDimensionsView implements GameSettingsSetView {

    private GridPane root;
    private int dimensions;

    private TextField dimensionsTextField;
    private GameSettingsAuthoringViewDelegate delegate;

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringPath";
    private ResourceBundle pathResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    public GamePathDimensionsView () {
        makeGridDimensionsTextField();
    }

    @Override
    public Node getInstanceAsNode () {
        return root;

    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public int getGridDimensions () {
        return dimensions;
    }

    public void setGridDimensions (int dimensions) {
        this.dimensions = dimensions;
        dimensionsTextField.setText(Integer.toString(dimensions));
    }

    private void makeGridDimensionsTextField () {
        dimensionsTextField = TextFieldFactory.makeTextField("",
                                                             e -> submitGridDimensions(dimensionsTextField
                                                                     .getText()));

        dimensionsTextField.setPrefWidth(105); // TODO

        root =
                GridFactory.createRowWithLabelandNode(pathResource.getString("DimensionsTextField"),
                                                      dimensionsTextField, 125);

    }

    private void submitGridDimensions (String gridDimensionsString) {
        try {
            dimensions = Integer.parseInt(gridDimensionsString);
            delegate.onUserEnteredGameGridSize(dimensions);
            
        }
        catch (NumberFormatException e) {
            setGridDimensions(dimensions);
            Alert inputError =
                    DialogueBoxFactory.createErrorDialogueBox(
                                                              "The dimensions of the grid must be an integer.",
                                                              "Input error");
        }
    }

}
