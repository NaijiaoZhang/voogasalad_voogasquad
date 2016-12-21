package engine.level.wave;

import javafx.beans.property.SimpleStringProperty;

public interface WaveString {
	public SimpleStringProperty getID();
	public SimpleStringProperty getEnemy();
	public SimpleStringProperty getCount();
	public SimpleStringProperty getFrequency();
	public SimpleStringProperty getStartTime();
	public SimpleStringProperty getPath();
}
