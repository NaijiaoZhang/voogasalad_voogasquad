package gameplayer.model.enemy;

import java.util.Observable;

import engine.effect.EffectMethod;
import gameplayer.model.Cell;
import gameplayer.model.IDrawable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Enemy extends Observable implements IDrawable {

	private String name;
	private double movingSpeed;
	private double health;
	private String image;
	private double xCoordinate;
	private double yCoordinate;
	private Cell currentCell;
	private int xDirection; // -1 if moving left, 1 if moving right
	private int yDirection; // -1 if moving down, 1 if moving up
	private double width, height;
	private int uniqueID;
	private Label enemyInfo;
	private boolean showInfo;
	private int pathID;
	private boolean shouldRemove;
	private double goldReward, scoreReward;

	public Enemy(int ID, String name, double movingSpeed, int health, String image, double width, double height, int pathNum, double goldAward, double scoreReawrd) {
		this.pathID = pathNum;
		this.uniqueID = ID;
		this.name = name;
		this.movingSpeed = movingSpeed;
		this.health = health;
		this.image = image;
		this.width = width;
		this.height = height;
		this.showInfo = false;
		this.enemyInfo = new Label("Name: " + name + "\nHealth: " + health);
		this.shouldRemove = false;
		this.goldReward = goldAward;
		this.scoreReward = scoreReawrd;
		initLabel();
	}

	private void initLabel(){
		this.enemyInfo.setLayoutX(getX());
		this.enemyInfo.setLayoutY(getY() + new ImageView(getImage()).getFitHeight());
		this.enemyInfo.setVisible(showInfo);
	}
	/*
	 * public double[] getWidthAndHeight(){ double[] weidthAndHeight =
	 * {this.width, this.height}; return weidthAndHeight; }
	 */
	
	public int getPathID() {
		return this.pathID;
	}
	
	public double getGoldReward(){
		return this.goldReward;
	}
	
	public double getScoreReward(){
		return this.scoreReward;
	}
	
	public void setUniqueID(int id) {
		this.uniqueID = id;
	}

	public int getUniqueID() {
		return this.uniqueID;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.height;
	}

	@EffectMethod
	public String getName() {
		return name;
	}

	@EffectMethod
	public int getxDirection() { // heading
		return xDirection;
	}

	@EffectMethod
	public void setxDirection(int xDirection) { // heading
		this.xDirection = xDirection;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public int getyDirection() {
		return yDirection;
	}

	@EffectMethod
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
		setChanged();
		notifyObservers();
	}

	
	public void setCurrentCell(Cell c) { // don't think we need to notify
											// observers of this change
		this.currentCell = c;
		//this.xDirection = c.getNext().getX() - c.getX();
		//this.yDirection = c.getNext().getY() - c.getY();
	}

	@EffectMethod
	public Cell getCurrentCell() {
		return this.currentCell;
	}

	@EffectMethod
	public double getX() {
		return xCoordinate;
	}

	@EffectMethod
	public void setX(double xCoordinate) {
		this.xCoordinate = xCoordinate;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public double getY() {
		return yCoordinate;
	}

	@EffectMethod
	public void setY(double yCoordinate) {
		this.yCoordinate = yCoordinate;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public void setImage(String image) { // might not need to notify observers
											// here
		this.image = image;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public String getImage() {
		//System.out.println("What is the image: "+image);
		int index=this.image.lastIndexOf("/")+1;
		String loc = this.image.substring(index, image.length());
		return loc;
	}

	@EffectMethod
	public double getMovingSpeed() {
		return movingSpeed;
	}

	@EffectMethod
	public void setMovingSpeed(double movingSpeed) {
		this.movingSpeed = movingSpeed;
		setChanged();
		notifyObservers();
	}

	@EffectMethod
	public double getHealth() {
		return health;
	}

	@EffectMethod
	public void setHealth(double d) {
		this.health = d;
		setChanged();
		notifyObservers();
	}

	public Label getEnemyInfo() {
		return enemyInfo;
	}
	
	public boolean mustRemove() {
		return this.shouldRemove;
	}
	
	public void setRemove(boolean removeOrNah) {
		this.shouldRemove = removeOrNah;
	}

	public void toggleInfoVisibility() {
		this.showInfo = !showInfo;
		this.enemyInfo.setVisible(showInfo);
	}

}
