package engine;

import java.io.FileNotFoundException;

public interface ModelController {

    <R> R getModelController (Class<R> key);

    String SaveData ();
    
    GameAuthoringData loadData (String filePath) throws FileNotFoundException;
    
    GameData getGameData ();
}
