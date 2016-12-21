package engine.level.wave;

import javafx.beans.property.SimpleStringProperty;

public class WaveStringType implements WaveString{
	private int id;
	private int enemy;
	private int path;
	private double frequency;
	private double startTime;
	private int count;
	
	public WaveStringType(Wave wave) {
		this.id = wave.getId();
		this.enemy = wave.getEnemyID();
		this.path = wave.getPathID();
		this.frequency = wave.getFrequency();
		this.startTime = wave.getStartTime();
		this.count = wave.getEnemyCount();
	}
	
	@Override
	public SimpleStringProperty getID() {
		return new SimpleStringProperty(Integer.toString(id));
	}

	@Override
	public SimpleStringProperty getEnemy() {
		return new SimpleStringProperty(Integer.toString(enemy));
	}

	@Override
	public SimpleStringProperty getCount() {
		return new SimpleStringProperty(Integer.toString(count));
	}

	@Override
	public SimpleStringProperty getFrequency() {
		return new SimpleStringProperty(Double.toString(frequency));
	}

	@Override
	public SimpleStringProperty getStartTime() {
		return new SimpleStringProperty(Double.toString(startTime));
	}

	@Override
	public SimpleStringProperty getPath() {
		return new SimpleStringProperty(Integer.toString(path));
	}

}
