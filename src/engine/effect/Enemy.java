package engine.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import gameplayer.model.Cell;
import gameplayer.model.IDrawable;

public class Enemy extends SuperEnemy implements IDrawable, ITestEnemy{
	
	private String name; 
	private double movingSpeed;  
	private double health;
	private String image;
	private double xCoordinate;
	private double yCoordinate;
	private Cell currentCell;
	private int xDirection; //-1 if moving left, 1 if moving right
	private int yDirection; //-1 if moving down, 1 if moving up	
	private double width, height;
	private int uniqueID;
	private Cell enemyCell;
	private List<EffectType> effects;
	
	public Enemy() {
		this.effects = new ArrayList<EffectType>();
		this.health = 50;
		this.name = "Sean";
	}
	
	
	public void addEffect(EffectType effect) {
	    effects.add(effect);
	}
	
	public int getEffectsSize() {
	    return effects.size();
	}
	
	/*
	public double[] getWidthAndHeight(){
		double[] weidthAndHeight = {this.width, this.height};
		return weidthAndHeight;
	}
	*/
	public void setCell(Cell c){
		this.enemyCell = c; 
	}
	
	public Cell getCell(){
		return this.enemyCell;
	}
	
	public void setUniqueID(int id){
		this.uniqueID = id;
	}
	
	public int getUniqueID(){
		return this.uniqueID;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public double getHeight(){
		return this.height;
	}
	
	
	@EffectMethod
	public String getName(){
		return name; 
	}
	
	public int getxDirection() { //heading
		return xDirection;
	}

	
	public void setxDirection(int xDirection) { //heading
		this.xDirection = xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}

	@EffectMethod
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}


	public void setCurrentCell(Cell c){ //don't think we need to notify observers of this change
		this.currentCell = c;
		this.xDirection = c.getNext().getX() - c.getX();
		this.yDirection = c.getNext().getY() - c.getY();
	}
	
	public Cell getCurrentCell(){
		return this.currentCell;
	}
	
	public double getX() {
		return xCoordinate;
	}
	
	public void setX(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getY() {
		return yCoordinate;
	}

	public void setY(double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public void setImage(String image) { //might not need to notify observers here
		this.image = image;
	}

	
	public String getImage(){
		return this.image;
	}

	public double getMovingSpeed() {
		return movingSpeed;
	}

	public void setMovingSpeed(double movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

	/* (non-Javadoc)
     * @see engine.effect.ITestEnemy#getHealth()
     */
	@Override
    public double getHealth() {
		return health;
	}

	public void setHealth(double d) {
		this.health = d;
	}

}
