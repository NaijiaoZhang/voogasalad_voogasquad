package gameplayer.model.weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import gameplayer.model.IDrawable;
import gameplayer.model.effect.GameEffect;

public class Weapon extends Observable implements IDrawable{
	
	private int uniqueID;
	private double damage;
	private double speedX;
	private double speedY;
	private String name;
	private String image;
	private double xCoordinate;
	private double yCoordinate;
	private double distanceTravelled;
	//private double initialX, initialY;
	private double range;
	private int targetEnemyID;
	private double speedMag;
	private int weaponTypeID;
	private HashMap<String, ArrayList<GameEffect>> weaponEffect;
	
	
	public Weapon(String name,  double demage, double targetX, double targetY, String image,  double range, int targetID, double x, double y, double speedMag, HashMap<String, ArrayList<GameEffect>> weaponEffect, int weaponTypeID) {
		this.name = name;
		this.damage = demage;
		this.damage = 50;
		this.speedX = speedMag*(targetX-x)/(Math.sqrt(targetX*targetX+x*x));
		this.speedY  = speedMag*(targetY-y)/(Math.sqrt(targetY*targetY+y*y));
		this.image = image;
		this.distanceTravelled = 0;
		this.range = range;
		this.xCoordinate = x;
		this.yCoordinate = y;
		
		this.targetEnemyID = targetID;
		//System.out.println("TARGET ENEMY IDDDDDD "+ this.targetEnemyID);
		this.speedMag = speedMag;
		this.weaponEffect = weaponEffect;
		
		/*
		System.out.println();
>>>>>>> 6804c3a6943d043933632ab14cb7138487ba9d41

		//System.out.println("++++++++++++++++++++++++");
		//System.out.println();
		for(String s: weaponEffect.keySet()){
			for(GameEffect e : weaponEffect.get(s)){
				//System.out.println("trigger name: " + s);
				//System.out.println("effec name: " + e.toString());

				e.addEncompassingClass(this);
			}			
		}
		*/

		this.weaponTypeID = weaponTypeID;
	}
	
	
	
	public void triggerEffect(Object e){
		String className = e.getClass().getName();
		System.out.println("Class Name:  " + className);
		ArrayList<GameEffect> triggered = this.weaponEffect.get(className);
		System.out.println("Triggered gameeffect: "+triggered);
		for(GameEffect g : triggered){
			g.addTrigger(e);
			g.execute();
		}
	}
	
	
	// add boolean method out of range
	public int getWeaponTypeID(){
		return this.weaponTypeID;
	}
	
	public int getTargetEnemyID() {
		return this.targetEnemyID;
	}



	public Boolean inRange(){
		return this.range >= this.distanceTravelled;
	}
	
	
	public String getName(){
		return this.name;
	}
	

	
	
	public void incrementDistanceTravelled(double x, double y) {
		this.distanceTravelled += Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	

	

	public double getSpeedX(){
		return this.speedX;
	}
	
	public double getSpeedY(){
		return this.speedY;
	}
	
	/*
	 * can factor out to a moving object class with enemy class
	 */
	public double getX() {
		return xCoordinate;
	}

	public void setX(double xCoordinate) {
		this.xCoordinate = xCoordinate;
		setChanged();
		notifyObservers();
	}

	public double getY() {
		return yCoordinate;
	}

	public void setY(double yCoordinate) {
		this.yCoordinate = yCoordinate;
		setChanged();
		notifyObservers();
	}
	
	public String getImage(){
		return this.image;
	}

	public int getUniqueID() {
		return this.uniqueID;
	}

	public void setUniqueID(int iD) {
		this.uniqueID = iD;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}

	
	
}
