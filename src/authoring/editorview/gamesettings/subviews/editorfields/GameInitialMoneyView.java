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


public class GameInitialMoneyView implements GameSettingsSetView {

    private TextField initialMoneyField;
    private int initialMoney;
    private GameSettingsAuthoringViewDelegate delegate;
    private GridPane root;

    public GameInitialMoneyView (ResourceBundle labelsResource) {
        createField(labelsResource);
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void createField (ResourceBundle resource) {
        initialMoneyField = TextFieldFactory.makeTextField("",
                                                           e -> submitInitialMoney(initialMoneyField
                                                                   .getText()));

        initialMoneyField.setPrefWidth(105); // TODO magic number
        root =
                GridFactory.createRowWithLabelandNode(resource.getString("InitialMoney"),
                                                      initialMoneyField, 125);

    }

    public void updateInitialMoney (int money) {
        initialMoney = money;
        initialMoneyField.setText(Integer.toString(money));
    }

    private void submitInitialMoney (String moneyString) {
        try {
            initialMoney = Integer.parseInt(moneyString);
            delegate.onUserEnteredGameMoney(initialMoney);
        }
        catch (NumberFormatException e) {
            updateInitialMoney(initialMoney);
            Alert inputError =
                    DialogueBoxFactory.createErrorDialogueBox(
                                                              "The amount of money must be an integer.",
                                                              "Input error");
            // TODO move to resource file
        }
    }

}
