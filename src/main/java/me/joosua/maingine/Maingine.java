package me.joosua.maingine;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.glfw.GlfwManager;
import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.EngineSettings;
import me.joosua.maingine.settings.WindowSettings;
import me.joosua.maingine.utils.LoggerManager;
import me.joosua.maingine.utils.SystemInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>Main class of Maingine. This class bootstraps the other parts of the engine.
 * Some of the intialization is done here.</p>
 *
 * @since 0.0.2
 */
public class Maingine {

  public static final String VERSION = "0.0.2";
  public static boolean DEBUG = false;

  private static Logger logger;

  private Engine engine;
  private Window window;

  private EngineSettings engineSettings;
  private WindowSettings windowSettings;

  /**
   * <p>Main constructor of Maingine. The first part of
   * initialization is done here.</p>
   *
   * @since 0.0.2
   */
  public Maingine() {

    LoggerManager.prepareLogger();

    logger = LogManager.getLogger(Maingine.class);

    SystemInfo.printInfo();

  }

  /**
   * <p>Set the engine settings.</p>
   *
   * <p>Settings must be set before {@link #init()}.</p>
   *
   * @param engineSettings Settings for the engine. Can't be null.
   * @param windowSettings Settings for the window. Can't be null.
   * @see #init()
   * @since unreleased
   */
  public void setSettings(EngineSettings engineSettings, WindowSettings windowSettings) {

    if (engineSettings == null) {
      logger.error("EngineSettings can't be null!");
    } else if (windowSettings == null) {
      logger.error("WindowSettings can't be null!");
    } else {
      this.engineSettings = engineSettings;
      this.windowSettings = windowSettings;
    }

  }

  /**
   * <p>Second phase of Maingine initilization.</p>
   *
   * <p>Settings must be set with {@link #setSettings(EngineSettings, WindowSettings)}
   * before this is called.</p>
   *
   * @see #setSettings(EngineSettings, WindowSettings)
   * @since unreleased
   */
  public void init() {

    if (engineSettings == null || windowSettings == null) {

      logger.error("Settings must be set before initialization!");

    } else {

      logger.info("Initializing Maingine");

      if (!GlfwManager.init()) {
        throw new IllegalStateException("GLFW couldn't be initialized ");
      }

      window = new Window(windowSettings);

      if (!window.isOpen()) {
        throw new IllegalStateException("Window couldn't be created");
      }

      engine = new Engine(engineSettings, window);

      logger.info("Maingine has been initialized");

    }

  }

  /**
   * <p>Gentlemen, start your engines.</p>
   *
   * <p>This is where the engine will take control of the thread
   * and first game state will be called shortly.</p>
   *
   * <p>Initialization with {@link #init()} must be done before this.</p>
   *
   * <p>Cleanup will be done immediately after this.</p>
   *
   * @see #stop()
   * @since unreleased
   */
  public void run() {

    if (engine != null) {

      engine.run();

      cleanup();

    } else {

      logger.error("Maingine must initialized before starting!");

    }

  }

  /**
   * <p>Stops the engine from running.</p>
   *
   * <p>This just makes shutdown request to the engine which
   * may be refused.</p>
   *
   * <p>Cleanup will be perfomed after the engine is not running.</p>
   *
   * @see #run()
   * @since unreleased
   */
  public void stop() {

    engine.stop();

  }

  /**
   * <p>Cleanup the mess.</p>
   *
   * <p>This will destroy the window and terminate GLFW from the thread.</p>
   *
   * @since unreleased
   */
  private void cleanup() {

    window.destroy();

    GlfwManager.terminate();

  }

  /**
   * <p>Gets Maingine's current engine instance.</p>
   *
   * <p>The returned value will only be valid after
   * initialization with {@link #init()}.</p>
   *
   * @return Get Maingine's current engine instance or <code>NULL</code> if none.
   * @since unreleased
   */
  public Engine getEngine() {

    return engine;

  }

  /**
   * <p>Gets Maingine's current window instance.</p>
   *
   * <p>The returned value will only be valid after
   * initialization with {@link #init()}.</p>
   *
   * @return Get Maingine's current window instance or <code>NULL</code> if none.
   * @since unreleased
   */
  public Window getWindow() {

    return window;

  }

}
