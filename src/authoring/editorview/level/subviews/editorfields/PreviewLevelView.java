package authoring.editorview.level.subviews.editorfields;

import authoring.editorview.level.LevelAuthoringViewDelegate;
import authoring.editorview.level.LevelSetView;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * 
 * @author Diane Hadley
 *
 */
public class PreviewLevelView implements LevelSetView {

    private Button previewButton;
    private LevelAuthoringViewDelegate delegate;

    public PreviewLevelView () {
        buildPreviewButton();
    }

    @Override
    public Node getInstanceAsNode () {
        return previewButton;
    }

    private void buildPreviewButton () {
        previewButton = ButtonFactory.makeButton("Preview Level", e -> {
            Stage s = new Stage();
            s.setHeight(600);
            s.setWidth(600);
            s.show();
        });
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;

    }

}
