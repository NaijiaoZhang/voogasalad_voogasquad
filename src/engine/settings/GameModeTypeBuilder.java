package engine.settings;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import engine.AbstractTypeBuilder;

import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;
import engine.path.PathOption;

public class GameModeTypeBuilder extends AbstractTypeBuilder<GameMode, GameModeBuilder> implements GameModeBuilder, GameModeInitializer {

	public static final String DEFAULT_NAME = "New Game";
    public static final String DEFAULT_IMAGE_PATH = "Images/greensquare.png";
    public static final double DEFAULT_SIZE = 1;
    public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";
    public static final String[] DEFAULT_WINNING_CONDITIONS = new String[]{};
    public static final String[] DEFAULT_LOSING_CONDITIONS = new String[]{};
    public static final String DEFAULT_GAME_TYPE = "Normal";
    public static final int DEFAULT_INITIAL_LIVES = 1;
    public static final int DEFAULT_INITIAL_MONEY = 5000;
    public static final PathOption DEFAULT_PATH_TYPE = PathOption.SET;
    public static final int DEFAULT_GRID_SIZE = 10;
    public static final Integer[] DEFAULT_PATHS = new Integer[]{};
    
    private ObservableProperty<String> gameType;
    private ObservableList<String> winningConditions;
    private ObservableList<String> losingConditions;
    private ObservableProperty<Integer> initialLives;
    private ObservableProperty<Integer> initialMoney;
    private ObservableProperty<PathOption> pathType;
    private ObservableProperty<Integer> gridSize;
    private ObservableList<Integer> paths;
	
	public GameModeTypeBuilder() {
		super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
	}

	@Override
	public GameModeBuilder addWinningConditionsListener(BiConsumer<List<String>, List<String>> listener) {
		winningConditions.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addLosingConditionsListener(BiConsumer<List<String>, List<String>> listener) {
		losingConditions.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addGameTypeListener(BiConsumer<String, String> listener) {
		gameType.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addInitialLivesListener(BiConsumer<Integer, Integer> listener) {
		initialLives.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addInitialMoneyListener(BiConsumer<Integer, Integer> listener) {
		initialMoney.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addGridSizeListener(BiConsumer<Integer, Integer> listener) {
		gridSize.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addPathTypeListener(BiConsumer<PathOption, PathOption> listener) {
		pathType.addListener(listener);
		return this;
	}

	@Override
	public GameModeBuilder addPathListener(BiConsumer<List<Integer>, List<Integer>> listener) {
		paths.addListener(listener);
		return this;
	}

	
	@Override
	public ObservableList<String> getWinningConditions() {
		return winningConditions;
	}

	@Override
	public ObservableList<String> getLosingConditions() {
		return losingConditions;
	}

	@Override
	public ObservableProperty<Integer> getInitalLives() {
		return initialLives;
	}

	@Override
	public ObservableProperty<Integer> getInitialMoney() {
		return initialMoney;
	}

	@Override
	public ObservableProperty<String> getGameType() {
		return gameType;
	}

	@Override
	public ObservableProperty<PathOption> getPathType() {
		return pathType;
	}

	@Override
	public ObservableProperty<Integer> getGridSize() {
		return gridSize;
	}

	@Override
	public ObservableList<Integer> getPaths() {
		return paths;
	}

	@Override
	public GameModeBuilder buildWinningConditions(List<String> winningConditions) {
		this.winningConditions.setProperty(winningConditions);
		return this;
	}

	@Override
	public GameModeBuilder buildLosingConditions(List<String> losingConditions) {
		this.losingConditions.setProperty(losingConditions);
		return this;
	}

	@Override
	public GameModeBuilder buildGameType(String gameType) {
		this.gameType.setProperty(gameType);
		return this;
	}

	@Override
	public GameModeBuilder buildInitialLives(int lives) {
		this.initialLives.setProperty(lives);
		return this;
	}

	@Override
	public GameModeBuilder buildInitialMoney(int money) {
		this.initialMoney.setProperty(money);
		return this;
	}

	@Override
	public GameModeBuilder buildGridSize(int size) {
		this.gridSize.setProperty(size);
		return this;
	}

	@Override
	public GameModeBuilder buildPaths(List<Integer> paths) {
		this.paths.setProperty(paths);
		return this;
	}

	@Override
	public GameModeBuilder buildPathType(PathOption pathType) {
		this.pathType.setProperty(pathType);
		return this;
	}
	
	@Override
	protected GameMode create() {
		return new GameModeType(this);
	}

	@Override
	protected void restoreTypeDefaults() {
		this.winningConditions = new ObservableListProperty<String>(Arrays.stream(DEFAULT_WINNING_CONDITIONS).collect(Collectors.toList()));
		this.losingConditions = new ObservableListProperty<String>(Arrays.stream(DEFAULT_LOSING_CONDITIONS).collect(Collectors.toList()));
		this.gameType = new ObservableObjectProperty<String>(DEFAULT_GAME_TYPE);
		this.initialLives = new ObservableObjectProperty<Integer>(DEFAULT_INITIAL_LIVES);
		this.initialMoney = new ObservableObjectProperty<Integer>(DEFAULT_INITIAL_MONEY);
		this.gridSize = new ObservableObjectProperty<Integer>(DEFAULT_GRID_SIZE);
		this.paths = new ObservableListProperty<Integer>(Arrays.stream(DEFAULT_PATHS).collect(Collectors.toList()));
		this.pathType = new ObservableObjectProperty<PathOption>(DEFAULT_PATH_TYPE);
	}

	@Override
	protected GameModeBuilder getThis() {
		return this;
	}

	@Override
	protected GameModeBuilder copyType(GameMode type) {
		return this
		.buildGameType(type.getGameType())
		.buildInitialLives(type.getInitalLives())
		.buildInitialMoney(type.getInitialMoney())
		.buildLosingConditions(type.getLosingConditions())
		.buildWinningConditions(type.getWinningConditions())
		.buildGridSize(type.getGridSize())
		.buildPaths(type.getPaths())
		.buildPathType(type.getPathType());
	}
}
