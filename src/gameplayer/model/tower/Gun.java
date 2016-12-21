package gameplayer.model.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import gameplayer.model.effect.GameEffect;
import gameplayer.model.weapon.Weapon;

public class Gun {

	private int fireCounter = 0;
	private double firingRate, attackingRange;  // need to figure out the firing rate int or double!!!
	private engine.weapon.Weapon weaponType;
	private double xcoor, ycoor; // in pixel
	
	public Gun(int firingRate, engine.weapon.Weapon weaponType,  double x, double y) {
		this.firingRate = firingRate;
		this.weaponType = weaponType; 
		this.attackingRange = weaponType.getRange();
		this.xcoor = x;
		this.ycoor = y; 
	}
	
	public double getX(){
		return this.xcoor;
	}
	
	public double getY(){
		return this.ycoor;
	}
	
	public double getRange(){
		return this.attackingRange;
	}
	
	public boolean isFiring(){
		fireCounter++;
		if(fireCounter % firingRate == 0)
			return true;
		return false;
	}
	
	public Weapon getWeapon(int targetID, double targetX, double targetY, Map<Integer, GameEffect> allEffects){

		 String name = weaponType.getName(); 
		 double demage = 50; // get from weapon type later
		 String image = weaponType.getImagePath();
		 double speedMag = weaponType.getSpeed();
		 List<Integer> effects = weaponType.getEffects();
		 HashMap<String, ArrayList<GameEffect>> weaponEffect = new HashMap<String,ArrayList<GameEffect>>();
		 for(int i: effects){
			 GameEffect ge = allEffects.get(i);
			 String trigger = ge.getTriggerClass(); 
			 if(!weaponEffect.keySet().contains(trigger)){
				 ArrayList<GameEffect> effectByTrigger = new ArrayList<GameEffect>();
				 weaponEffect.put(trigger, effectByTrigger);
			 }
			 weaponEffect.get(trigger).add(ge);			 
		 }
		 
		 return new Weapon (name,  demage, targetX, targetY, image, this.attackingRange,targetID, this.xcoor,this.ycoor,speedMag, weaponEffect ,this.weaponType.getId() );
	}

}
