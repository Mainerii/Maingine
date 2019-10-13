package me.joosua.maingine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.engine.gamestate.GameState;
import me.joosua.maingine.engine.gamestate.GameStateManager;
import me.joosua.maingine.glfw.GlfwManager;
import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.EngineSettings;
import me.joosua.maingine.settings.WindowSettings;
import me.joosua.maingine.utils.LoggerManager;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaingineTest {

  @Test
  @Order(1)
  public void testLogger() {

    LoggerManager.prepareLogger(false, new String[] {"maingine", "tests"});

    assertNotNull(System.getProperty("log4j2.debug"));
    assertNotNull(System.getProperty("maingine.logger.dir"));

    LogManager.getLogger(MaingineTest.class);

  }

  @Test
  @Order(2)
  public void testGlfw() {

    assertTrue(GlfwManager.init());

  }

  @Test
  @Order(3)
  public void testWindow() {

    Window window = new Window(null);

    assertEquals(0, window.getWindowID());
    assertFalse(window.isOpen());
    assertTrue(window.isCloseRequested());
    assertFalse(window.destroy());
    assertNull(window.getTitle());
    assertFalse(window.setTitle(""));
    assertFalse(window.setVisibility(true));
    assertFalse(window.isVisible());
    assertFalse(window.isResizable());
    assertFalse(window.destroy());
    assertEquals(0, window.getWindowID(), 0);

    WindowSettings settings = new WindowSettings();

    assertEquals("", settings.getTitle());
    settings.setTitle(null);
    assertEquals("", settings.getTitle());
    settings.setTitle("Example");
    assertEquals("Example", settings.getTitle());
    assertFalse(settings.isVisible());
    settings.setVisibility(true);
    assertTrue(settings.isVisible());
    assertFalse(settings.isResizable());
    settings.setResizable(true);
    assertTrue(settings.isResizable());
    assertNotNull(settings.getSize());
    assertEquals(0, settings.getSize().x);
    assertEquals(0, settings.getSize().y);
    settings.setSize(50, 100);
    assertEquals(50, settings.getSize().x);
    assertEquals(100, settings.getSize().y);

    window = new Window(settings);

    assertNotEquals(0, window.getWindowID());
    assertTrue(window.isOpen());
    assertFalse(window.isCloseRequested());
    assertEquals("Example", window.getTitle());
    assertTrue(window.setTitle(""));
    assertEquals("", window.getTitle());
    assertTrue(window.isVisible());
    assertTrue(window.setVisibility(false));
    assertFalse(window.isVisible());
    assertTrue(window.isResizable());
    assertTrue(window.setSize(0, 0));
    assertTrue(window.destroy());
    assertFalse(window.setTitle(""));
    assertFalse(window.setVisibility(true));
    assertFalse(window.isVisible());
    assertFalse(window.isResizable());
    assertNull(window.getSize());
    assertFalse(window.destroy());
    assertEquals(0, window.getWindowID());

  }

  @Test
  @Order(4)
  public void testMaingine() {

    Maingine maingine = new Maingine();

    final EngineSettings engineSettings = new EngineSettings();
    final WindowSettings windowSettings = new WindowSettings();

    assertNull(maingine.getWindow());
    assertNull(maingine.getEngine());

    maingine.setSettings(null, null);
    maingine.init();

    assertNull(maingine.getWindow());
    assertNull(maingine.getEngine());

    maingine.setSettings(engineSettings, windowSettings);
    maingine.init();

    assertNotNull(maingine.getWindow());
    assertNotNull(maingine.getEngine());

  }

  @Test
  @Order(5)
  public void testEngine() {

    Window window = new Window(new WindowSettings());

    EngineSettings engineSettings = new EngineSettings();
    engineSettings.setTargetFps(0);
    engineSettings.setTargetUps(20);

    Engine engine = new Engine(engineSettings, new GameStateManager(), window);

    assertEquals(0, engine.getTargetFps());
    assertEquals(20, engine.getTargetUps());
    assertEquals(0, engine.getFps());
    assertEquals(0, engine.getUps());

    engine.setTargetFps(10);
    engine.setTargetUps(30);

    assertEquals(10, engine.getTargetFps());
    assertEquals(30, engine.getTargetUps());

  }

  @Test
  @Order(6)
  public void testGameStateManager() {

    GameStateManager gameStateManager = new GameStateManager();

    assertEquals(0, gameStateManager.getGameStates().size());
    assertNull(gameStateManager.getCurrentGameState());
    assertNull(gameStateManager.getGameState(null));
    assertNull(gameStateManager.getGameState(""));
    assertNull(gameStateManager.getGameState("Game"));

    final boolean[] calls = {false, false, false, false};

    GameState gamestate1 = new GameState() {

      public void init() {
        calls[0] = true;
      }

      public void update(double delta) {
        calls[1] = true;
      }

      public void render() {
        calls[2] = true;
      }

      public void unset() {
        calls[3] = true;
      }

    };

    GameState gamestate2 = new GameState() {

      public void init() {

      }

      public void update(double delta) {

      }

      public void render() {

      }

      public void unset() {

      }

    };

    assertFalse(gameStateManager.addGameState(null, gamestate1));
    assertTrue(gameStateManager.addGameState("Game", gamestate1));
    assertFalse(gameStateManager.addGameState("Game", gamestate2));
    assertTrue(gameStateManager.addGameState("Game2", gamestate2));
    assertNotNull(gameStateManager.getGameState("Game"));
    assertEquals(gamestate1, gameStateManager.getGameState("Game"));

    assertTrue(gameStateManager.selectGameState(null));
    assertFalse(calls[0]);
    assertTrue(gameStateManager.selectGameState("Game"));
    assertEquals(gamestate1, gameStateManager.getCurrentGameState());
    assertTrue(calls[0]);
    gameStateManager.update(0);
    gameStateManager.render();
    assertTrue(calls[1]);
    assertTrue(calls[2]);
    assertFalse(gameStateManager.selectGameState("None"));
    assertEquals(gamestate1, gameStateManager.getCurrentGameState());
    assertFalse(calls[3]);
    assertTrue(gameStateManager.selectGameState("Game2"));
    assertEquals(gamestate2, gameStateManager.getCurrentGameState());
    assertTrue(calls[3]);
    assertEquals("Game", gameStateManager.getGameStateName(gamestate1));

    assertFalse(gameStateManager.removeGameState("None"));
    assertFalse(gameStateManager.removeGameState(null));
    assertTrue(gameStateManager.removeGameState("Game2"));
    assertNull(gameStateManager.getCurrentGameState());
    assertNull(gameStateManager.getGameStateName(gameStateManager.getCurrentGameState()));

  }

}
