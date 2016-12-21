package authoring.editorview.gamesettings.subviews.editorfields;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.controlsfx.control.CheckComboBox;
import authoring.editorview.NameIdPair;
import authoring.editorview.gamesettings.GameSettingsAuthoringViewDelegate;
import authoring.editorview.gamesettings.GameSettingsSetView;
import authoring.utilityfactories.ComboBoxFactory;
import authoring.utilityfactories.GridFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class GamePathView implements GameSettingsSetView {

    private GridPane root;
    private GameSettingsAuthoringViewDelegate delegate;
    private CheckComboBox<String> pathCheckComboBox;
    private ObservableList<String> availablePathList;
    private List<NameIdPair> pathNameIdList;
    private List<Integer> pathIdList;
    private List<String> pathNameList;

    public GamePathView (ResourceBundle settingsResource) {
        root = new GridPane();
        pathNameIdList = new ArrayList<NameIdPair>();
        pathIdList = new ArrayList<Integer>();
        pathNameList = new ArrayList<String>();
        buildView(settingsResource);
    }

    private void buildView (ResourceBundle settingsResource) {
        availablePathList = FXCollections.observableArrayList();

        pathCheckComboBox =
                ComboBoxFactory.makeCheckComboBox(availablePathList, a -> updatePaths(a));
        pathCheckComboBox.setPrefWidth(105);
        root =
                GridFactory.createRowWithLabelandNode(settingsResource.getString("Path"),
                                                      pathCheckComboBox, 125);
    }

    @Override
    public Node getInstanceAsNode () {
        return root;
    }

    @Override
    public void setDelegate (GameSettingsAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void updatePaths (List<String> pathNames) {
    	for (NameIdPair pair : pathNameIdList) {
            if (pathNames.contains(pair.getName()) && !pathIdList.contains(pair.getId())) {
                pathNameList.add(pair.getName());
                pathIdList.add(pair.getId());
                delegate.onUserEnteredPath(pair.getId());
            }
            else if (!pathNames.contains(pair.getName()) && pathNameList.contains(pair.getName())) {
            	pathNameList.remove(pair.getName());
                pathIdList.remove((Object) pair.getId());
                delegate.onUserEnteredRemovePath(pair.getId());

            }
        }
    }
    
    public void clearPathList(){
    	pathIdList.clear();
        pathNameList.clear();
    }

    public void setPathList (List<Integer> pathList) {
    	pathIdList.clear();
        pathNameList.clear();
        
        this.pathIdList = new ArrayList<Integer>(pathList);
        pathCheckComboBox.getCheckModel().clearChecks();
        for (Integer id : pathList) {
            for (NameIdPair pair : pathNameIdList) {
                if (pair.getId() == id) {
                	pathNameList.add(pair.getName());
                	if (!pathCheckComboBox.getCheckModel().isChecked(pair.getName())){
                		pathCheckComboBox.getCheckModel().check(pair.getName());
                	}                 
                }
            }
        }
    }

    public void setAvailablePathList (List<Integer> availablePathList) {
    	pathNameIdList.clear();
        this.availablePathList.clear();
        for (Integer id : availablePathList) {
            NameIdPair pair = new NameIdPair(delegate.getPathName(id), id);
            this.pathNameIdList.add(pair);
            this.availablePathList.add(delegate.getPathName(id));
        }
    }
}
