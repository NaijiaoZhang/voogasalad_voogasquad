package engine.level;

import java.util.List;
import engine.TypeBuilder;
import engine.level.wave.WaveManager;


public interface LevelBuilder extends TypeBuilder<Level, LevelBuilder>, BindableLevel {

    LevelBuilder buildPaths (List<Integer> paths);

    LevelBuilder buildWaves (WaveManager enemies);

    LevelBuilder buildRewardHealth (double rewardHealth);

    LevelBuilder buildRewardMoney (double rewardMoney);

    LevelBuilder buildRewardScore (double rewardScore);

    LevelBuilder buildDurationInSeconds (double durationInSeconds);

    LevelBuilder buildLevelTime (double time);

}
