package gameplayer.model.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import engine.tower.TowerType;
import engine.weapon.Weapon;
import engine.weapon.WeaponType;
import gameplayer.model.Cell;
import gameplayer.model.GamePlayData;
import gameplayer.model.Grid;
import gameplayer.model.Path;
import gameplayer.model.effect.GameEffect;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import gameplayer.model.weapon.WeaponManager;
import gameplayer.view.GridGUI;

/**
 * FOR FRONT END: availableTowerTpyes would be changed by level changes
 * updateAvailableTower() is notify observer method
 * 
 * timeInterval which is a fixed time interval between each call for fireWeapon()
 * getTimeInterval() returns a long, name it x, the actual time is 1/x second
 * for front end control the time
 * 
 * 
 * added sell Tower method which will remove tower from on grid list and update the gold amount
 *   
 * talk to game authoring to make sure the fire rate is actually an int
 * so firerate per second makes sense
 * 
 * @author yuminzhang
 *
 */
public class TowerManager extends Observable {
	private static final int UPGRADE_COST = 100;
	private GamePlayData gameData;
	private EnemyManager enemyManager;
	private Map<Integer, engine.tower.Tower> allTowerTypes;
	private HashMap<Integer, engine.tower.Tower> availableTowerTypes;
	private Map<Integer, engine.weapon.Weapon> allWeapons;	
	private HashMap<Integer, gameplayer.model.tower.Tower> towersOnGrid; 
	private Map<Integer, engine.tower.Tower> upgradeTowerTypes;
	private int uniqueTowerID;
	private long timeInterval;

	public static final int WEAPONYERROR = 20;
	public static final int WEAPONXERROR = 40;


	public TowerManager(GamePlayData gameData, EnemyManager enemyManager) {
		this.gameData = gameData;
		this.enemyManager = enemyManager;
		this.allTowerTypes = this.gameData.getFactory().getTowers();
		this.allWeapons = this.gameData.getFactory().getWeaponBank();
		this.upgradeTowerTypes = this.gameData.getFactory().getTowerUpgrades();
		this.towersOnGrid =  new HashMap<Integer, gameplayer.model.tower.Tower>();
		this.uniqueTowerID = 0;
		this.availableTowerTypes = new HashMap<Integer, engine.tower.Tower>();

	}

	public HashMap<Integer, engine.tower.Tower> getAvailableTower() {
		return this.availableTowerTypes;
	}

	/**
	 * this method should not be called !!!
	 */
	public void populateAvailableTower() {
		int level = gameData.getCurrentLevel();
		//System.out.println("in updateAvailableTower: level:" + level);
		//System.out.println("Alltowertypes: ");
		//System.out.println(allTowerTypes);
		for (int i=0;i<allTowerTypes.keySet().size();i++) {
			//System.out.println("At tower #: "+i);
			//System.out.println("At "+i+":"+allTowerTypes.get(i));
			availableTowerTypes.put(i, allTowerTypes.get(i));

		}
		//System.out.println("All available tower types: ");
		//System.out.println(this.availableTowerTypes);
	}


	//this method does not populate available towers for the initial round, so available towers 
	//stays null
	public void updateAvailableTower() {
		int level = gameData.getCurrentLevel();
		//System.out.println("in updateAvailableTower: level:" + level);
		//System.out.println("Alltowertypes: ");
		//System.out.println(allTowerTypes);
		for (int i : allTowerTypes.keySet()) {
			if (allTowerTypes.get(i).getUnlockLevel() == level) {
				availableTowerTypes.put(i, allTowerTypes.get(i));
			}
		}
		//System.out.println("All available tower types: ");
		//System.out.println(this.availableTowerTypes);
		calculateTimeInterval();
		setChanged();
		notifyObservers();

	}

