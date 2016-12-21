package authoring.utilityfactories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;


/**
 * Factory to create textFields required by view components
 * 
 * @author Kayla Schulz
 *
 */
public final class TextFieldFactory {

    private TextFieldFactory () {

    }

    /**
     * 
     * @param promptText
     * @param event (ActionEvent)
     * @return TextField with promptText and corresponding ActionEvent
     */
    public static TextField makeTextField (String promptText, EventHandler<ActionEvent> event) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setOnAction(event);
        return textField;
    }

}
