package authoring.utilityfactories;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * Creates the different type of VBoxes and HBoxes necessary for the view components of authoring
 * 
 * @author Kayla Schulz
 *
 */
public final class BoxFactory {

    private BoxFactory () {

    }

    /**
     * Creates HBox with label and textField
     * 
     * @param labelString for textField label
     * @param textField
     * @return HBox containing label and textField
     */
    public static HBox createHBoxWithLabelandNode (String labelString, Node textField) {
        HBox box = new HBox(5);
        Label label = new Label(labelString);
        box.getChildren().addAll(label, textField);
        return box;
    }

    /**
     * Creates VBox with label
     * 
     * @param labelString
     * @return VBox with label
     */
    public static VBox createVBox (String labelString) {
        VBox box = new VBox(5);
        Label label = new Label(labelString);
        box.getChildren().add(label);
        return box;
    }

}
