package authoring.editorview.path;

public class PathAuthoringViewFactory {

    private PathAuthoringViewFactory () {

    }

    public static PathUpdateView build (int width, int height) {
        return new PathAuthoringView(width, height);
    }

}
