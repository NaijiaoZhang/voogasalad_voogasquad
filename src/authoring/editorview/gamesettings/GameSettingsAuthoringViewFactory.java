package authoring.editorview.gamesettings;

/**
 * 
 * @author Kayla Schulz
 *
 */
public class GameSettingsAuthoringViewFactory {

    private GameSettingsAuthoringViewFactory () {
        // Does Nothing
    }

    public static GameSettingsUpdateView build (int width, int height) {
        return new GameSettingsAuthoringView(width, height);
    }

}
