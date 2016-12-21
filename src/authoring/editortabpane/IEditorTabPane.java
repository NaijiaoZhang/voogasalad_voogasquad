package authoring.editortabpane;

import javafx.scene.Node;


/**
 * 
 * @author Kayla Schulz
 *
 */
public interface IEditorTabPane {

    public Node getInstanceAsNode ();

    public void setDelegate (EditorTabPaneDelegate delegate);

    public String getViewToOpen ();
}
