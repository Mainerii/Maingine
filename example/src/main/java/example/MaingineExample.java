package example;

import example.game.Game;
import me.joosua.maingine.Maingine;
import me.joosua.maingine.engine.gamestate.GameStateManager;
import me.joosua.maingine.settings.EngineSettings;
import me.joosua.maingine.settings.WindowSettings;

public class MaingineExample {

	public static void main(String[] args) {

	  Maingine.DEBUG = false;

    EngineSettings engineSettings = new EngineSettings();
    engineSettings.setTargetFps(60);
    engineSettings.setTargetUps(120);

    WindowSettings windowSettings = new WindowSettings();
    windowSettings.setTitle("Maingine example");
    windowSettings.setResizable(true);
    windowSettings.setVisibility(true);

		Maingine maingine = new Maingine();
    maingine.setSettings(engineSettings, windowSettings);

    maingine.init();

    GameStateManager gameStateManager = maingine.getGameStateManager();
    gameStateManager.addGameState("Game", new Game(maingine.getEngine()));
    maingine.setMainStateName("Game");

    maingine.run();

	}

}
