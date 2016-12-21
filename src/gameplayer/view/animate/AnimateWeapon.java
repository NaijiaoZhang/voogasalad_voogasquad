package gameplayer.view.animate;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public class AnimateWeapon {
	
	private ImageView tower;
	
	public AnimateWeapon(ImageView tower){
		this.tower = tower;
	}
	
	public void throwWeapon(ImageView weapon, double x, double y){
		weapon.setX(tower.getX());
		weapon.setY(tower.getY());
	}

}
