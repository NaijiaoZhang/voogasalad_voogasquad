package engine.settings;

import java.util.ArrayList;
import java.util.List;

import engine.AbstractType;
import engine.observer.ObservableList;
import engine.observer.ObservableProperty;
import engine.path.PathOption;

public class GameModeType extends AbstractType implements GameMode {


    private ObservableProperty<String> gameType;
    private ObservableList<String> winningConditions;
    private ObservableList<String> losingConditions;
    private ObservableProperty<Integer> initialLives;
    private ObservableProperty<Integer> initialMoney;
    private ObservableProperty<PathOption> pathType;
    private ObservableProperty<Integer> gridSize;
    private ObservableList<Integer> paths;

	protected GameModeType(GameModeInitializer gameInitializer) {
		super(gameInitializer);
		this.gameType = gameInitializer.getGameType();
		this.winningConditions = gameInitializer.getWinningConditions();
		this.losingConditions = gameInitializer.getLosingConditions();
		this.initialLives = gameInitializer.getInitalLives();
		this.initialMoney = gameInitializer.getInitialMoney();
		this.pathType = gameInitializer.getPathType();
		this.gridSize = gameInitializer.getGridSize();
		this.paths = gameInitializer.getPaths();
	}
	


    public List<String> getWinningConditions() {
        return winningConditions.getProperty();
    }

    public void setWinningCondition(String winningCondition) {
        winningConditions.add(winningCondition);
    }

    public List<String> getLosingConditions() {
        return losingConditions.getProperty();
    }

    public void setLosingCondition(String losingCondition) {
        losingConditions.add(losingCondition);
    }

    public String getGameType() {
        return gameType.getProperty();
    }

    public void setGameType(String gameType) {
        this.gameType.setProperty(gameType); 
    }

	@Override
	public int getInitalLives() {
		return initialLives.getProperty();
	}

	@Override
	public void setInitialLives(int lives) {
		initialLives.setProperty(lives);
	}



	@Override
	public int getInitialMoney() {
		return initialMoney.getProperty();
	}



	@Override
	public void setInitialMoney(int money) {
		initialMoney.setProperty(money);
	}



	@Override
	public void removeWinningCondition(String winningCondition) {
		winningConditions.remove(winningCondition);
	}



	@Override
	public void removeLosingCondition(String losingCondition) {
		losingConditions.remove(losingCondition);
	}



	@Override
	public void setPathType(PathOption pathType) {
		this.pathType.setProperty(pathType);
	}



	@Override
	public PathOption getPathType() {
		return pathType.getProperty();
	}



	@Override
	public void setGridSize(int gridSize) {
		this.gridSize.setProperty(gridSize);
	}



	@Override
	public int getGridSize() {
		return gridSize.getProperty();
	}



	@Override
	public void addPath(int pathID) {
		this.paths.add(pathID);
	}



	@Override
	public void removePath(int pathID) {
		this.paths.remove(pathID);
	}



	@Override
	public List<Integer> getPaths() {
		return paths.getProperty();
	}



	@Override
	public void clearPaths() {
		paths.setProperty(new ArrayList<>());
	}
}
