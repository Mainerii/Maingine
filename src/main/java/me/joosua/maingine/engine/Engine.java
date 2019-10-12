package me.joosua.maingine.engine;

import me.joosua.maingine.glfw.window.Window;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

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

  private boolean closeRequested;

  private int fps = 0;

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

    int targetFps = 60;
    double targetTime = 1.0 / targetFps;

    int loops = 0;
    double fpsTimer = 0;

    double lastLoopTime = GLFW.glfwGetTime() - targetTime;

    while (true) {

      double currentTime = GLFW.glfwGetTime();
      double lastLoopLength = currentTime - lastLoopTime;
      lastLoopTime = GLFW.glfwGetTime();

      fpsTimer += lastLoopLength;
      loops++;

      if (fpsTimer >= 1) {
        fps = loops;
        loops = 0;
        fpsTimer = 0;
        System.out.println("FPS: " + fps + " Delta: " + lastLoopLength);
      }

      processInput();
      update(lastLoopLength);

      if (closeRequested) break;

      render();

      try {

        double sleep = (lastLoopTime - GLFW.glfwGetTime() + targetTime) / 1000;

        if (sleep > 0) {
          Thread.sleep((long) sleep);
        }

      } catch (InterruptedException e) {
        logger.error("Engine sleep error: " + e.getMessage());
      }

    }

    logger.info("The engine has been stopped");

  }

  /**
   * <p>Poll the user input.</p>
   *
   * <p>This should be called before {@link #update(double)}.</p>
   *
   * @since unreleased
   */
  private void processInput() {

    window.pollEvents();

    if (window.isCloseRequested()) {
      closeRequested = true;
    }

  }

  /**
   * <p>Update everything that's needed to before rendering.</p>
   *
   * <p>This should be called after {@link #processInput()}.</p>
   *
   * @param delta Time each update takes (Multiply time-related values with this).
   * @since unreleased
   */
  private void update(double delta) {

  }

  /**
   * <p>Render to screen.</p>
   *
   * <p>This should be called after {@link #update(double)}.</p>
   *
   * @since unreleased
   */
  private void render() {

    window.render();

  }

  /**
   * <p>Request to stop the engine.</p>
   *
   * <p>This is similar to clicking window close request button (X on MS Windows).</p>
   *
   * <p>This can still be cancelled with {@link #refuseCloseRequest()} and the current
   * close request state can be gotten with {@link #isCloseRequested()}.</p>
   *
   * @see #refuseCloseRequest()
   * @see #isCloseRequested()
   * @since unreleased
   */
  public void stop() {

    closeRequested = true;

  }

  /**
   * <p>Cancel stopping the engine.</p>
   *
   * <p>This needs to be called if {@link #isCloseRequested()} returns <code>TRUE</code>
   * and the engine shouldn't be stopped.</p>
   *
   * @see #stop
   * @see #isCloseRequested()
   * @since unreleased
   */
  public void refuseCloseRequest() {

    closeRequested = false;

  }

  /**
   * <p>Checks if the engine is close requested.</p>
   *
   * <p>Close request could have come from {@link Window#isCloseRequested()} or
   * {@link #stop()}.</p>
   *
   * <p>The request can be cancelled with {@link #refuseCloseRequest()}.</p>
   *
   * @return <code>TRUE</code> if the engine close is requested, <code>FALSE</code> if not.
   * @see #stop()
   * @see #isCloseRequested()
   * @since unreleased
   */
  public boolean isCloseRequested() {

    return closeRequested;

  }

  /**
   * <p>Get current FPS.</p>
   *
   * <p>This value is updated once every second so it can only be used
   * for statistics.</p>
   *
   * @return Current rate of frames per second.
   * @since unreleased
   */
  public int getFps() {

    return fps;

  }

}
