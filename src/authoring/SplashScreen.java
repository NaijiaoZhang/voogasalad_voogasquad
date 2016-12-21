package authoring;

import javafx.scene.Group;
import javafx.scene.Scene;


public class SplashScreen {

    private Scene scene;
    private Group root;

    public SplashScreen () {
        root = new Group();
        scene = new Scene(root);
    }

    public Scene getScene () {
        return scene;
    }

}
