package authoring.editorview.path;

public interface PathAuthoringViewDelegate {

    public void onUserEnteredGridDimensions (int dimensions);

    public void onUserEnteredPathImage (String pathImagePath);

    public void onUserEnteredPathName (String pathName);

    public void onUserEnteredPathType (String pathType);

    public void onUserEnteredCreatePath ();

    public void onUserEnteredDeletePath ();

    public void onUserEnteredEditPath (int pathID);

    public boolean onUserEnteredAddPathCoordinate (int x, int y);

    public boolean onUserEnteredRemovePathCoordinate (int x, int y);

}
