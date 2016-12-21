package authoring.editorview.path;

import authoring.editorview.INodeView;


/**
 * 
 * This interface details the components of the path tab. The interface defines our internal API
 * and the public methods defining what components the path view should contain.
 *
 */
public interface PathSetView extends INodeView {

    public void setDelegate (PathAuthoringViewDelegate delegate);

}
