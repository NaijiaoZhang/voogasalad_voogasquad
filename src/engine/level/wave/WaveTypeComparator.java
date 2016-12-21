package engine.level.wave;

import java.util.Comparator;


public class WaveTypeComparator implements Comparator<Wave> {

    @Override
    public int compare (Wave o1, Wave o2) {
        return (int) (o1.calculateTime() - o2.calculateTime());
    }
}
