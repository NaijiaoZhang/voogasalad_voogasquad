package authoring.editorview.tower;

import authoring.editorview.INodeView;


/**
 * The Tower View interface will detail the components of the tower creation. The interface
 * determines a portion of our internal API.
 *
 * @author Kayla Schulz
 */
public interface TowerSetView extends INodeView {

    public void setDelegate (TowerAuthoringViewDelegate delegate);

}
