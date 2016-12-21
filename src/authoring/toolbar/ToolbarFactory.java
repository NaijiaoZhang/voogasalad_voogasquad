package authoring.toolbar;

/**
 * Builds toolbar and returns Toolbar Interface
 * 
 * @author Kayla Schulz
 *
 */
public class ToolbarFactory {

    private ToolbarFactory () {
        // Does Nothing
    }

    /**
     * 
     * @param width
     * @param height
     * @return Toolbar Interface
     */
    public static IToolbar build (int width, int height) {
        return new Toolbar(width, height);
    }

}
