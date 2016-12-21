package authoring.editorview.level;

import java.util.List;
import authoring.editorview.IUpdateView;
import authoring.editorview.ListDataSource;
import engine.level.wave.WaveString;


public interface LevelUpdateView extends LevelSetView, IUpdateView {

    public void updateRewardScore (double winPoints);

    public void updateRewardHealth (double winHealth);

    public void updateRewardMoney (double winMoney);

    public void updateTransitionTime (double time);

    public void updateLevelOptions (List<Integer> levelOptions);

    public void setLevelListDataSource (ListDataSource source);

    // Wave Object Updates

    public void updateWaves (List<WaveString> waves);

}
