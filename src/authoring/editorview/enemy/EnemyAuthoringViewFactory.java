package authoring.editorview.enemy;

/**
 * 
 * @author Diane Hadley
 *
 */

public class EnemyAuthoringViewFactory {

    public static EnemyUpdateView build (int width, int height) {
        return new EnemyAuthoringView();
    }

}
