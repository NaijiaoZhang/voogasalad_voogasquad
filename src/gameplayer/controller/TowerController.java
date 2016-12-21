package gameplayer.controller;

import java.util.ArrayList;
import java.util.Map;

import engine.weapon.WeaponType;
import gameplayer.model.tower.Tower;
import gameplayer.model.tower.TowerManager;
import gameplayer.model.weapon.Weapon;
import gameplayer.view.GameGUI;
import gameplayer.view.GridGUI;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TowerController {
	private TowerManager towerManager;
	private GraphicsLibrary graphicsLib;
	private GameGUI view;
	private boolean sellable;

	// create back end code for firing a weapon
	/**
	 * get all weaponTypes from all towers on the grid check on fire rate and do
	 * math to fire weapon to map create all the newly fired weapon into an
	 * arraylist<weapon> then call weaponManager's method updateWeapon(ArrayList
	 * <Weapon> newlyGeneratedWeapons) the method above will both updated
	 * current weapon on map and add the new weapons on the position of the
	 * tower
	 * 
	 */

	public TowerController(TowerManager towerManager, GameGUI view) {
		this.towerManager = towerManager;
		this.graphicsLib = new GraphicsLibrary();
		this.view = view;
	}

	private void sellTower(int id) {
		this.towerManager.sellTower(id);
	}

	public void handleSellTowerClick(Tower t) {

		Map<Integer, Tower> towersOnGrid = towerManager.getTowerOnGrid();
		for (Map.Entry<Integer, Tower> entry : towersOnGrid.entrySet()) {
			if(entry.getValue().equals(t)){
				sellTower(entry.getKey());
			}

		}
	}
	
	public void upgrade(int uniqueID){
		this.towerManager.upgradeTower(uniqueID);
	}

	private void createButton() {
		Button sellTower = graphicsLib.createButton("Sell tower", e -> {
			this.sellable = true;
		});
	}

	/**
	 * migrate placing tower to here
	 */

}
