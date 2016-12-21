package gameplayer.view.statsdisplay;

import gameplayer.view.helper.GraphicsLibrary;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Framework for heads up/stats display. Information should be updated
 * automatically, displayed in a variety of formats, and automatically
 * positioned within a HUD that is separate from the game screen
 * 
 * @author lucyzhang
 *
 */
public class StatsDisplay {

	private static final String LEVEL = "LevelType: ";
	private static final String LIVES_LEFT = "Lives left: ";
	private static final String AMOUNT_OF_GOLD = "Amount of gold: ";
	private static final String SCORE = "Score: ";
	private HBox scorePane;
	private GraphicsLibrary graphics;
	private Label goldLabel;
	private Label liveLabel;
	private Label levelLabel;
	private Label scoreLabel; 
	private double initGold;
	private double initLives;
	private double initLevel;
	private double initScore; 

	public StatsDisplay(double gold, double lives, double level,double score) {
		this.scorePane = new HBox(10);
		this.scorePane.getStyleClass().add("statsHBox");
		this.graphics = new GraphicsLibrary();
		/*
		 * this.goldScore = new Text(); this.liveScore = new Text();
		 * this.levelScore = new Text();
		 */
		this.initGold = gold;
		this.initLives = lives;
		this.initLevel = level;
		this.initScore = score; 
	}

	public HBox getScorePane() {
		return this.scorePane;
	}

	public void init() {
		this.goldLabel = createTextScore(AMOUNT_OF_GOLD, Double.toString(this.initGold), this.goldLabel);
		this.liveLabel = createTextScore(LIVES_LEFT, Double.toString(this.initLives), this.liveLabel);
		this.levelLabel= createTextScore(LEVEL, Double.toString(this.initLevel), this.levelLabel);
		this.scoreLabel = createTextScore(SCORE, Double.toString(this.initScore),this.scoreLabel);
	}

	private Label createTextScore(String title, String score, Label scoreType) {
		Group root = new Group();
		this.scorePane.getChildren().add(root);
		scoreType = new Label(title + score);
		// Label label = new Label(title);
		// this.scorePane.getChildren().add(label);
//		Text label = graphics.createText(root, title);
		scoreType.getStyleClass().add("scoreLabel");
		this.scorePane.getChildren().add(scoreType);
		return scoreType;
	}

	public void updateGold(double gold) {
		this.goldLabel.setText(AMOUNT_OF_GOLD + Double.toString(gold));
	}

	public void updateLives(double lives) {
		
		this.liveLabel.setText(LIVES_LEFT + Double.toString(lives));
	}

	public void updateLevel(double level) {
		this.initLevel=level;
		this.levelLabel.setText(LEVEL + Double.toString(level));
	}
	
	public void updateScore(double score){
		this.scoreLabel.setText(SCORE + Double.toString(score));
	}
	
	public void updateLevelUI(double gold, double lives, double level, double score){
		System.out.println("WHAT IS THE LEVEL HUH? "+level);
		updateGold(gold);
		updateLives(lives);
		updateLevel(level);
		updateScore(score);
	}

}
