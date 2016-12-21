package gameplayer.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import splashscreen.SplashScreen;

import java.io.File;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * This class takes care of opening a computer's file chooser on the initial run of the program, and allows the user to
 * choose an xml file for the user to read to create a slogoView based on the file settins
 * @author lucyzhang
 *
 */
public class HomeSelection {
	private final File INITIAL_DIRECTORY = new File("player.samplexml");
	private final File IMAGE_DIRECTORY = new File("Images");
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	private String fileName;
	private Stage s;
	private ResourceBundle myResources;

	public HomeSelection(Stage s) {
		this.s = s;
	}
	
	public HomeSelection() {
		
	}

	/**
	 * Creates the file directory to choose an xml simulation file to run
	 */
	public void initHomeScreen() {
		createFileDirectory(INITIAL_DIRECTORY);
	}


	/**
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	private void createFileDirectory(File directory) {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(directory);
		chooser.setTitle("Choose File");
		File temp = chooser.showOpenDialog(new Stage());
		if (temp != null) {
			fileName = temp.toString();
//			System.out.println("File name: "+fileName);
			GamePlayerController playerController = new GamePlayerController(fileName);
			//playerController.init(false);
			s.setTitle(SplashScreen.TITLE);
			s.setScene(playerController.getMainScene());
			s.show();
		}
	}

	//TODO: create game from xml (no game class?)
	/*
	private GameData createGameFromXML(String fileName) {
		DataSetup data = new DataSetup(fileName);
		HashMap<String, String> generalInfo = data.getGeneralInfo();
		slogoView = new SlogoView(generalInfo.get("title"), generalInfo.get("background_color").toUpperCase(),
				generalInfo.get("language"), Integer.parseInt(generalInfo.get("numTurtles")));
		Scene scene = slogoView.init();
		scene.getStylesheets().add(this.getClass().getResource("/view/voogaStyle.css").toExternalForm());
		s.setTitle(Main.TITLE);
		s.setScene(scene);
		s.show();
		return null;
	}
	*/
}
