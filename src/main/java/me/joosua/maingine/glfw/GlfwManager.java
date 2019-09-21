package me.joosua.maingine.glfw;

import java.io.PrintStream;

import me.joosua.maingine.Maingine;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.system.Configuration;

/**
 * <p>GlfwManager takes care of everything to do with GLFW itself (not windows).</p>
 *
 * <p>The methods here are mainly static and must be called from a specific thread.</p>
 *
 * @since unreleased
 */
public class GlfwManager {

  private static final Logger logger = LogManager.getLogger(GlfwManager.class);

  /**
   * <p>Initialize GLFW for main thread. If the initialization fails, the engine
   * should not proceed with anything to do with GLFW.</p>
   *
   * <p>Note that this should only be called from the main thread and no window's context
   * should be on another thread when this is called.</p>
   *
   * <p>After GLFW is no longer needed, it should be terminated with {@link #terminate()}.
   * Terminating is only required if initialization was successful.</p>
   *
   * @return <code>TRUE</code> is successful, or <code>FALSE</code> if not.
   * @see #terminate()
   * @since unreleased
   */
  public static boolean init() {

    Configuration.DEBUG.set(Maingine.DEBUG);

    if (!GLFW.glfwInit()) {

      logger.error("GLFW couldn't be initialized!");

      return false;

    }

    // Make GLFW user Log4j logger for errors
    PrintStream logStream = IoBuilder.forLogger(logger).setLevel(Level.ERROR).buildPrintStream();
    GLFWErrorCallback.createPrint(logStream);

    logger.info("GLFW has been initialized");

    return true;

  }

  /**
   * <p>Terminate GLFW on current thread. All the windows will be closed
   * and resources used will be freed</p>
   *
   * <p>This should only be called after GLFW is no longer needed and was
   * successfully initialized with {@link #init()}.</p>
   *
   * @see #init()
   * @since unreleased
   */
  public static void terminate() {

    GLFW.glfwTerminate();
    GLFW.glfwSetErrorCallback(null);

    logger.info("GLFW has been terminated");

  }

}
