package authoring.editorview.level.subviews.editorfields;

import java.util.List;
import java.util.ResourceBundle;
import authoring.editorview.level.WaveObject;
import engine.level.wave.WaveString;
import authoring.editorview.level.LevelSetView;
import authoring.editorview.level.LevelAuthoringViewDelegate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


/**
 * 
 * @author Kayla Schulz
 *
 */
public class WaveTableView implements LevelSetView {

    private TableView<WaveObject> waveTable;
    private TableColumn<WaveObject, String> waveNumberCol;
    private TableColumn<WaveObject, String> enemyNameCol;
    private TableColumn<WaveObject, String> enemyCountCol;
    private TableColumn<WaveObject, String> enemyFrequencyCol;
    private TableColumn<WaveObject, String> pathCol;
    private TableColumn<WaveObject, String> timeDelayCol;
    private ObservableList<WaveObject> data;

    private LevelAuthoringViewDelegate delegate;

    public WaveTableView (ResourceBundle labelsResource, int width) {
        waveTable = new TableView<WaveObject>();
        data = FXCollections.observableArrayList();
        waveTable.setPrefWidth(width);
        waveTable.setEditable(true);
        createTableColumns();
        setEditableColumns();
    }

    @SuppressWarnings("unchecked")
    private void createTableColumns () {
        waveNumberCol = new TableColumn<WaveObject, String>("Wave Number");
        enemyNameCol = new TableColumn<WaveObject, String>("Enemy Name");
        enemyCountCol = new TableColumn<WaveObject, String>("Number of Enemies");
        enemyFrequencyCol = new TableColumn<WaveObject, String>("Enemy Frequency");
        pathCol = new TableColumn<WaveObject, String>("Path");
        timeDelayCol = new TableColumn<WaveObject, String>("Time Delay");
        waveNumberCol.setCellValueFactory(
                                          new PropertyValueFactory<WaveObject, String>("waveNumber"));
        enemyNameCol.setCellValueFactory(
                                         new PropertyValueFactory<WaveObject, String>("enemyName"));

        enemyCountCol
                .setCellValueFactory(new PropertyValueFactory<WaveObject, String>("numOfEnemies"));
        enemyFrequencyCol
                .setCellValueFactory(new PropertyValueFactory<WaveObject, String>("enemyFrequency"));

        pathCol
                .setCellValueFactory(new PropertyValueFactory<WaveObject, String>("path"));
        timeDelayCol
                .setCellValueFactory(new PropertyValueFactory<WaveObject, String>("timeDelay"));

        waveTable.getColumns().addAll(waveNumberCol, enemyNameCol, enemyCountCol,
                                      enemyFrequencyCol, pathCol, timeDelayCol);
        waveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setEditableColumns () {
        enemyNameCol.setEditable(true);
        enemyNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        enemyNameCol.setOnEditCommit(
                                     new EventHandler<CellEditEvent<WaveObject, String>>() {
                                         @Override
                                         public void handle (CellEditEvent<WaveObject, String> t) {
                                             ((WaveObject) t.getTableView().getItems().get(
                                                                                           t.getTablePosition()
                                                                                                   .getRow()))
                                                                                                           .setEnemyName(t
                                                                                                                   .getNewValue());
                                             delegate.onUserEnteredEnemy(t.getRowValue()
                                                     .getWaveNumber(), t.getNewValue());
                                         }
                                     });
        enemyCountCol.setEditable(true);
        enemyCountCol.setCellFactory(TextFieldTableCell.forTableColumn());
        enemyCountCol.setOnEditCommit(
                                      new EventHandler<CellEditEvent<WaveObject, String>>() {
                                          @Override
                                          public void handle (CellEditEvent<WaveObject, String> t) {
                                              ((WaveObject) t.getTableView().getItems().get(
                                                                                            t.getTablePosition()
                                                                                                    .getRow()))
                                                                                                            .setNumOfEnemies(t
                                                                                                                    .getNewValue());
                                              delegate.onUserEnteredNumofEnemies(t.getRowValue()
                                                      .getWaveNumber(), t.getNewValue());
                                          }
                                      });
        enemyFrequencyCol.setEditable(true);
        enemyFrequencyCol.setCellFactory(TextFieldTableCell.forTableColumn());
        enemyFrequencyCol.setOnEditCommit(
                                          new EventHandler<CellEditEvent<WaveObject, String>>() {
                                              @Override
                                              public void handle (CellEditEvent<WaveObject, String> t) {
                                                  ((WaveObject) t.getTableView().getItems().get(
                                                                                                t.getTablePosition()
                                                                                                        .getRow()))
                                                                                                                .setEnemyFrequency(t
                                                                                                                        .getNewValue());
                                                  delegate.onUserEnteredEnemyFrequency(t
                                                          .getRowValue()
                                                          .getWaveNumber(), t.getNewValue());
                                              }
                                          });
        pathCol.setEditable(true);
        pathCol.setCellFactory(TextFieldTableCell.forTableColumn());
        pathCol.setOnEditCommit(
                                new EventHandler<CellEditEvent<WaveObject, String>>() {
                                    @Override
                                    public void handle (CellEditEvent<WaveObject, String> t) {
                                        ((WaveObject) t.getTableView().getItems().get(
                                                                                      t.getTablePosition()
                                                                                              .getRow()))
                                                                                                      .setPath(t
                                                                                                              .getNewValue());
                                        delegate.onUserEnteredSpawnPoint(t.getRowValue()
                                                .getWaveNumber(), t.getNewValue());
                                    }
                                });
        timeDelayCol.setEditable(true);
        timeDelayCol.setCellFactory(TextFieldTableCell.forTableColumn());
        timeDelayCol.setOnEditCommit(
                                     new EventHandler<CellEditEvent<WaveObject, String>>() {
                                         @Override
                                         public void handle (CellEditEvent<WaveObject, String> t) {
                                             ((WaveObject) t.getTableView().getItems()
                                                     .get(t.getTablePosition().getRow()))
                                                             .setTimeDelay(t.getNewValue());
                                             delegate.onUserEnteredWaveTimeDelay(t.getRowValue()
                                                     .getWaveNumber(), t.getNewValue());
                                         }
                                     });
    }

    @Override
    public Node getInstanceAsNode () {
        return waveTable;
    }

    @Override
    public void setDelegate (LevelAuthoringViewDelegate delegate) {
        this.delegate = delegate;
    }

    private void setData (List<WaveString> waves) {
        data.removeAll(data);
        for (WaveString n : waves) {
            WaveObject temp =
                    new WaveObject(n.getID(), n.getEnemy(), n.getCount(),
                                   n.getFrequency(), n.getPath(), n.getStartTime());
            data.add(temp);
        }
    }

    public void updateWaveTableView (List<WaveString> waves) {
        setData(waves);
        waveTable.setItems(data);
        waveTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
