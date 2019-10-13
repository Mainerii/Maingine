package me.joosua.maingine.engine;

import me.joosua.maingine.glfw.window.Window;
import me.joosua.maingine.settings.EngineSettings;
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
 * @since 0.0.3
 */
public class Engine {

  private static final Logger logger = LogManager.getLogger(Engine.class);

  private Window window;

  private boolean closeRequested;

  private int fps = 0;
  private double targetFps = 0;
  private double targetFpsTime = 0;

  private int ups = 0;
  private double targetUps = 0;
  private double targetUpsTime = 0;

  /**
   * <p>Initialize the engine.</p>
   *
   * <p>This handles everything that needs to be taken care
   * of before starting the engine with {@link #run}.</p>
   *
   * @param window The window to be used with engine.
   * @see #run
   * @since 0.0.3
   */
  public Engine(EngineSettings settings, Window window) {

    logger.info("Beginning engine initialization");

    this.window = window;

    setTargetFps(settings.getTargetFps());
    setTargetUps(settings.getTargetUps());

    logger.info("The engine has been initialized");

  }

  /**
   * <p>Start the game loop.</p>
   *
   * <p>This function will take the control of the callers thread. No
   * return until stopping the engine.</p>
   *
   * @since 0.0.3
   */
  public void run() {

    logger.info("Starting the engine");

    double refreshTimer = 0;

    double deltaUps = 0;
    double deltaFps = 0;
    int frames = 0;
    int ticks = 0;

    double initialTime = GLFW.glfwGetTime();

    gameloop:
    while (true) {

      double currentTime = GLFW.glfwGetTime();
      double lastLoopTime = currentTime - initialTime;

      refreshTimer += lastLoopTime;

      deltaUps += lastLoopTime / targetUpsTime;
      deltaFps += lastLoopTime / targetFpsTime;

      initialTime = currentTime;

      if (targetUpsTime > 0 && deltaUps >= 1) {

        processInput();
        update(deltaUps * targetUpsTime);

        if (closeRequested) break gameloop;

        ticks++;
        deltaUps = 0;

      }

      if (targetFpsTime > 0 && deltaFps >= 1) {

        render();

        frames++;
        deltaFps--;

      }

      if (refreshTimer >= 1) {

        refreshTimer = 0;

        fps = frames;
        ups = ticks;

        frames = 0;
        ticks = 0;

        System.out.println(String.format("UPS: %s, FPS: %s", ups, fps));

      }

    }

    logger.info("The engine has been stopped");

  }

  /**
   * <p>Poll the user input.</p>
   *
   * <p>This should be called before {@link #update(double)}.</p>
   *
   * @since 0.0.3
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
   * @since 0.0.3
   */
  private void update(double delta) {

  }

  /**
   * <p>Render to screen.</p>
   *
   * <p>This should be called after {@link #update(double)}.</p>
   *
   * @since 0.0.3
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
   * @since 0.0.3
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
   * @since 0.0.3
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
   * @since 0.0.3
   */
  public boolean isCloseRequested() {

    return closeRequested;

  }

  /**
   * <p>Set the target FPS.</p>
   *
   * <p>The actual FPS can be slightly over the targeted one and much lower than
   * it on too slow systems.</p>
   *
   * @param fps Targeted frames per second
   * @see #getFps()
   * @see #getTargetFps()
   * @since 0.0.3
   */
  public void setTargetFps(double fps) {

    this.targetFps = fps;

    if (fps > 0) {
      targetFpsTime = 1 / fps;
    } else {
      targetFpsTime = 0;
    }

  }

  /**
   * <p>Get the target FPS.</p>
   *
   * <p>This is the FPS engine tries to reach and keep. The actual FPS can be slightly over
   * the targeted one and much lower than it on too slow systems.</p>
   *
   * @return Targeted frames per second
   * @see #setTargetFps(double)
   * @since 0.0.3
   */
  public double getTargetFps() {

    return targetFps;

  }

  /**
   * <p>Get current FPS.</p>
   *
   * <p>This value is updated once every second so it can only be used
   * for statistics.</p>
   *
   * @return Current rate of frames per second.
   * @see #setTargetFps(double)
   * @since 0.0.3
   */
  public int getFps() {

    return fps;

  }

  /**
   * <p>Set the target UPS.</p>
   *
   * <p>The actual UPS can be slightly over the targeted one and much lower than
   * it on too slow systems.</p>
   *
   * @param ups Targeted updates per second
   * @see #getUps()
   * @see #getTargetFps()
   * @since 0.0.3
   */
  public void setTargetUps(double ups) {

    this.targetUps = ups;

    if (ups > 0) {
      targetUpsTime = 1 / ups;
    } else {
      targetUpsTime = 0;
    }

  }

  /**
   * <p>Get the target UPS.</p>
   *
   * <p>This is the UPS engine tries to reach and keep. The actual UPS can be slightly over
   * the targeted one and much lower than it on too slow systems.</p>
   *
   * <p>Target UPS of <code>0</code> or lower removes the UPS limitation.</p>
   *
   * @return Targeted updates per second
   * @see #setTargetUps(double)
   * @since 0.0.3
   */
  public double getTargetUps() {

    return targetUps;

  }

  /**
   * <p>Get current UPS.</p>
   *
   * <p>This value is updated once every second so it can only be used
   * for statistics.</p>
   *
   * @return Current rate of updates per second.
   * @see #setTargetUps(double)
   * @since 0.0.3
   */
  public int getUps() {

    return ups;

  }

}
