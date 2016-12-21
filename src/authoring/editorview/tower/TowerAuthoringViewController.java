package authoring.editorview.tower;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.EditorViewController;
import authoring.editorview.ListCellData;
import authoring.editorview.ListDataSource;
import authoring.editorview.NameIdPair;
import authoring.editorview.collisioneffects.EffectAuthoringViewController;
import authoring.utilityfactories.DialogueBoxFactory;
import engine.effect.EffectManagerController;
import engine.tower.TowerManagerController;
import engine.weapon.WeaponManagerController;
import authoring.editorview.tower.TowerUpdateView;


/**
 * 
 * @author Kayla Schulz
 * @author Andrew Bihl
 *
 */
public class TowerAuthoringViewController extends EditorViewController
        implements TowerAuthoringViewDelegate, ListDataSource {

    private TowerManagerController towerDataSource;
    private EffectManagerController effectDataSource;
    private WeaponManagerController weaponDataSource;
    private int currentTowerID;
    private TowerUpdateView towerView;
    private List<NameIdPair> weapons;
    private List<Integer> weaponIDList;
    private List<String> weaponNames;

    public TowerAuthoringViewController (int editorWidth, int editorHeight) {
        towerView = TowerAuthoringViewFactory.build(editorWidth, editorHeight);
        towerView.setDelegate(this);
        towerView.setTowerListDataSource(this);
        this.view = towerView;
    }

    public void setTowerDataSource (TowerManagerController source,
                                    WeaponManagerController weaponSource) {
        this.towerDataSource = source;
        this.weaponDataSource = weaponSource;
        this.towerDataSource.addTypeBankListener(this.towerView);
        effectDataSource = towerDataSource.getEffectManagerController();
        weaponOptions();
        onUserPressedCreateNewTower();
    }

    /**
     * Notification methods coming from delegate
     */
    @Override
    public void onUserPressedCreateNewTower () {
        currentTowerID = towerDataSource.createType(towerView);
        refreshView();
    }

    public void refreshView () {
        towerView.updateImagePathDisplay(towerDataSource.getImagePath(currentTowerID));
        towerView.updateNameDisplay(towerDataSource.getName(currentTowerID));
        towerView.updateSizeDisplay(towerDataSource.getSize(currentTowerID));
        towerView.updateTowerBuyPriceDisplay(towerDataSource.getTowerBuyPrice(currentTowerID));
        towerView.updateTowerSellPriceDisplay(towerDataSource.getTowerSellPrice(currentTowerID));
        towerView.updateUnlockLevelDisplay(towerDataSource.getTowerUnlockLevel(currentTowerID));
        towerView.updateTowerUpgradeBank(towerDataSource.getTowerUpgrades(currentTowerID));
        weaponOptions();
    }

    @Override
    public void onUserEnteredTowerName (String towerName) {
        towerDataSource.setName(currentTowerID, towerName);
    }

    @Override
    public void onUserEnteredTowerImagePath (String towerImagePath) {
        towerDataSource.setImagePath(currentTowerID, towerImagePath);
    }

    @Override
    public void onUserEnteredTowerUnlockLevel (String towerLevel) {
        try {
            Integer.parseInt(towerLevel);
            towerDataSource.setTowerUnlockLevel(currentTowerID, Integer.parseInt(towerLevel));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredTowerBuyPrice (String towerBuyPrice) {
        try {
            Double.parseDouble(towerBuyPrice);
            towerDataSource.setTowerBuyPrice(currentTowerID, Double.parseDouble(towerBuyPrice));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredTowerSellPrice (String towerSellPrice) {
        try {
            Double.parseDouble(towerSellPrice);
            towerDataSource.setTowerSellPrice(currentTowerID, Double.parseDouble(towerSellPrice));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredTowerAbility (String towerAbility) {
        towerDataSource.setTowerChosenAbility(currentTowerID, Integer.parseInt(towerAbility));
    }

    @Override
    public void onUserPressedDeleteTower () {
        int nextID = this.towerView.getNearestAvailableItemID(currentTowerID);
        towerDataSource.deleteType(currentTowerID);
        currentTowerID = nextID;
        this.refreshView();
    }

    @Override
    public void onUserDeletedTowerAbility (String towerAbility) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUserDeletedTowerWeapon (String towerChosenWeapon) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUserDeletedTowerUpgrade (String towerUpgrade) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUserEnteredTowerSize (String towerSize) {
        try {
            Double.parseDouble(towerSize);
            towerDataSource.setSize(currentTowerID, Double.parseDouble(towerSize));
        }
        catch (NumberFormatException e) {
            createDialogueBox();
        }
    }

    @Override
    public void onUserEnteredTowerChosenWeapon (String towerChosenWeapon) {
        int id = 0;
        for (int i = 0; i < weaponNames.size(); i++) {
            if (weaponNames.get(i).equals(towerChosenWeapon)) {
                id = i;
                break;

            }
        }
        towerDataSource.setTowerChosenWeapon(currentTowerID, id);
    }

    @Override
    public void onUserEnteredTowerUpgrade (String towerUpgrade) {
        // TODO Auto-generated method stub

    }

    private void createDialogueBox () {
        ResourceBundle dialogueBoxResource = ResourceBundle.getBundle("resources/DialogueBox");
        DialogueBoxFactory.createErrorDialogueBox(dialogueBoxResource.getString("Integer"),
                                                  dialogueBoxResource.getString("CheckInput"));
    }

    @Override
    public void onUserSelectedTower (int towerID) {
        currentTowerID = towerID;
        refreshView();
    }

    @Override
    public ListCellData getCellDataForSubject (int id) {
        ListCellData cellData = new ListCellData();
        cellData.setName(towerDataSource.getName(id));
        cellData.setImagePath(towerDataSource.getImagePath(id));
        cellData.setId(id);
        return cellData;
    }

    @Override
    public void onUserSelectedTowerUpgrade (int towerID) {
        // TODO Show the tower so it can be edited.

    }

    @Override
    public void onUserPressedCreateTowerUpgrade () {
        towerDataSource.createTowerUpgrade(this.towerView, this.currentTowerID);
    }

    @Override
    public void onUserPressedAddEffect () {
        EffectAuthoringViewController effectAuthoringView =
                new EffectAuthoringViewController(effectDataSource);
        effectDataSource.createType(effectAuthoringView.getEffectAuthoringView());
        effectAuthoringView.setEffectOptions(effectDataSource.getCreatedTypeIds());
        effectAuthoringView.setAvailClasses(effectDataSource.getAvailableClasses());
        effectAuthoringView.setAvailDataObjects(effectDataSource.getAvailableDataObjects());
        effectAuthoringView.openEffectView();
    }

    private void weaponOptions () {
        weapons = new ArrayList<>();
        weaponIDList = new ArrayList<>();
        weaponNames = new ArrayList<>();
        List<Integer> weaponIDs = weaponDataSource.getCreatedTypeIds();
        for (int curID : weaponIDs) {
            String weaponName = weaponDataSource.getName(curID);
            NameIdPair temp = new NameIdPair(weaponName, curID);
            weaponIDList.add(curID);
            weapons.add(temp);
            weaponNames.add(weaponName);
        }
        towerView.setWeaponOptions(weaponNames);
    }

}
