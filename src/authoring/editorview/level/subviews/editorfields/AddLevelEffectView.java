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
public class AddLevelEffectView implements LevelSetView {

    private LevelAuthoringViewDelegate delegate;
    private Button addLevelEffectButton;

    public AddLevelEffectView (ResourceBundle labelsResource) {
        createButton(labelsResource);
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Node getInstanceAsNode () {
        return addLevelEffectButton;
    }

    private void createButton (ResourceBundle labelsResource) {
        addLevelEffectButton =
                ButtonFactory.makeButton(labelsResource.getString("AddEffect"),
                                         e -> delegate.onUserPressedAddEffect());
    }

}