	public Map<Integer,gameplayer.model.tower.Tower> getTowerOnGrid() { // fix naming
		return this.towersOnGrid;
	}
	
	
	public void initializeTowerForLoading(){
		Cell[][] grid = this.gameData.getGridArray();
		for(int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				Tower t = grid[i][j].getTower();
				if(t != null){
					this.towersOnGrid.put(t.getUniqueID(), t);
				}
			}
		}
	}

	/**
	 * 
	 * @param type
	 * @param x
	 *            y are in pixel coordinates
	 * @return
	 */
	public Boolean placeTower(int type, int x, int y) {
		// System.out.println("Placetower in pixel coord: x:" + x + ",y:" + y);

		// add error checking later to see if no such type available
		engine.tower.Tower towerType = availableTowerTypes.get(type);
		//System.out.println("========================");
		//System.out.println("contains weapon: " + towerType.getWeapons().get(0));
		//System.out.println("number of tower on grid: " + this.towersOnGrid.size());


		int x1 = (int) (x / this.gameData.getCellWidth());
		int y1 = (int) (y / this.gameData.getCellHeight());
		if (!canPlaceTower(x1, y1, towerType.getCost())) {
			//System.out.println("cannot be placed!!!!!!");
			return false;
		}

		List<Integer> weaponTypes = towerType.getWeapons();
		ArrayList<Gun> gunForTower = new ArrayList<Gun>();
		// System.out.println("all the int weapons: " + gunsForTower.size());
		for (int i : weaponTypes) {
			engine.weapon.Weapon weaponForGun = this.allWeapons.get(i);
			int fireRate =  (int) ((int) this.timeInterval/weaponForGun.getReloadTime());
			double x2 = this.gameData.cellToCoordinate(x1);
			double y2 = this.gameData.cellToCoordinate(y1);
			//System.out.println("plaCE tower x1 "+x1);
			//System.out.println("plaCE tower y1 "+y1);

			//System.out.println("place tower x2: "+x2);
			//System.out.println("place tower y2: "+y2);

			Gun tempGun = new Gun(fireRate ,weaponForGun, x2+WEAPONXERROR, y2+WEAPONYERROR);// change fire rate 
			gunForTower.add(tempGun);
		}

		gameplayer.model.tower.Tower newlyPlaced = new gameplayer.model.tower.Tower(towerType, gunForTower,
				this.uniqueTowerID);
		newlyPlaced.setCoordinates(x1, y1);
		this.towersOnGrid.put(uniqueTowerID, newlyPlaced);
		uniqueTowerID++;

		this.gameData.setGold(this.gameData.getGold() - newlyPlaced.getCost());
		this.gameData.getGrid().placeTower(newlyPlaced, (int) x1, (int) y1);
		return true;

	}

	private boolean canPlaceTower(int xcood, int ycoord, double cost) {
		Cell placingLocation = this.gameData.getGridArray()[xcood][ycoord];
		if (placingLocation.getTower() != null)
			return false;
		
		
		if (placingLocation.getNext() != null || onEndCell(placingLocation)) {
			return false;
		}

		if (this.gameData.getGold() - cost < 0)
			return false;

		return true;
	}
	
	private Boolean onEndCell(Cell c){
		Grid g = this.gameData.getGrid();
		if(g.isNoPathType()){
			return false;
		}
		
		else{
			HashMap<Integer,Path> allPath = g.getAllPaths();
			for(int i : allPath.keySet()){
				if(c.equals(allPath.get(i))){
					return true;
				}
			}
		}
		
		return false;		
	}

	private void calculateTimeInterval(){
		ArrayList<Long> allFireRate = new ArrayList<Long>();
		for (int i : this.allTowerTypes.keySet()){
			for(int j: allTowerTypes.get(i).getWeapons()){
				allFireRate.add((long)this.allWeapons.get(j).getReloadTime());
			}
		}

		Long[] allFireRates = allFireRate.toArray(new Long[allFireRate.size()]); 

		this.timeInterval = this.gameData.lcm(allFireRates);

	}

	public long getTimeInterval(){
		return this.timeInterval;
	}


	/**
	 * need to call this method in controller
	 */
	public void sellTower(int uniqueTowerID){
		Tower t = this.towersOnGrid.get(uniqueTowerID);
		System.out.println(t.getImage());
		this.gameData.setGold(this.gameData.getGold() + t.sellTower());
		this.gameData.getGrid().removeTower((int)t.getX(),(int)t.getX());
		this.towersOnGrid.remove(uniqueTowerID);

		//don't need this
		//setChanged();
		//notifyObservers();
	}

	/**
	 * add a ungrade queue into the tower class with reverse order from ArrayList
	 * talk to front end when queue is empty disable the upgrade buttom 
	 * display upgrade cost on front end
	 *
	 * 
	 * @param UniqueID
	 */
	public void upgradeTower(int UniqueID){
		Tower toBeUpgraded = this.towersOnGrid.get(UniqueID);
		
//		int upgradeType = toBeUpgraded.getUpgradeType();
//		engine.tower.Tower upgraded = this.upgradeTowerTypes.get(upgradeType); // change back to upgradeType when xml is fixed
		/*
		for(int i : this.upgradeTowerTypes.keySet()){
			System.out.println("key set: " + i);
		}
		*/
		//System.out.println("tower upgrade check: " +upgraded== null );
		//System.out.println("tower upgrade check: " +upgraded.getCost() );
		
		this.gameData.setGold(this.gameData.getGold() - UPGRADE_COST);
//		toBeUpgraded.setImage(upgraded.getImagePath());
		toBeUpgraded.setImage("Images/supermonkey.png"); 
		 toBeUpgraded.incrementUpgradeCount();
		 System.out.println(toBeUpgraded.getUpgradeCount());
//		List<Integer> weaponTypes = upgraded.getWeapons();
//		ArrayList<Gun> gunForTower = new ArrayList<Gun>();
//		// System.out.println("all the int weapons: " + gunsForTower.size());
//		for (int i : weaponTypes) {
//			engine.weapon.Weapon weaponForGun = this.allWeapons.get(i);
//			int fireRate =  (int) weaponForGun.getReloadTime();
//			Gun tempGun = new Gun(fireRate, weaponForGun,this.gameData.cellToCoordinate(toBeUpgraded.getX()) , this.gameData.cellToCoordinate(toBeUpgraded.getY())); 
//			gunForTower.add(tempGun);
//		}
//		toBeUpgraded.setGuns(gunForTower);


	}

	/**
	 * get all the weapons fired by the towers currently on Grid
	 */
	public ArrayList<gameplayer.model.weapon.Weapon> generateNewWeapons(Map<Integer, GameEffect> allEffects){
		ArrayList<gameplayer.model.weapon.Weapon> newlyFired = new ArrayList<gameplayer.model.weapon.Weapon>();
		HashMap<Integer, Enemy> enemyOnGrid = this.enemyManager.getEnemyOnGrid();

		for(int i: this.towersOnGrid.keySet()){
			for(Gun g : towersOnGrid.get(i).getAllWeaponTypes()){
				if(g.isFiring()){
					for(int j : enemyOnGrid.keySet()){
						Enemy e = enemyOnGrid.get(j);
						Enemy target = enemyOnGrid.get(j);
						newlyFired.add(g.getWeapon(j,target.getX(), target.getY(), allEffects ));							
						break;
					}
				}
			}
		}

		//		System.out.println("+++++++++++++++++++++");
		//		System.out.println("=====================");
		//
		//		System.out.println("number of weapon fired: " + newlyFired.size());
		//		System.out.println("newlyFired"+newlyFired.size());
		return newlyFired;
	}

}
