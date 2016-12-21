package gameplayer.model.weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;

import engine.effect.EffectManager;
import engine.effect.EffectTypeManager;
import gameplayer.model.GamePlayData;
import gameplayer.model.effect.CollisionEffectFactory;
import gameplayer.model.effect.GameEffect;
import gameplayer.model.tower.Gun;
import gameplayer.model.tower.TowerManager;
import gameplayer.view.GridGUI;
import javafx.scene.image.ImageView;

public class WeaponManager extends Observable{
	private GamePlayData gameData;
	private TowerManager towerManager;
	private HashMap<Integer, Weapon> weaponOnGrid;
	private int uniqueWeaponID;
	private long timeInterval;
	private int tempCountFix;
	private CollisionEffectFactory collisionFactory;
	private EffectManager effectManager;
	private Map<Integer, GameEffect> allEffects;
	
	// all effects are gameEffect
	
	
	// from engine.WeaponManager.getEffectManager effectTypeManager
	// getEntities() return a hashmap<int, Effect(raw)>
	//convert raw effect into gameEffect
	// collisionFactory.create(Effect(raw)) return the gameEffect
	// hashmap<int, gameEffect> = effectTypeManager.getEntities().entries().stream().collect(Colletctors.tomap(e-> e.getkey(), e->collisionFactory.create(e.getValue))
	
	// add encompassing class (weapon w) at creation
	
	//at collision time
	// weapon.getEffect() -> arrayList of int
	// effect =  hashmap.get()
	// effect.addTrigger()
	// effect.execute()
	
	// gameEffect.getTriggerClass()
	
	



	public WeaponManager(GamePlayData gameData, TowerManager towerManager) {
		this.gameData = gameData;
		this.towerManager = towerManager;
		this.timeInterval = this.towerManager.getTimeInterval();
		this.tempCountFix = 1; 
		initializeNewLevel();
		this.collisionFactory = new CollisionEffectFactory(gameData); // only used to turn into gameEffect
		this.effectManager = gameData.getFactory().getWeaponEffectManager(); // is effectManager the same as effectType Manager????? 		
		this.allEffects = effectManager.getEntities().entrySet().stream().collect(Collectors.toMap(e-> e.getKey(), e->collisionFactory.create(e.getValue())));

	}

	public void initializeNewLevel(){
		weaponOnGrid = new HashMap<Integer, Weapon>();
		uniqueWeaponID = 0;
	}
	
	public HashMap<Integer, Weapon> getWeaponOnGrid(){
		return this.weaponOnGrid;
	}
	
	public long timeInterval(){
		return this.timeInterval;
	}


	public void updateWeapon(HashMap<Integer,ImageView>weaponsOnScreen) {
		//newly fired weapon
		if(this.tempCountFix % 20 == 0){//VERY TEMP FIX MAKE BULLETS ONCE PER SECOND
			ArrayList<Weapon> newlyGeneratedWeapons = this.towerManager.generateNewWeapons(this.allEffects);
			for (int i = 0; i < newlyGeneratedWeapons.size(); i++){
				newlyGeneratedWeapons.get(i).setUniqueID(this.uniqueWeaponID);
				this.weaponOnGrid.put(uniqueWeaponID, newlyGeneratedWeapons.get(i));
				uniqueWeaponID++;
			}
			tempCountFix = 1; 
		}
		else{
			tempCountFix++; 
		}

		Iterator<Integer>weaponOnGridIterate = weaponOnGrid.keySet().iterator(); 
		while(weaponOnGridIterate.hasNext()){
			Integer i = weaponOnGridIterate.next();
			Weapon w = weaponOnGrid.get(i);
//			System.out.println("weaponsOnGrid Size: "+weaponOnGrid.size());
				
			if (w.getX() < GridGUI.GRID_WIDTH) {
				w.setX(w.getSpeedX() + w.getX());
			}
			if (w.getY() < GridGUI.GRID_HEIGHT) {
				w.setY(w.getSpeedY() + w.getY());
			}

			if (!this.gameData.coordinateInBound(w.getX(), w.getY())) {
				weaponOnGridIterate.remove();
				weaponsOnScreen.remove(w.getUniqueID());
			}
		}

		setChanged();
		notifyObservers();
	}


}
