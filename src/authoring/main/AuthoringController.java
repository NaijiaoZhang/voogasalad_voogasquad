package authoring.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import authoring.editorview.EditorViewController;
import authoring.editorview.IUpdateView;
import authoring.editorview.enemy.EnemyAuthoringViewController;
import authoring.editorview.enemy.EnemyUpdateView;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewController;
import authoring.editorview.gamesettings.GameSettingsUpdateView;
import authoring.editorview.level.LevelUpdateView;
import authoring.editorview.level.LevelAuthoringViewController;
import authoring.editorview.path.PathUpdateView;
import authoring.editorview.path.PathAuthoringViewController;
import authoring.editorview.tower.TowerUpdateView;
import authoring.editorview.tower.TowerAuthoringViewController;
import authoring.editorview.weapon.WeaponUpdateView;
import authoring.editorview.weapon.WeaponAuthoringViewController;
import authoring.toolbar.IToolbar;
import authoring.utilityfactories.DialogueBoxFactory;
import authoring.view.ViewController;
import engine.GameAuthoringData;
import engine.ModelAuthoringController;
import engine.ModelController;
import engine.enemy.EnemyManager;
import engine.enemy.EnemyManagerController;
import engine.level.LevelManager;
import engine.level.LevelManagerController;
import engine.path.PathManager;
import engine.path.PathManagerController;
import engine.settings.GameModeManager;
import engine.settings.GameModeManagerController;
import engine.tower.TowerManager;
import engine.tower.TowerManagerController;
import engine.weapon.WeaponManager;
import engine.weapon.WeaponManagerController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import statswrapper.Wrapper;


/**
 * 
 * @author Andrew Bihl
 * @author Diane Hadley
 *
 */
public class AuthoringController {
    private ModelController modelController;
    private ViewController viewController;
    private IToolbar toolbar;

    public AuthoringController (int size) throws IOException {
        modelController = new ModelAuthoringController();
        try {
            viewController = new ViewController(size, size);
            connectDataInterfaces(viewController);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
        	throw new IOException();
        }
        configureToolbar();

    }

    private void configureToolbar () {
        toolbar = this.viewController.getView().getMyToolbar();

        toolbar.setOnPressedSave(e -> {
			try {
				saveAsXMLFile();
			} catch (IOException e1) {
				DialogueBoxFactory.createErrorDialogueBox("Couldn't Save Game", "IOExcpetion");

			}
		});

        toolbar.setOnPressedLoad(e -> {
            loadData();// "player.samplexml/load.xml"

            toolbar.setOnPressedPreview(a -> choosePreviewLevel());
        });

    }

    private void choosePreviewLevel () {
        List<Integer> possiblePaths =
                modelController.getModelController(GameModeManager.class).getEntity(0).getPaths();
        // TODO Select path options and on click call method below
        // createPreview(modelController.getGameData());
    }

    public void saveAsXMLFile () throws IOException {
        String fileContent = this.modelController.SaveData();
        toolbar.saveFile(fileContent);
        // TODO Lucy: add api call to record game in web app

        try {
            String gameData = xmlToString(fileContent);
            Wrapper.getInstance().createGame(gameData);
        }
        catch (IOException e) {
        	throw new IOException();
        }
    }

    public void loadData () {
        // TODO GameModeManagerController ConstructTypeProperties is empty because it needs methods
        // to call in front end.
        String filePath = toolbar.loadFile();

        try {
            GameAuthoringData data = modelController.loadData(filePath);

            modelController.getModelController(EnemyManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(EnemyManager.class),
                                     (EnemyUpdateView) getUpdateView("enemy"));
            modelController.getModelController(TowerManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(TowerManager.class),
                                     (TowerUpdateView) getUpdateView("tower"));
            modelController.getModelController(WeaponManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(WeaponManager.class),
                                     (WeaponUpdateView) getUpdateView("weapon"));
            modelController.getModelController(PathManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(PathManager.class),
                                     (PathUpdateView) getUpdateView("path"));
            modelController.getModelController(LevelManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(LevelManager.class),
                                     (LevelUpdateView) getUpdateView("level"));
            modelController.getModelController(GameModeManagerController.class)
                    .loadManagerData(data.getManagerMediator().getManager(GameModeManager.class),
                                     (GameSettingsUpdateView) getUpdateView("setup"));
            refreshViews();

        }
        catch (Exception e) {
            //System.out.print(e);
            Alert errorDialogueBox = DialogueBoxFactory.createErrorDialogueBox("Error With File",
                                                                               "This file could not be loaded.");
        }
    }

    private void refreshViews () {
        for (EditorViewController view : viewController.getControllers().values()) {
            view.refreshView();
        }
    }

    private IUpdateView getUpdateView (String key) {
        return viewController.getController(key).getUpdateView();
    }

    private String xmlToString (String textContent) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(textContent));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        return sb.toString();
    }

    private void connectDataInterfaces (ViewController authoringVC) {

        HashMap<String, EditorViewController> editorVCs = authoringVC.getControllers();
        PathAuthoringViewController pathVC = (PathAuthoringViewController) editorVCs.get("path");
        LevelAuthoringViewController levelVC =
                (LevelAuthoringViewController) editorVCs.get("level");
        WeaponAuthoringViewController weaponVC =
                (WeaponAuthoringViewController) editorVCs.get("weapon");
        EnemyAuthoringViewController enemyVC =
                (EnemyAuthoringViewController) editorVCs.get("enemy");
        TowerAuthoringViewController towerVC =
                (TowerAuthoringViewController) editorVCs.get("tower");
        GameSettingsAuthoringViewController setupVC =
                (GameSettingsAuthoringViewController) editorVCs.get("setup");

        PathManagerController pathModel =
                modelController.getModelController(PathManagerController.class);
        LevelManagerController levelModel =
                modelController.getModelController(LevelManagerController.class);
        WeaponManagerController weaponModel =
                modelController.getModelController(WeaponManagerController.class);
        EnemyManagerController enemyModel =
                modelController.getModelController(EnemyManagerController.class);
        TowerManagerController towerModel =
                modelController.getModelController(TowerManagerController.class);
        GameModeManagerController settingsModel =
                modelController.getModelController(GameModeManagerController.class);

        pathVC.setPathDataSource(pathModel);
        enemyVC.setEnemyDataSource(enemyModel);
        levelVC.setLevelDataSource(levelModel, enemyModel);
        weaponVC.setWeaponDataSource(weaponModel);
        towerVC.setTowerDataSource(towerModel, weaponModel);
        setupVC.setGameSettingsDataSource(settingsModel, pathModel, levelModel);
    }

    public Scene getScene () {
        return this.viewController.getView().getScene();
    }
}
