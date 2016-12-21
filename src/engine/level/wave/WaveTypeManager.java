package engine.level.wave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import engine.AbstractTypeManager;
import engine.enemy.EnemyManager;

public class WaveTypeManager extends AbstractTypeManager<Wave> implements WaveManager {

	 @Override
	    public void visitRemoveEntry (EnemyManager manager, Integer index) {
	       // applyToAllEntities(a -> a.removeWave(index));
		 //Need to do something
	    }

	@Override
	public List<Wave> waveList() {
		List<Wave> waves = new ArrayList<Wave>(getEntities().values());
		return waves;
	}
	 

}
