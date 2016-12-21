package authoring.utilityfactories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/**
 * Factory to create buttons required by view components
 * 
 * @author Kayla Schulz
 *
 */
public final class ButtonFactory {

    private ButtonFactory () {

    }

    /**
     * Creates button with text and event
     * 
     * @param buttonText
     * @param event (ActionEvent)
     * @return button with text and corresponding action
     */
    public static Button makeButton (String buttonText, EventHandler<ActionEvent> event) {
        Button button = new Button(buttonText);
        button.setOnAction(event);
        return button;
    }

}
