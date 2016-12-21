package authoring.view;

import authoring.editortabpane.IEditorTabPane;
import authoring.toolbar.IToolbar;
import java.util.List;
import authoring.editortabpane.EditorTabPaneDelegate;
import javafx.scene.Node;
import javafx.scene.Scene;


/**
 * An interface to define the external API in regards to the creation of
 * the authoring screen.
 *
 * @author Kayla Schulz
 */
public interface IAuthoringView {

    /**
     * Returns the current scene of the game authoring environment
     * 
     * @return Scene of game authoring environment
     */
    public Scene getScene ();

    public void setEditorView (Node editor);

    public void createEditorTabPane (List<String> tabs);

    public void setEditorTabPaneDelegate (EditorTabPaneDelegate delegate);

    public IEditorTabPane getMySideTabbedToolbar ();

    public IToolbar getMyToolbar ();

}
