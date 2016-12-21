package engine.settings;

import java.util.Collections;
import java.util.List;

import authoring.editorview.gamesettings.GameSettingsUpdateView;
import engine.AbstractTypeManagerController;
import engine.ManagerMediator;
import engine.MethodObjectData;
import engine.effect.EffectManagerController;
import engine.effect.EffectTypeManagerController;
import engine.path.PathOption;


public class GameModeTypeManagerController 
	extends AbstractTypeManagerController<GameModeManager, GameModeBuilder, GameMode, GameSettingsUpdateView> implements GameModeManagerController {

	private EffectManagerController gameModeEffectManagerController;
	
	public GameModeTypeManagerController(ManagerMediator managerMediator) {
		super(new GameModeTypeManager(), new GameModeTypeBuilder(), managerMediator);
		this.gameModeEffectManagerController = new EffectTypeManagerController(managerMediator, getTypeManager().getGameModeEffectManager());
	}

	@Override
	public void setNumberofLives(int gameModeID, int lives) {
		getTypeManager().getEntity(gameModeID).setInitialLives(lives);
	}

	@Override
	public int getNumberofLives(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getInitalLives();
	}

	@Override
	public void setMoney(int gameModeID, int money) {
		getTypeManager().getEntity(gameModeID).setInitialMoney(money);
	}

	@Override
	public int getMoney(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getInitialMoney();
	}

	@Override
	public void addWinningCondition(int gameModeID, String winningCondition) {
		getTypeManager().getEntity(gameModeID).setWinningCondition(winningCondition);
	}

	@Override
	public void addLosingCondition(int gameModeID, String losingCondition) {
		getTypeManager().getEntity(gameModeID).setLosingCondition(losingCondition);
	}

	@Override
	public void removeWinningCondition(int gameModeID, String winningCondition) {
		getTypeManager().getEntity(gameModeID).removeWinningCondition(winningCondition);
	}

	@Override
	public void removeLosingCondition(int gameModeID, String losingCondition) {
		getTypeManager().getEntity(gameModeID).removeLosingCondition(losingCondition);
	}

	@Override
	public List<String> getWinningConditions(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getWinningConditions();
	}

	@Override
	public List<String> getLosingConditons(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getLosingConditions();
	}

	@Override
	protected GameModeBuilder constructTypeProperties(GameSettingsUpdateView updateView, GameModeBuilder typeBuilder) {
		return typeBuilder //.addGameTypeListener( (oldValue, newValue) -> updateView.updateGameName(newValue))
				.addInitialMoneyListener((oldValue, newValue) -> updateView.updateInitialMoney(newValue))
				.addInitialLivesListener((oldValue, newValue) -> updateView.updateNumberofLives(newValue.intValue()))
				.addLosingConditionsListener((oldValue, newValue) -> updateView.updateLosingConditions(newValue))
				.addWinningConditionsListener((oldValue, newValue) -> updateView.updateLosingConditions(newValue))
				.addPathListener((oldValue, newValue) -> updateView.updatePathList(newValue))
				.addGridSizeListener((oldValue, newValue) -> updateView.updateGridSize(newValue))
				.addPathTypeListener((oldValue, newValue) -> updateView.updatePathType(newValue.name()));
				
	}

	@Override
	public void setPathType(int gameModeID, String pathType) {
		getTypeManager().notifyObservers(new MethodObjectData<Object>("GridSize", gameModeID) );
		getTypeManager().getEntity(gameModeID).setPathType(PathOption.valueOf(pathType));
		getTypeManager().getEntity(gameModeID).clearPaths();
	}

	@Override
	public String getPathType(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getPathType().name();
	}

	@Override
	public void setGridSize(int gameModeID, int gridSize) {
		getTypeManager().notifyObservers(new MethodObjectData<Object>("GridSize", gameModeID) );
		getTypeManager().getEntity(gameModeID).setGridSize(gridSize);
		getTypeManager().getEntity(gameModeID).clearPaths();
	}

	@Override
	public int getGridSize(int gameModeID) {
		int gridSize = getTypeManager().getEntity(gameModeID).getGridSize();
		getTypeManager().notifyObservers(new MethodObjectData<Object>("GridSize", gameModeID) );
		return gridSize;
	}

	@Override
	public void addPath(int gameModeID, int pathID) {
		getTypeManager().notifyObservers(new MethodObjectData<Object>("AddPath", pathID));
		getTypeManager().getEntity(gameModeID).addPath(pathID);
	}

	@Override
	public void removePath(int gameModeID, int pathID) {
		getTypeManager().notifyObservers(new MethodObjectData<Object>("RemovePath", pathID));
		getTypeManager().getEntity(gameModeID).removePath(pathID);
	}

	@Override
	public List<Integer> getPaths(int gameModeID) {
		return getTypeManager().getEntity(gameModeID).getPaths();
	}

	@Override
	public EffectManagerController getEffectManagerController() {
		return gameModeEffectManagerController;
	}

}
