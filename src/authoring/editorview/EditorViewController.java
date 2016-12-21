package authoring.editorview;

import javafx.scene.Node;


public abstract class EditorViewController {

    protected INodeView view;

    public Node getView () {
        return view.getInstanceAsNode();
    }

    public IUpdateView getUpdateView () {
        return (IUpdateView) this.view;
    }

    public abstract void refreshView ();
}
