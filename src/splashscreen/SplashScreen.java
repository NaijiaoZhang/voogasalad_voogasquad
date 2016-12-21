package splashscreen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.main.AuthoringController;
import authoring.utilityfactories.DialogueBoxFactory;
import engine.GameAuthoringData;
import gameplayer.controller.GamePlayerController;
import gameplayer.controller.HomeSelection;
import gameplayer.loader.SavedSettings;
import gameplayer.loader.XMLParser;
import gameplayer.view.helper.GraphicsLibrary;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import statswrapper.Wrapper;

public class SplashScreen {
	private GraphicsLibrary graphics = new GraphicsLibrary();
	public static final String TITLE = "VOOGASquad";
	private static final int SIZE = 700;
	private Stage stage;
	private Scene scene;
	private GridPane mainScreen;

	public SplashScreen(Stage s) {
		this.stage = s;
		this.mainScreen = new GridPane();
		this.mainScreen.getStyleClass().add("splash");
		this.mainScreen.setStyle("-fx-background: #34495e;");

	}

	public void init() {

		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("http://people.duke.edu/~lz107/voogaTemplates/splashscreen.html");
		this.mainScreen.add(browser, 0, 0);
		this.mainScreen.setMinWidth(SIZE);
		this.mainScreen.setMinHeight(SIZE);
		this.scene = new Scene(this.mainScreen);
		this.scene.getStylesheets().add(this.getClass().getResource("splash.css").toExternalForm());
		this.stage.setScene(this.scene);
		addSignInOption();
	}

	private void addSignInOption() {
		//Text scenetitle = new Text("Welcome");
		//scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		//mainScreen.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		mainScreen.add(userName, 0, 1);

		TextField userTextField = new TextField();
		mainScreen.add(userTextField, 0, 2);

		Label pw = new Label("Password:");
		mainScreen.add(pw, 0, 3);

		PasswordField pwBox = new PasswordField();
		mainScreen.add(pwBox, 0, 4);

		//System.out.println("textfield input: "+userTextField.getText()+","+ pwBox.getText());
		addLoginButtons(userTextField, pwBox);
		addCreateAccountButtons(userTextField, pwBox);
	}

	private void addLoginButtons(TextField userfield, TextField passField) {
		Button btn = new Button("Sign in");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		mainScreen.add(hbBtn, 1, 2); //column, row
	
		final Text actiontarget = new Text();
		mainScreen.add(actiontarget, 0, 5);

		btn.setOnAction(e -> {
			
			try {
				String response = Wrapper.getInstance().login(userfield.getText(), passField.getText());
				actiontarget.setText(response);
				//testing
				//Wrapper.getInstance().updateGameScores("gold", "0", "777");
				createPlayGameOrMakeGameOptions();
				
				//TESTING
				//Wrapper.getInstance().recordGameScores("50", "3", "0");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				createPlayGameOrMakeGameOptions();
				DialogueBoxFactory.createErrorDialogueBox("Couldn't load authoring environment", "IOExcpetion");
			}
		});
	}
	
	private void createPlayGameOrMakeGameOptions(){
		Button btn1 = graphics.createButton("Make a game", e ->{
			try {
				startAuthoringEnv();
			} catch (IOException e1) {
				DialogueBoxFactory.createErrorDialogueBox("Couldn't load authoring environment", "IOExcpetion");
			}
		});
		Button btn2 = graphics.createButton("Play a game", e -> {
			startGame();
		});
		Button btn3 = graphics.createButton("Load a game", e ->{
			FileChooser filechooser = new FileChooser();
	        Stage mainStage = (Stage) ((Node) e.getSource()).getScene().getWindow();

	        
			File out = filechooser.showOpenDialog(mainStage);
			String xmlName;
			SavedSettings settings;
			try {
				XStream deserializer = new XStream(new DomDriver());
				settings = (SavedSettings) deserializer.fromXML(new FileInputStream(out));
				xmlName = settings.getGameType();

				
			} catch (Exception e1) {
				DialogueBoxFactory.createErrorDialogueBox("Couldn't load a game", "Exception");
				return;
			}
			GamePlayerController gameController = new GamePlayerController(xmlName, settings);
			gameController.init(false);
			
			Stage stage = new Stage();
			gameController.setDataStoreOnClose(stage);
			stage.setTitle(TITLE);
			stage.setScene(gameController.getMainScene());
			stage.show();
		
			
		});
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().addAll(btn1, btn2,btn3);
		mainScreen.add(hbBtn, 0, 5);
	}

	private void addCreateAccountButtons(TextField userfield, TextField passField) {
		Button btn = new Button("Create account");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		mainScreen.add(hbBtn, 1, 4);
		final Text actiontarget = new Text();
		mainScreen.add(actiontarget, 0, 5);

		btn.setOnAction(e -> {
			try {
				String response = Wrapper.getInstance().createAccount(userfield.getText(), passField.getText());
				actiontarget.setText(response+ "/n Now login");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

	private void startAuthoringEnv() throws IOException {
		Stage s = new Stage();
		AuthoringController generalController = new AuthoringController(SIZE);

		Scene scene = generalController.getScene();
		s.setTitle(TITLE);
		s.setScene(scene);
		s.setResizable(false);
		s.setHeight(SIZE);
		s.setWidth(SIZE + 395);
		s.show();

	}

	private void startGame(){
		Stage s = new Stage();
		
		XMLGallery fileGallery = new XMLGallery();
		s.setScene(fileGallery.getScene());
		s.show();
		
	}

}
