package engine.settings;
import java.util.List;
import java.util.function.BiConsumer;

import engine.BindableType;
import engine.path.PathOption;

public interface BindableGameMode extends BindableType<GameModeBuilder>{
	GameModeBuilder addWinningConditionsListener(BiConsumer<List<String>, List<String>> listener);
	GameModeBuilder addLosingConditionsListener(BiConsumer<List<String>, List<String>> listener);
	GameModeBuilder addGameTypeListener(BiConsumer<String, String> listener);
	GameModeBuilder addInitialLivesListener(BiConsumer<Integer, Integer> listener);
	GameModeBuilder addInitialMoneyListener(BiConsumer<Integer, Integer> listener);
	GameModeBuilder addGridSizeListener(BiConsumer<Integer, Integer> listener);
	GameModeBuilder addPathTypeListener(BiConsumer<PathOption, PathOption> listener);
	GameModeBuilder addPathListener(BiConsumer<List<Integer>, List<Integer>> listener);



}
