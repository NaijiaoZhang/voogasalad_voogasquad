package authoring.editorview.collisioneffects;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ListDataSource;
import authoring.editorview.collisioneffects.subviews.EffectAvailableClassesView;
import authoring.editorview.collisioneffects.subviews.EffectAvailableDataObjectsView;
import authoring.editorview.collisioneffects.subviews.EffectAvailableMethodsView;
import authoring.editorview.collisioneffects.subviews.EffectBank;
import authoring.editorview.collisioneffects.subviews.EffectDataView;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectConditionField;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectField;
import authoring.editorview.collisioneffects.subviews.editorfields.EffectNameField;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class EffectAuthoringView implements EffectUpdateView {

    private GridPane effectsPane;
    private EffectBank effectBank;
    private EffectAvailableClassesView effectAvailClasses;
    private EffectAvailableDataObjectsView effectDataObjects;
    private EffectAvailableMethodsView effectAvailMethods;
    private EffectDataView effectDataView;
    private EffectNameField nameField;
    private EffectConditionField conditionField;
    private EffectField effectField;
    private Scene myScene;
    private ResourceBundle labelsResource;

    public EffectAuthoringView () {
        labelsResource = ResourceBundle.getBundle("resources/GameAuthoringEffect");
        effectBank = new EffectBank();
        effectsPane = new GridPane();
        effectAvailClasses = new EffectAvailableClassesView();
        effectDataObjects = new EffectAvailableDataObjectsView();
        effectAvailMethods = new EffectAvailableMethodsView();
        nameField = new EffectNameField(labelsResource);
        conditionField = new EffectConditionField(labelsResource);
        effectField = new EffectField(labelsResource);
        effectDataView = new EffectDataView(nameField, conditionField, effectField);
        setPane();
        myScene = new Scene(effectsPane);
    }

    private void setPane () {
        effectsPane.add(effectBank.getInstanceAsNode(), 0, 0, 1, 1);
        effectsPane.add(effectAvailClasses.getInstanceAsNode(), 1, 0, 1, 1);
        effectsPane.add(effectAvailMethods.getInstanceAsNode(), 2, 0, 1, 1);
        effectsPane.add(effectDataObjects.getInstanceAsNode(), 3, 0, 1, 1);
        effectsPane.add(effectDataView.getInstanceAsNode(), 0, 1, GridPane.REMAINING, 1);
    }

    public Scene getScene () {
        return myScene;
    }

    public void openEffectView () {
        Stage stage = new Stage();
        Scene myScene = getScene();
        stage.setScene(myScene);
        stage.show();
    }

    @Override
    public void updateEffectName (String name) {
        nameField.updateName(name);
    }

    @Override
    public void createNewEffect () {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteEffect () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setEffectListDataSource (ListDataSource source) {
        this.effectBank.setListDataSource(source);
        this.effectAvailClasses.setListDataSource(source);
        this.effectAvailMethods.setListDataSource(source);
        this.effectDataObjects.setListDataSource(source);
    }

    @Override
    public EffectAvailableMethodsView getEffectAvailMethods () {
        return effectAvailMethods;
    }

    @Override
    public void updateConditionField (String condition) {
        conditionField.updateField(condition);
    }

    @Override
    public void updateEffectField (String effect) {
        effectField.updateField(effect);
    }

    @Override
    public void updateEffectBank (List<Integer> effects) {
        effectBank.updateBank(effects);
    }

    @Override
    public void updateListedAvailableClasses (List<String> availClasses) {
        effectAvailClasses.updateAvailClasses(availClasses);
    }

    @Override
    public void updateAvailableMethods (List<String> availMethods) {
        effectAvailMethods.updateAvailMethods(availMethods);
    }

    @Override
    public void updateAvailableDataObjects (List<String> availDataObjects) {
        effectDataObjects.updateAvailDataObjects(availDataObjects);
    }

    @Override
    public void setDelegate (EffectAuthoringViewDelegate delegate) {
        effectBank.setDelegate(delegate);
        effectDataView.setDelegate(delegate);
        effectAvailMethods.setDelegate(delegate);
        effectAvailClasses.setDelegate(delegate);
        effectDataObjects.setDelegate(delegate);
    }

    @Override
    public Node getInstanceAsNode () {
        return effectsPane;
    }

    @Override
    public void updateNameDisplay (String name) {
        nameField.updateName(name);
    }

    @Override
    public void updateImagePathDisplay (String imagePath) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSizeDisplay (double size) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateBank (List<Integer> ids) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateDeleteEntity (String entityID) {
        // TODO Auto-generated method stub

    }

    @Override
    public Integer getNearestAvailableItemID (int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateTriggers (List<String> triggers) {
        // TODO Auto-generated method stub

    }

}
