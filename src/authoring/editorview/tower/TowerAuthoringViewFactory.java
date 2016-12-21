package authoring.editorview.tower;

/**
 * 
 * @author Diane Hadley
 *
 */

public class TowerAuthoringViewFactory {

    public static TowerUpdateView build (int width, int height) {

        return new TowerAuthoringView();
    }

}
