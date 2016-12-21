package authoring.editorview.level.subviews.editorfields;

import java.util.ResourceBundle;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.editorview.level.LevelSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class CreateNewWaveView implements LevelSetView {

    private static final String RESOURCE_FILE_NAME = "resources/GameAuthoringLevels";
    private ResourceBundle levelResource = ResourceBundle.getBundle(RESOURCE_FILE_NAME);

    private LevelAuthoringViewDelegate delegate;
    private Button createWaveButton;

    public CreateNewWaveView (ResourceBundle levelsResource) {
        buildViewComponents();
    }

    @Override
    public Node getInstanceAsNode () {
        return createWaveButton;
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void buildViewComponents () {

        createWaveButton =
                ButtonFactory.makeButton(levelResource.getString("NewWave"),
                                         e -> {
                                             createNewWave();
                                         });
        createWaveButton.setTranslateY(5);
        createWaveButton.setTranslateX(5);
        createWaveButton.setFocusTraversable(false);
    }

    public void createNewWave () {
        delegate.onUserEnteredAddWave();
    }

}
