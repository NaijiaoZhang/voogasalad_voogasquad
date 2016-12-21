package gameplayer.loader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.enemy.EnemyManager;
import engine.enemy.EnemyType;
import engine.enemy.EnemyTypeBuilder;
import engine.enemy.EnemyTypeManager;
import engine.level.LevelManager;
import engine.level.LevelTypeManager;
import engine.path.PathManager;
import engine.path.PathTypeManager;
import engine.settings.GameMode;
import engine.settings.GameModeManager;
import engine.settings.GameModeType;
import engine.settings.GameModeTypeManager;
import engine.tower.Tower;
import engine.tower.TowerManager;
import engine.tower.TowerType;
import engine.tower.TowerTypeBuilder;
import gameplayer.model.enemy.Enemy;
import engine.enemy.EnemyTypeBuilder;
import engine.tower.TowerTypeManager;
import engine.weapon.Weapon;
import engine.weapon.WeaponManager;
import engine.weapon.WeaponTypeManager;
import engine.GameAuthoringData;
import engine.ManagerMediator;
import engine.ManagerTypeMediator;
import engine.effect.EffectManager;
/**
 * This class is an xml parser that is able to read xml files and grab the appropriate values from them. 
 * @author Aaron, Naijiao
 *
 */
public class XMLParser {
	
	private Element rootElement;
	private XStream serializer;
	private ManagerMediator gameManager;
	public XMLParser(String xmlFilename) {
		serializer = new XStream(new DomDriver());
		gameManager = getGameManager(xmlFilename);
	}
	
	public XMLParser(ManagerMediator manager) {
		gameManager = manager;
	}
	
	private ManagerMediator getGameManager(String xmlFilename) {
		try {
			File xmlFile = new File(xmlFilename);
			//System.out.println("The file exists: "+xmlFile);
			GameAuthoringData data = (GameAuthoringData) serializer.fromXML(new FileInputStream(xmlFile));
			return data.getManagerMediator();
			
		} catch (FileNotFoundException e) {
			//TODO: implement real error handling
			//System.out.println("File not found, please try again");
		} 
		return null;
	}
    
    
    public Map<Integer,Tower> getTowerTypes(){
    	TowerManager towerManager = gameManager.getManager(TowerManager.class);
    	Map<Integer, Tower> towerTypes = towerManager.getEntities();
    	return towerTypes;
    }
    
    public Map<Integer,Tower> getTowerUpgrades() {
    	TowerManager towerManager = gameManager.getManager(TowerManager.class);
    	return towerManager.getUpgrades();
    }
    
    
    public Map<Integer, Weapon> getWeaponTypes() {
    	WeaponManager weaponManager = gameManager.getManager(WeaponManager.class);
    	Map<Integer, Weapon> weaponTypes = weaponManager.getEntities();
    	return weaponTypes;
    }
    
	protected Map<Integer, engine.enemy.Enemy> getEnemyTypes() { //refactor names
		EnemyManager enemyManager = gameManager.getManager(EnemyManager.class);
		Map<Integer, engine.enemy.Enemy> enemyTypes = enemyManager.getEntities();
		return enemyTypes;
	}
	
	protected GameMode getGameMode() {
		GameModeManager gameModeManager = gameManager.getManager(GameModeManager.class);
		return gameModeManager.getEntity(0);
	}
	
	protected LevelManager getLevelManager() {
		return gameManager.getManager(LevelManager.class);
	}
	
	protected PathManager getPathManager() {
		return gameManager.getManager(PathManager.class);
	}
	
	protected EffectManager getWeaponEffectManager() {
    	WeaponManager weaponManager = gameManager.getManager(WeaponManager.class);
    	return weaponManager.getWeaponEffectManager();
	}
	
	protected EffectManager getGameModeEffectManager() {
		GameModeManager gameModeManager = gameManager.getManager(GameModeManager.class);
		return gameModeManager.getGameModeEffectManager();
	}
	
	
	
	
	//TODO: get rid of this method
    public boolean isValid() {
    	return true;
    	
    }
    
    //This returns the TYPE of the xml
    public String getName(){
    	GameMode gameMode = getGameMode();
    	//change when xml file has gameMode
    	return "TempGame"; //type or name?
    }
    
	
}