package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.io.xml.DomDriver;
import engine.ability.AbilityManagerController;
import engine.ability.AbilityTypeManagerController;
import engine.enemy.Enemy;
import engine.enemy.EnemyManager;
import engine.enemy.EnemyManagerController;
import engine.enemy.EnemyTypeManagerController;
import engine.level.LevelManagerController;
import engine.level.LevelTypeManagerController;
import engine.path.PathManagerController;
import engine.path.PathTypeManagerController;
import engine.settings.GameModeManagerController;
import engine.settings.GameModeTypeBuilder;
import engine.settings.GameModeTypeManagerController;
import engine.tower.Tower;
import engine.tower.TowerManager;
import engine.tower.TowerManagerController;
import engine.tower.TowerTypeManagerController;
import engine.weapon.WeaponManagerController;
import engine.weapon.WeaponTypeManagerController;

public class ModelAuthoringController implements ModelController {
    private Map<Class<?>, ManagerController<?, ?, ?, ?>> modelControllers;
    private GameData gameData;
    private XStream Serializer = new XStream(new DomDriver());
    
    public ModelAuthoringController() {
        modelControllers = new HashMap<Class<?>, ManagerController<?, ?, ?, ?>>();
        ManagerMediator managerMediator = new ManagerTypeMediator();
        gameData = new GameAuthoringData(managerMediator);
        initializeControllers(managerMediator);
    }
    
    //TODO - error checking
    @Override
    public <R> R getModelController(Class<R> key) {
        return key.cast(modelControllers.get(key));
    }
    
    @Override
    public String SaveData () {
        //System.out.print(Serializer.toXML(gameData)); //Test XML
        return Serializer.toXML(gameData);
    }
    
    private void initializeControllers(ManagerMediator managerMediator) {
        modelControllers.put(GameModeManagerController.class, new GameModeTypeManagerController(managerMediator));
        modelControllers.put(TowerManagerController.class, new TowerTypeManagerController(managerMediator));
        modelControllers.put(WeaponManagerController.class, new WeaponTypeManagerController(managerMediator));
        modelControllers.put(AbilityManagerController.class, new AbilityTypeManagerController(managerMediator));
        modelControllers.put(PathManagerController.class, new PathTypeManagerController(managerMediator));
        modelControllers.put(EnemyManagerController.class, new EnemyTypeManagerController(managerMediator));
        modelControllers.put(LevelManagerController.class, new LevelTypeManagerController(managerMediator));
    }

	@Override
	//TODO Catch it for real
	public GameAuthoringData loadData(String filePath) throws FileNotFoundException {
			File xmlFile = new File(filePath);
			GameAuthoringData data = (GameAuthoringData) Serializer.fromXML(new FileInputStream(xmlFile));
			return data;
	}

	@Override
	public GameData getGameData() {
		return gameData;
	}
    
//    public void testXML() {
//        String xml = Serializer.toXML(gameData);
//        GameData test = (GameData)Serializer.fromXML(xml);
//        Map<Integer, Tower>  data = test.getManagerMediator().getManager(TowerManager.class).getEntities();
//    }
    
//    public static void main (String[] args) {
//        ModelAuthoringController test = new ModelAuthoringController();
//        test.testXML();
//        
//    }
}
