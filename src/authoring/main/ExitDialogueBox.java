package authoring.main;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * 
 * @author Diane Hadley
 *
 */

public class ExitDialogueBox {

    public void displayDialogueBoxOnExit (Stage s, EventHandler<ActionEvent> saveEvent) {
        s.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle (WindowEvent event) {

                Dialog<ButtonType> cd = new Dialog<ButtonType>();
                cd.setContentText("Do you want to save the changes you made?");

                ButtonType save = new ButtonType("Save", ButtonData.YES);
                ButtonType dontSave = new ButtonType("Don't Save", ButtonData.LEFT);
                ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                cd.getDialogPane().getButtonTypes().setAll(dontSave, cancel, save);

                Optional<ButtonType> response = cd.showAndWait(); // http://code.makery.ch/blog/javafx-dialogs-official/

                final Button btsave = (Button) cd.getDialogPane().lookupButton(save);
                btsave.setOnAction(saveEvent);

                if (response.get() == cancel) {
                    event.consume();
                }

                if (response.get() == save) {
                    btsave.fire();
                }

            }

        });

    }
}
