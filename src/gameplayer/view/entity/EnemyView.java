package gameplayer.view.entity;

import java.util.Observable;
import java.util.Observer;

import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.image.ImageView;

public class EnemyView{
	private GraphicsLibrary graphics;
	private String imagePath;
	private ImageView enemy;
	private double currenthealth;
	

	public EnemyView(String imagePath, double width, double height, double health) {
		this.graphics = new GraphicsLibrary();
		this.imagePath = imagePath;
		init(width, height);

	}
	
	public ImageView getEnemyView(){
		return this.enemy;
	}
	
	private void init(double width, double height){
		this.enemy = graphics.createImageView(graphics.createImage(imagePath));
		graphics.setImageViewParams(this.enemy, width, height);
	}
	
	public void update(int x, int y){
		this.enemy.setX(x);
		this.enemy.setY(y);
	}
	
	private void createLifeBar(){
		
	}

}
