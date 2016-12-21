package authoring.view;

/**
 * Creates the overall authoring view
 * 
 * @author Kayla Schulz
 *
 */
public class AuthoringViewFactory {

    private AuthoringViewFactory () {
        // Does Nothing
    }

    /**
     * 
     * @param width
     * @param height
     * @return Authoring View Interface
     */
    public static IAuthoringView build (int width, int height) {
        return new AuthoringView(width, height);
    }

}
