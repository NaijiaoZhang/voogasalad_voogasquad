package authoring.editorview;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * 
 * @author Andrew Bihl
 *
 *         This class allows the user to implement a simple list view where cells are based off a
 *         list of data object IDs.
 *         The ListCellData object represents the information that should be presented in the list
 *         view, and is retrieved from a class implementing the
 *         dataSource interface.
 *         To change the way the list cells looks, override createCellFromData().
 *         If any initial cells hold non-data cells at all times, the CONTENT_OFFSET value must be
 *         set. For example, there may button in
 *         the first cell that is clicked to create a new object. The CONTENT_OFFSET should be set
 *         to 1 after this button cell is added so
 *         that the mapping of indices to data id's can be maintained.
 *
 */

public abstract class ImageBank implements ChangeListener<Number> {
    protected ListDataSource dataSource;

    protected ListView<Node> listView;
    protected ObservableList<Node> items;

    // Index represents the enemies index in the list view, value represents the corresponding
    // enemy's id
    protected ArrayList<Integer> itemIDs;

    protected final int DEFAULT_CELL_HEIGHT = 60;
    protected final int DEFAULT_CELL_WIDTH = 60;
    protected final int DEFAULT_BANK_WIDTH = 120;
    protected final String DEFAULT_SUBJECT_IMAGE_PATH = "./Images/questionmark.png";
    protected int CONTENT_OFFSET = 0;

    public ImageBank () {
        items = FXCollections.observableArrayList();
        itemIDs = new ArrayList<Integer>();
        listView = new ListView<Node>();
        listView.setItems(items);
        listView.setPrefWidth(DEFAULT_BANK_WIDTH);
        listView.getSelectionModel().selectedIndexProperty().addListener(this);
    }

    public void setListDataSource (ListDataSource source) {
        this.dataSource = source;
    }

    public Node getInstanceAsNode () {
        return listView;
    }

    public void updateBank () {
        ArrayList<Integer> idCopy = (ArrayList<Integer>) this.itemIDs.clone();
        int i = 0;
        while (i < idCopy.size()) {
            if (idCopy.get(i).equals(-1)) {
                idCopy.remove(i);
            }
            else {
                i++;
            }
        }
        this.updateBank(idCopy);
    }

    public void updateBank (List<Integer> ids) {
        if (dataSource == null) {
            //System.out.println("Table data source not set");
            return;
        }
        this.items.remove(CONTENT_OFFSET, items.size());
        itemIDs = new ArrayList<Integer>();
        if (ids.size() != 0) {
            for (int i = 0; i <= Collections.max(ids) + 1; i++)
                itemIDs.add(-1);
        }
        for (int i = 0; i < ids.size(); i++) {
            ListCellData cellData = dataSource.getCellDataForSubject(ids.get(i));
            Node cell = createCellFromData(cellData);
            items.add(cell);
            // itemIDs.set(cellData.getId(), items.size()-1);
            itemIDs.set(items.size() - 1, cellData.getId());
        }
    }

    public Integer getIndexForItemWithID (int id) {
        for (int i = 0; i < this.itemIDs.size(); i++) {
            if (this.itemIDs.get(i) == id) {
                return i;
            }
        }
        return null;
    }

    /**
     * 
     * @param index of item
     * @return ID of the data object represented at that index. Will be null if there is no item
     *         there.
     */
    public Integer getIDForItemAtIndex (int index) {
        Integer result = this.itemIDs.get(index);
        if (result < 0) {
            return null;
        }
        return result;
    }

    protected Node createCellFromData (ListCellData data) {
        ImageView cell = new ImageView();
        String imageFilePath = data.getImagePath();
        if (imageFilePath.equals(null) || imageFilePath.length() < 1) {
            imageFilePath = DEFAULT_SUBJECT_IMAGE_PATH;
        }
        File file = new File(data.getImagePath());
        Image image = new Image(file.toURI().toString());
        cell.setImage(image);
        cell.setPreserveRatio(true);
        cell.setFitHeight(DEFAULT_CELL_HEIGHT);
        cell.setFitWidth(DEFAULT_CELL_WIDTH);
        return cell;
    }

    protected abstract void userSelectedRow (int index);

    @Override
    public void changed (ObservableValue<? extends Number> observable,
                         Number oldValue,
                         Number newValue) {
        userSelectedRow(newValue.intValue());
    }

}
