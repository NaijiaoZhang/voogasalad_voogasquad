package authoring.editorview.weapon;

import authoring.editorview.INodeView;


/**
 * The interface for all weapon views. Allows those classes to implement setDelegate and
 * getInstanceAsNode
 * 
 * @author Kayla Schulz
 *
 */
public interface WeaponSetView extends INodeView {

    public void setDelegate (WeaponAuthoringViewDelegate delegate);
}
