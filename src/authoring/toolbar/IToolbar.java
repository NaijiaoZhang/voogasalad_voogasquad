package authoring.toolbar;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


public interface IToolbar {

    public Node getInstanceAsNode ();

    public void setOnPressedSave (EventHandler<MouseEvent> e);

    public void saveFile (String fileContent);

    public void setOnPressedLoad (EventHandler<MouseEvent> e);

    public void setOnPressedPreview (EventHandler<MouseEvent> e);

    public String loadFile ();

}
