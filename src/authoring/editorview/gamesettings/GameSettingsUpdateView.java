package authoring.editorview.gamesettings;

import java.util.List;
import authoring.editorview.IUpdateView;
import authoring.editorview.ListDataSource;


public interface GameSettingsUpdateView extends GameSettingsSetView, IUpdateView {

    public void updateNumberofLives (int lives);

    public void updateInitialMoney (int money);

    public void updateGridSize (int size);

    public void updatePathList (List<Integer> pathList);

    public void updateAvailablePaths (List<Integer> availablePathList);

    public void updatePathType (String pathType);

    public void updateWinningConditions (List<String> winningConditions);

    public void updateLosingConditions (List<String> losingConditions);

    public void setGameSettingsListDataSource (ListDataSource source);

}
