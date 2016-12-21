package authoring.utilityfactories;

import java.util.List;
import java.util.function.Consumer;
import org.controlsfx.control.CheckComboBox;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;


/**
 * Factory to create comboboxes required by view components
 * 
 * @author Kayla Schulz
 * @author Diane Hadley
 *
 */
public final class ComboBoxFactory {

    private ComboBoxFactory () {

    }

    /**
     * 
     * @param promptText
     * @param event (ActionEvent)
     * @param options (List)
     * @return generic comboBox with promptText, corresponding event, and list options
     */
    public static ComboBox<Object> makeComboBox (String promptText,
                                                 EventHandler<ActionEvent> event,
                                                 ObservableList<Object> options) {
        ComboBox<Object> combobox = new ComboBox<Object>(options);
        combobox.setOnAction(event);
        combobox.setPromptText(promptText);
        return combobox;
    }

    public static CheckComboBox<String> makeCheckComboBox (
                                                           ObservableList<String> options,
                                                           Consumer<List<String>> func) {
        CheckComboBox<String> checkComboBox = new CheckComboBox<String>(options);
        checkComboBox.getCheckModel().getCheckedItems()
                .addListener(new ListChangeListener<String>() {

                    @Override
                    public void onChanged (javafx.collections.ListChangeListener.Change<? extends String> c) {

                        func.accept(checkComboBox.getCheckModel().getCheckedItems());
                    }

                });

        return checkComboBox;

    }

}
