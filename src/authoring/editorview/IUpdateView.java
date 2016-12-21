package authoring.editorview;

import java.util.List;


public interface IUpdateView {

    void updateNameDisplay (String name);

    void updateImagePathDisplay (String imagePath);

    void updateSizeDisplay (double size);

    void updateBank (List<Integer> ids);

    void updateDeleteEntity (String entityID);

    /**
     * Returns the highest item index less than the current. Helpful for when the current item being
     * viewed is deleted.
     * If there are none, the lowest index greater than the current will be returned.
     * If there are no other items, a null value is returned
     */
    public Integer getNearestAvailableItemID (int id);

}
