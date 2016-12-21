package gameplayer.model;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

import engine.effect.EffectManager;
import engine.effect.EffectMethod;
import gameplayer.loader.*;
import gameplayer.model.effect.CollisionEffectFactory;
import gameplayer.model.effect.GameEffect;
import gameplayer.model.effect.WinEffectFactory;
import gameplayer.view.GridGUI;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import statswrapper.Wrapper;

public class GamePlayData extends Observable{
	private GamePlayerFactory factory;
	private IntegerProperty currentLevel;

	private Grid grid;
	private Cell[][] gridArray;
	private int gridX;
	private int gridY;
	private DoubleProperty gold;
	private DoubleProperty lives;
	private DoubleProperty numLevels; // reach level number winning the game
	private DoubleProperty score;
	//CELL SIZE MUST BE INITIATED

	
	private Boolean lose = false;
	private Boolean win = false;
	private WinEffectFactory winFactory;
	private EffectManager effectManager;
	private Map<Integer, GameEffect> allEffects;	


	
	public GamePlayData(GamePlayerFactory factory){
		this.numLevels = new SimpleDoubleProperty();
		this.lives = new SimpleDoubleProperty();
		this.gold = new SimpleDoubleProperty();
		this.score = new SimpleDoubleProperty();
		this.currentLevel = new SimpleIntegerProperty();
		this.factory = factory;	
		
		this.winFactory = new WinEffectFactory(this);
		this.effectManager = this.factory.getWinEffectManager(); // get win effect Manager in xml in game setting.get effect manager 
		this.allEffects = effectManager.getEntities().entrySet().stream().collect(Collectors.toMap(e-> e.getKey(), e->winFactory.create(e.getValue())));
		for (int i : this.allEffects.keySet()){
			this.score.addListener((Observable, oldValue, newValue) -> allEffects.get(i).execute());
			this.gold.addListener((Observable, oldValue, newValue) -> allEffects.get(i).execute());
			this.currentLevel.addListener((Observable, oldValue, newValue) -> allEffects.get(i).execute());

		}		
		
	}
	
	/**
	 * could be used when start another game
	 * 
	 * @param factory
	 */
	public void initializeGameSetting() {
		HashMap<String, Double> settingInfo = this.factory.getGameSetting();
		//System.out.println("total levels: "+ settingInfo.get("totalNumberOfLevels"));
		//this.numLevels.setValue(2.0);
		this.numLevels.set(settingInfo.get("totalNumberOfLevels"));  // put into property file
		this.gold.set(settingInfo.get("gold"));
		this.lives.set(settingInfo.get("lives").intValue());
		this.currentLevel.set(settingInfo.get("levelnumber").intValue()); //REMEMBER TO CHANGE
		//System.out.println("initial level number: "+this.currentLevel.get());
		this.score.set(0);
	}
	
	public void initializeLevelInfo() {
		System.out.println("game data level: "+this.currentLevel.get());
		setLevel(this.currentLevel.get());
		this.grid = this.factory.getGrid(this.currentLevel.get());
		gridArray = this.grid.getGrid();
		this.gridX = this.gridArray.length;
		this.gridY = this.gridArray[0].length;
		// get level rewards and change current score, life, gold according
	}
	
	@EffectMethod
	public void setWin(){
		//System.out.println("==================");
		//System.out.println("total number of levels: " + this.numLevels.get());
		//System.out.println("current level number: " + this.currentLevel.get());

		this.win = true;
		setChanged();
		notifyObservers();
	}
	
	@EffectMethod
	public void setLose() {
		this.lose= true;
	}

	public boolean won(){
		return this.win;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
		this.gridArray = grid.getGrid();
	}
	
	public GamePlayerFactory getFactory(){
		return this.factory;
	}
	
	public int getRow() {
		return this.gridX;
	}

	public int getColumns() {
		return this.gridY;
	}


	public Grid getGrid() {
		//System.out.println("Successfully got grid");
		return this.grid;
	}
	
	public Cell[][] getGridArray() {
		return this.gridArray;
	}

	@EffectMethod
	public double getLevelNumber() {
		return this.numLevels.get();
	}

	@EffectMethod
	public double getGold() {
		return gold.get();
	}
	
	@EffectMethod
	public double getScore(){
		return this.score.get();
	}
	
	public void setScore(double additionalScore){
		this.score.set(this.score.get() + additionalScore );
		try {
			Wrapper.getInstance().logScore(Double.toString(this.score.get()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public void setGold(double gold) {
		//System.out.println("Setting gold");
		this.gold.set(gold);
		try {
			Wrapper.getInstance().updateGameScores("gold", Integer.toString((int)this.currentLevel.get()), Double.toString(this.gold.get()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public double getLife() {
		return this.lives.get();
	}

	// used by enemymodel
	public void setLife(double life) {
		this.lives.set((int)life);
		try {
			Wrapper.getInstance().updateGameScores("lives", Integer.toString(this.currentLevel.get()), Double.toString(this.lives.get()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}

	public void setLevel(int d) {
		System.out.println("set level to be: " + d);
		System.out.println("set level check win:  " + this.win);
		this.currentLevel.set(d);;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public int getCurrentLevel() {
		return this.currentLevel.get();
	}
	
	@EffectMethod
	public int getTotalLevel() {
		return (int)this.numLevels.get();
	}

	public double getCellWidth() {
		return GridGUI.GRID_WIDTH / this.getColumns();
	}

	public double getCellHeight() {
		return GridGUI.GRID_HEIGHT / this.getRow();
	}

	public double cellToCoordinate(double d) {
		return (d) * getCellWidth();
	}
	
	/**
	 * utility methods for each single classes
	 */
	public Boolean coordinateInBound(double x, double y) {
		return (x < GridGUI.GRID_WIDTH && y < GridGUI.GRID_HEIGHT && x > 0 && y > 0);
	}

	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	private long gcd(long a, long b)
	{
	    while (b > 0)
	    {
	        long temp = b;
	        b = a % b; 
	        a = temp;
	    }
	    return a;
	}

	private long gcd(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = gcd(result, input[i]);
	    return result;
	}
	
	private long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	public long lcm(Long[] allFireRates)
	{
	    long result = allFireRates[0];
	    for(int i = 1; i < allFireRates.length; i++) result = lcm(result, allFireRates[i]);
	    return result;
	}


}
