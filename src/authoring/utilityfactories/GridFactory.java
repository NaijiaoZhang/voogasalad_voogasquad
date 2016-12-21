package authoring.utilityfactories;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;


public final class GridFactory {

    private GridFactory () {
    }

    public static GridPane createRowWithLabelandNode (String labelString,
                                                      Node node,
                                                      int columnSize) {
        GridPane row = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setMinWidth(columnSize);

        ColumnConstraints column2 = new ColumnConstraints();

        row.getColumnConstraints().addAll(column1, column2);

        Label label = new Label(labelString);

        row.add(label, 0, 0);
        row.add(node, 1, 0);

        return row;
    }

}
