package engine.settings;

import java.util.List;

import engine.TypeBuilder;
import engine.path.PathOption;


public interface GameModeBuilder extends TypeBuilder<GameMode, GameModeBuilder>, BindableGameMode { //TODO - Add bindable interface 
	GameModeBuilder buildWinningConditions(List<String> winningConditions);
	GameModeBuilder buildLosingConditions(List<String> losingConditions);
	GameModeBuilder buildGameType(String gameType);
	GameModeBuilder buildInitialLives(int lives);
	GameModeBuilder buildInitialMoney(int money);
	GameModeBuilder buildPathType(PathOption pathType);
	GameModeBuilder buildGridSize(int size);
	GameModeBuilder buildPaths(List<Integer> paths);

}
