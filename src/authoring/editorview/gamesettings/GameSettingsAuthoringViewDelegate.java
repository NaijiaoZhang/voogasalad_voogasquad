package authoring.editorview.gamesettings;

import java.util.List;
import engine.path.Coordinate;


/**
 * 
 * @author Diane Hadley
 * @author Kayla Schulz
 *
 */
public interface GameSettingsAuthoringViewDelegate {

    public void onUserEnteredGameLives (int lives);

    public void onUserEnteredGameMoney (int money);

    public void onUserEnteredGameName (String name);

    public void onUserEnteredGameImage (String imagePath);

    public void onUserEnteredGameGridSize (int size);

    public void onUserEnteredGamePathType (String type);

    public void onUserEnteredWinningConditions (String winConditions);

    public void onUserEnteredLosingConditions (String loseConditions);

    public void onUserEnteredPath (int pathID);

    public void onUserEnteredRemovePath (int pathID);

    public String getPathName (int pathID);

    public String getPathImage (int pathID);

    public List<Integer> getAvailablePathList ();

    public int getPathDimensions (int pathID);

    public List<Coordinate<Integer>> getPathCoordinates (int pathID);

    public List<Integer> getLevelIDs ();

    public String getLevelName (int levelID);

    public void onUserPressedAddEffect ();

}
