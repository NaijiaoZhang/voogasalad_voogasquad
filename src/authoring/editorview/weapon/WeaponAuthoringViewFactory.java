package authoring.editorview.weapon;

import java.io.IOException;


/**
 * 
 * @author Diane Hadley
 *
 */

public class WeaponAuthoringViewFactory {

    public static WeaponUpdateView build (int width, int height) throws IOException {
        return new WeaponAuthoringView(width, height);
    }

}
