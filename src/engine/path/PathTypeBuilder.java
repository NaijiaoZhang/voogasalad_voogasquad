package engine.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import engine.AbstractTypeBuilder;
import engine.ability.Ability;
import engine.enemy.Enemy;
import engine.observer.ObservableList;
import engine.observer.ObservableListProperty;
import engine.observer.ObservableObjectProperty;
import engine.observer.ObservableProperty;
import engine.tower.Tower;
import engine.tower.TowerType;
import engine.weapon.Weapon;

public class PathTypeBuilder extends AbstractTypeBuilder<Path, PathBuilder> implements PathBuilder, PathInitializer {
    
     public static final String DEFAULT_NAME = "New Path";
     public static final String DEFAULT_IMAGE_PATH = "Images/blacksquare.png";
     public static final String DEFAULT_SOUND_PATH = "Music/DopeBeats.mp3";
     
     public static final double DEFAULT_SIZE = 1;
     public static final PathOption DEFAULT_TYPE = PathOption.SET;
     //public static final List<Coordinate<Integer>> DEFAULT_COORDINATES = Arrays.stream(new GridCoordinate[]{}).collect(Collectors.toList());
     public static final GridCoordinate[] DEFAULT_COORDINATES = new GridCoordinate[]{};
     public static final Integer DEFAULT_GRID_ROWS = 10;
     public static final Integer DEFAULT_GRID_COLUMNS = 10;
     
     
     private ObservableProperty<PathOption> type;
     private ObservableList<Coordinate<Integer>> coordinates;
     private ObservableProperty<Integer> gridRows;
     private ObservableProperty<Integer> gridColumns;
     
     public PathTypeBuilder() {
         super(DEFAULT_NAME, DEFAULT_IMAGE_PATH, DEFAULT_SIZE, DEFAULT_SOUND_PATH);
     }
    
    @Override
    public PathBuilder buildType(PathOption type) {
        this.type.setProperty(type);
        return this;
    }
    
    @Override
    public PathBuilder buildCoordinates(List<Coordinate<Integer>> coordinates) {
        this.coordinates.setProperty(coordinates);
        return this;
    }
    
    @Override
    public PathBuilder buildCoordinates (Coordinate<Integer> ... coordinates) {
        return buildCoordinates(Arrays.stream(coordinates).collect(Collectors.toList()));
    }
    
    @Override
    public PathBuilder buildGridSizeRows (Integer gridRows) {
        this.gridRows.setProperty(gridRows);
        return this;
    }

    @Override
    public PathBuilder buildGridSizeColumns (Integer gridColumns) {
        this.gridColumns.setProperty(gridColumns);
        return this;
    }
    
    @Override
    protected Path create () {
        return new PathType(this);
    }

    @Override
    public ObservableProperty<PathOption> getType () {
        return type;
    }

    @Override
    public ObservableList<Coordinate<Integer>> getCoordinates () {
        return coordinates;
    }
    
    @Override
    public ObservableProperty<Integer> getGridRows () {
        return gridRows;
    }
    
    @Override
    public ObservableProperty<Integer> getGridColumns () {
        return gridColumns;
    }

    @Override
    protected void restoreTypeDefaults () {
        this.type = new ObservableObjectProperty<PathOption>(DEFAULT_TYPE);
        this.coordinates = new ObservableListProperty<Coordinate<Integer>>(Arrays.stream(DEFAULT_COORDINATES).collect(Collectors.toList()));
        this.gridRows = new ObservableObjectProperty<Integer>(DEFAULT_GRID_ROWS);
        this.gridColumns = new ObservableObjectProperty<Integer>(DEFAULT_GRID_COLUMNS);
    }

    @Override
    protected PathBuilder getThis () {
        return this;
    }
    
    @Override
    public PathBuilder addTypeListener(BiConsumer<PathOption, PathOption> listener) {
        type.addListener(listener);
        return this;
    }
    
    @Override
    public PathBuilder addCoordinatesListener(BiConsumer<List<Coordinate<Integer>>, List<Coordinate<Integer>>> listener) {
        coordinates.addListener(listener);
        return this;
    }

    @Override
    public PathBuilder addGridRowsListener (BiConsumer<Integer, Integer> listener) {
        gridRows.addListener(listener);
        return this;
    }

    @Override
    public PathBuilder addGridColumnsListener (BiConsumer<Integer, Integer> listener) {
        gridColumns.addListener(listener);
        return this;
    }

    @Override
    protected PathBuilder copyType (Path type) {
        return this
                .buildCoordinates(type.getCoordinates())
                .buildGridSizeColumns(type.getGridColumns())
                .buildGridSizeRows(type.getGridRows())
                .buildType(type.getType());
    }
    
}
