package authoring.editortabpane;

import java.util.List;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class EditorTabPaneFactory {

    private EditorTabPaneFactory () {
        // Does Nothing
    }

    public static IEditorTabPane build (int aWidth, int aHeight, List<String> tabs) {
        return new EditorTabPane(aWidth, aHeight, tabs);
    }

}
