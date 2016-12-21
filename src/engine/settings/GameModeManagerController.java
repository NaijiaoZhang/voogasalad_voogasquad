package engine.settings;

import java.util.List;

import authoring.editorview.gamesettings.GameSettingsUpdateView;
import engine.ManagerController;
import engine.effect.EffectManagerController;

public interface GameModeManagerController extends ManagerController<GameModeManager, GameModeBuilder, GameMode, GameSettingsUpdateView>{
		
	public void setNumberofLives (int gameModeID, int lives);
	
	public int getNumberofLives (int gameModeID);
	
	public void setMoney (int gameModeID, int money);
	
	public int getMoney(int gameModeID);
	
	public void addWinningCondition (int gameModeID, String winningCondition);
    
    public void addLosingCondition (int gameModeID, String losingCondition);
    
    public void removeWinningCondition (int gameModeID, String winningCondition);
    
    public void removeLosingCondition (int gameModeID, String losingCondition);
    
    public List<String> getWinningConditions (int gameModeID);
    
    public List<String> getLosingConditons (int gameModeID);
    
    public void setPathType(int gameModeID, String pathType);
    
    public String getPathType(int gameModeID);
    
    public void setGridSize(int gameModeID, int gridSize);
    
    public int getGridSize(int gameModeID);
    
    public void addPath(int gameModeID, int pathID);
    
    public void removePath(int gameModeID, int pathID);
    
    public List<Integer> getPaths(int gameModeID);
    
    public EffectManagerController getEffectManagerController ();

}
