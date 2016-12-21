package gameplayer.view.buttonPanel;

import gameplayer.view.helper.GraphicsLibrary;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import statswrapper.Wrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class GamePlayButtonPanel{
	
	private EventHandler<ActionEvent> startOnPress; 
	private EventHandler<ActionEvent> pauseOnPress;
	private EventHandler<ActionEvent> saveOnPress;
	private ButtonPanel panel;
	private GraphicsLibrary graphicsLib;
	
	
	public GamePlayButtonPanel(){
		this.panel = new ButtonPanel();
		this.graphicsLib = new GraphicsLibrary();
	}
	
	public HBox getPane(){
		return this.panel.getButtonPanel();
	}
	
	public void init(){
		panel.init(createButtons());
	}
	
	public void bindAnimationStart(EventHandler<ActionEvent> handle){	
		this.startOnPress = handle; 
	}
	
	public void bindAnimationStop(EventHandler<ActionEvent> handle){
		this.pauseOnPress = handle;
	}
	
	public void bindSaveGame(EventHandler<ActionEvent> handle){
		this.saveOnPress = handle;
	}
	
	private Button[] createButtons(){
		Button[] buttonArr = {createPlayButton(), createPauseButton(), viewStats(), saveButton()};
		return buttonArr;
	}
	
	private Button createPlayButton(){
		Button play = graphicsLib.createButton("Play", startOnPress);
		return play;
	}
	
	private Button createPauseButton(){
		Button pause = graphicsLib.createButton("Pause", pauseOnPress);
		return pause;
	}
	
	private Button saveButton(){
		Button save = graphicsLib.createButton("Save", saveOnPress);
		return save;
	}
	
	private Button viewStats(){
		Button viewStats = graphicsLib.createButton("View stats", e -> {
			WebView browser = new WebView();
			WebEngine webEngine = browser.getEngine();
			webEngine.load("http://voogasquad.herokuapp.com/home");
			Scene s = new Scene(browser);
			Stage stage = new Stage();
			stage.setScene(s);
			stage.show();
			
		});
		return viewStats;
	}
	

	

}
