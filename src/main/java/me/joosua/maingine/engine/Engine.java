package me.joosua.maingine.engine;

import me.joosua.maingine.glfw.window.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>The engine of Maingine.</p>
 *
 * <p>This is what controls the runtime program flow and the
 * main game loop.</p>
 *
 * <p>Game states will be called from here.</p>
 *
 * @since unreleased
 */
public class Engine {

  private static final Logger logger = LogManager.getLogger(Engine.class);

  private Window window;

  /**
   * <p>Initialize the engine.</p>
   *
   * <p>This handles everything that needs to be taken care
   * of before starting the engine with {@link #run}.</p>
   *
   * @param window The window to be used with engine.
   * @see #run
   * @since unreleased
   */
  public Engine(Window window) {

    logger.info("Beginning engine initialization");

    this.window = window;

    logger.info("The engine has been initialized");

  }

  /**
   * <p>Start the game loop.</p>
   *
   * <p>This function will take the control of the callers thread. No
   * return until stopping the engine.</p>
   *
   * @since unreleased
   */
  public void run() {

    logger.info("Starting the engine");

    while(!window.isCloseRequested()) {

      processInput();
      update();
      render();

    }

    logger.info("The engine has been stopped");

  }

  /**
   * <p>Poll the user input.</p>
   *
   * <p>This should be called before {@link #update()}.</p>
   *
   * @since unreleased
   */
  private void processInput() {

    window.pollEvents();

  }

  /**
   * <p>Update everything that's needed to before rendering.</p>
   *
   * <p>This should be called after {@link #processInput()}.</p>
   *
   * @since unreleased
   */
  private void update() {

  }

  /**
   * <p>Render to screen.</p>
   *
   * <p>This should be called after {@link #update()}.</p>
   *
   * @since unreleased
   */
  private void render() {

    window.render();

  }

}
