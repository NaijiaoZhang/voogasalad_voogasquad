package authoring.editortabpane;

import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Much of this and the idea for the ribbon menu comes from the following link:
 * https://introjava.wordpress.com/2012/04/08/java-fx-2-ribbon-menu/
 * 
 * @author Kayla Schulz
 *
 */
public class EditorTabPane implements IEditorTabPane {

    private TabPane tabPane;
    private EditorTabPaneDelegate delegate;
    private String viewToOpen;
    private List<String> tabNames;

    public EditorTabPane (int width, int height, List<String> tabs) {
        viewToOpen = "Setup"; // default, change this later
        tabPane = new TabPane();
        tabNames = tabs;
        tabPane.setSide(Side.RIGHT);
        tabPane.setTabMaxHeight(120);
        tabPane.setTabMaxWidth(30);

        tabPane.setTabMinHeight(120);
        tabPane.setTabMinWidth(30);

        buildRibbonMenu();
    }

    private void buildRibbonMenu () {
        ToggleGroup group1 = new ToggleGroup();
        group1.selectedToggleProperty()
                // Set Change Text if toggled
                .addListener( (ObservableValue<? extends Toggle> ov,
                               Toggle old_toggle,
                               Toggle new_toggle) -> {
                    if (new_toggle == null)
                        return;
                    if (new_toggle.isSelected()) {
                    }
                });

        for (String name : tabNames) {
            ToggleButton button = buildButton(name, name.toLowerCase() + ".png",
                                              event -> delegate.userSelectedTab(name));
            button.setToggleGroup(group1);
            buildTabs(button);
        }

    }

    @Override
    public String getViewToOpen () {
        return viewToOpen;
    }

    private void buildTabs (ToggleButton button) {
        Tab tab = new Tab();
        tab.setGraphic(button);
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    private ToggleButton buildButton (String text,
                                      String fileName,
                                      EventHandler<ActionEvent> handler) {
        ToggleButton button = new ToggleButton();
        button.setText(text);
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(fileName));
        ImageView curImage = new ImageView(image);
        curImage.setFitHeight(30);
        curImage.setFitWidth(30);
        button.setGraphic(curImage);
        button.setGraphicTextGap(5);
        button.setMinSize(120, 30);
        button.setOnAction(handler);
        return button;
    }

    @Override
    public Node getInstanceAsNode () {
        return tabPane;
    }

    @Override
    public void setDelegate (EditorTabPaneDelegate delegate) {
        this.delegate = delegate;
    }

}
