package me.joosua.maingine.settings;

import me.joosua.maingine.engine.Engine;

/**
 * <p>EngineSettings is a class for storing all the engines's settings.</p>
 *
 * <p>Nothing here affects the engine immediately but the values can be passed
 * for {@link me.joosua.maingine.engine.Engine Engine} when creating one.</p>
 *
 * @since unreleased
 */
public class EngineSettings {

  private double targetUps = 0;
  private double targetFps = 0;

  /**
   * <p>Set the target FPS.</p>
   *
   * <p>The actual FPS can be slightly over the targeted one and much lower than
   * it on too slow systems.</p>
   *
   * <p>The value can be changed once the engine is running with
   * {@link Engine#setTargetFps(double)}.</p>
   *
   * @param fps Targeted frames per second
   * @see #getTargetFps()
   * @since unreleased
   */
  public void setTargetFps(double fps) {

    this.targetFps = fps;

  }

  /**
   * <p>Get the target FPS.</p>
   *
   * <p>This is the FPS engine tries to reach and keep. The actual FPS can be slightly over
   * the targeted one and much lower than it on too slow systems.</p>
   *
   * <p>The value can be gotten once the engine is running with
   * {@link Engine#getTargetFps()}.</p>
   *
   * @return Targeted frames per second
   * @see #setTargetFps(double)
   * @since unreleased
   */

  public double getTargetFps() {

    return targetFps;

  }

  /**
   * <p>Set the target UPS.</p>
   *
   * <p>The actual UPS can be slightly over the targeted one and much lower than
   * it on too slow systems.</p>
   *
   * <p>The value can be changed once the engine is running with
   * {@link Engine#setTargetUps(double)}.</p>
   *
   * @param ups Targeted updates per second
   * @see #getTargetUps()
   * @since unreleased
   */
  public void setTargetUps(double ups) {

    this.targetUps = ups;

  }

  /**
   * <p>Get the target UPS.</p>
   *
   * <p>This is the UPS engine tries to reach and keep. The actual UPS can be slightly over
   * the targeted one and much lower than it on too slow systems.</p>
   *
   * <p>The value can be gotten once the engine is running with
   * {@link Engine#getTargetUps()}.</p>
   *
   * @return Targeted updates per second
   * @see #setTargetUps(double)
   * @since unreleased
   */
  public double getTargetUps() {

    return targetUps;

  }

}
