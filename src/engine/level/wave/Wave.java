package engine.level.wave;

import engine.Type;


public interface Wave extends Type {

    public int getEnemyID ();

    public void setEnemyID (int enemyID);

    public int getPathID ();

    public void setPathID (int pathID);

    public int getEnemyCount ();

    public void setEnemyCount (int enemyCount);

    public double getStartTime ();

    public void setStartTime (double startTime);

    public double getFrequency ();

    public void setFrequency (double frequency);

    public double calculateTime ();
}
