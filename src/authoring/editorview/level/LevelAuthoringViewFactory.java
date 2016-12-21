package authoring.editorview.level;

/**
 * 
 * @author Kayla Schulz
 *
 */
public class LevelAuthoringViewFactory {

    public static LevelUpdateView build (int width, int height) {

        return new LevelAuthoringView(width, height);

    }

}
