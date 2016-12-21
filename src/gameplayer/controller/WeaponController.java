package gameplayer.controller;	

import java.util.ArrayList;
import java.util.HashMap;

import gameplayer.model.weapon.Weapon;
import gameplayer.model.weapon.WeaponManager;

public class WeaponController {
	private WeaponManager weaponManager;
	private long updateRate;
	
	
	public WeaponController(WeaponManager weaponManager) {
		this.weaponManager = weaponManager;
		this.updateRate = weaponManager.timeInterval();
	}
	
	
	/** for FRONT END
	 * need a method keep calling weaponManager.update()
	 * the time interval calling this method should be 1/this.updateRate
	 * weaponManager.update() will generates new weapons from tower as well as moving all weapons currently on grid
	 * weaponOnGrid is a hashMap mapping from uniqueID to actual weapon object
	 * 
	 */
	public HashMap<Integer,Weapon> getWeaponOnGrid(){
		return this.weaponManager.getWeaponOnGrid();
	}


}
