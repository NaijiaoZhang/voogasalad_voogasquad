package authoring.editorview.level;

import authoring.editorview.INodeView;


/**
 * 
 * Communicates with the model to indicate when a new level was chosen
 * 
 * @author Diane Hadley
 * @author Kayla Schulz
 *
 */
public interface LevelSetView extends INodeView {

    public void setDelegate (LevelAuthoringViewDelegate delegate);
}
