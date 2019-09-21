package me.joosua.maingine.glfw.window;

import me.joosua.maingine.settings.WindowSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Window {

  private static final Logger logger = LogManager.getLogger(Window.class);

  private long windowID;

  public Window(WindowSettings settings) {

    GLFW.glfwDefaultWindowHints();

    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, settings.isResizable() ? 1 : 0);

    windowID = GLFW.glfwCreateWindow(300, 300, settings.getTitle(), 0, 0);

    if (windowID == 0) {

      logger.error("Window couldn't be created!");
      return;

    }

    logger.info("Window '" + windowID + "' has been created");

  }

  public void update() {

    GLFW.glfwSwapBuffers(windowID);

    GLFW.glfwPollEvents();

  }

  /**
   * <p>Destroy the window properly. This should always used for closing the window. An exception
   * to this is if GLFW was uninitialized as it takes care of the open window's in it's context.</p>
   *
   * <p>Window is only closed if {@link #isOpen()} returns <code>TRUE</code>.</p>
   */
  public void destroy() {

    if (isOpen()) {

      GLFW.glfwDestroyWindow(windowID);

      logger.info("Window '" + windowID + "' has been destroyed");

      windowID = 0;

    }

  }

  /**
   * <p>Checks whether the window is open or not.</p>
   *
   * <p>Note that this checks if the window's ID is set or not. That means that
   * <code>FALSE</code> is returned only if the window has not been created properly or
   * it has been destroyed properly.</p>
   *
   * <p>An incorrect <code>TRUE</code> might occur if the window has crashed in native code
   * or had some other unexpected closure like one made without {@link #destroy()}.</p>
   *
   * @return <code>TRUE</code> if the window is open, <code>FALSE</code> if not.
   */
  public boolean isOpen() {

    return windowID != 0;

  }

  /**
   * <p>Gets the window's ID. The ID can be used to execute GLFW functions but only
   * do so if you are familiar with Maingine and know what you're doing as it may
   * cause bugs.</p>
   *
   * <p>The ID will be <code>0</code> if the window isn't open.</p>
   *
   * @return The window's ID
   * @see #isOpen()
   */
  public long getWindowID() {

    return windowID;

  }

}
