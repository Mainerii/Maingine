package me.joosua.maingine;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.glfw.GlfwManager;
import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.EngineSettings;
import me.joosua.maingine.settings.WindowSettings;
import me.joosua.maingine.utils.LoggerManager;
import me.joosua.maingine.utils.SystemInfo;

/**
 * <p>Main class of Maingine. This class bootstraps the other parts of the engine.
 * Some of the intialization is done here.</p>
 *
 * @since 0.0.2
 */
public class Maingine {

  public static final String VERSION = "0.0.2";

  public static boolean DEBUG = false;

  private Engine engine;

  /**
   * <p>Main constructor of Maingine. The first part of
   * initialization is done here.</p>
   *
   * @since 0.0.2
   */
  public Maingine(EngineSettings engineSettings, WindowSettings windowSettings) {

    LoggerManager.prepareLogger();

    SystemInfo.printInfo();

    if (!GlfwManager.init()) {
      throw new IllegalStateException("GLFW couldn't be initialized ");
    }

    Window window = new Window(windowSettings);

    if (!window.isOpen()) {
      throw new IllegalStateException("Window couldn't be created");
    }

    engine = new Engine(engineSettings, window);
    engine.run();

    window.destroy();

    GlfwManager.terminate();

  }

  /**
   * <p>Gets Maingine's current engine instance.</p>
   *
   * <p>The returned value will only be valid after
   * initialization with {@link #Maingine(EngineSettings, WindowSettings)}.</p>
   *
   * @return Get Maingine's current engine instance or <code>NULL</code> if none.
   * @since unreleased
   */
  public Engine getEngine() {

    return engine;

  }

}
