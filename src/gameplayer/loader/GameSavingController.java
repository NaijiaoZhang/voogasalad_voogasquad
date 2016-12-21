package gameplayer.loader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.utilityfactories.DialogueBoxFactory;
import gameplayer.model.GamePlayData;
import gameplayer.model.GamePlayModel;
import gameplayer.model.Grid;
import gameplayer.model.enemy.Enemy;
import gameplayer.model.enemy.EnemyManager;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

public class GameSavingController {
	//private XStream serializer;
	private GamePlayModel gameModel;
	String xmlName;
	
	public GameSavingController(GamePlayModel model, String xmlFilename) {
		//serializer = new XStream(new DomDriver());
		this.gameModel = model;
		this.xmlName = xmlFilename;
	}
	
	public String toPrettyXML() {
		GamePlayData gameData = gameModel.getData();
		SavedSettings settings = new SavedSettings(this.xmlName);
		settings.setGold(gameData.getGold());
		settings.setLevel(gameData.getCurrentLevel());
		settings.setLives(gameData.getLife());
		settings.setScore(gameData.getScore());
		return new XStream(new DomDriver()).toXML(settings);
	}
	
	public void saveGame() {
		
		String fileName = getUserInput();
		String content = this.toPrettyXML();
		FileWriter fw;
		try {
			fw = new FileWriter("SavedGames/"+fileName+".xml");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			System.out.println("Game does not exist, please choose another");
		}
	}
	
	private String getUserInput() {
		TextInputDialog prompt = new TextInputDialog("Game Title");
		prompt.setTitle("Save Game");
		prompt.setHeaderText("Please enter the name of the game to save");
		
		Optional<String> input = prompt.showAndWait();
		String gameTitle = "newGame";
		if (input.isPresent()) {
			gameTitle = input.get();
		}
		return gameTitle;
	}
}
