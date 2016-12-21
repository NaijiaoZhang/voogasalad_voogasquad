package authoring.editorview.path.subviews;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.ImageBank;
import authoring.editorview.path.PathAuthoringViewDelegate;
import authoring.utilityfactories.ButtonFactory;
import javafx.scene.control.Button;


public class PathBank extends ImageBank {

    private PathAuthoringViewDelegate delegate;

    public PathBank (ResourceBundle pathResource) {
        super();
        Button createPathButton =
                ButtonFactory.makeButton(pathResource.getString("NewPathButton"),
                                         e -> {
                                             delegate.onUserEnteredCreatePath();
                                         });
        createPathButton.setPrefWidth(130);
        items.add(createPathButton);
        this.CONTENT_OFFSET = 1;

    }

    public void setDelegate (PathAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void updatePathBank (List<Integer> createdPaths) {
        super.updateBank(createdPaths);

    }

    @Override
    protected void userSelectedRow (int index) {
        int selectedPathID = this.itemIDs.get(index);
        if (selectedPathID != -1) {
            this.delegate.onUserEnteredEditPath(selectedPathID);
        }

    }

}
