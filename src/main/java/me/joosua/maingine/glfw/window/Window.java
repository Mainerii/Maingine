package me.joosua.maingine.glfw.window;

import me.joosua.maingine.settings.WindowSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

/**
 * <p>Window class takes care of everything to do with windows.</p>
 *
 * <p>This also functions as a wrapper for GLFW and no GLFW's window command
 * should be used outside of this class for proper functionality.</p>
 *
 * @since unreleased
 */
public class Window {

  private static final Logger logger = LogManager.getLogger(Window.class);

  private long windowID;

  private String title;

  /**
   * <p>Create a window.</p>
   *
   * <p>This is the only way to create the window properly. Do not use GLFW functions
   * by themselves to create the window for correct Maingine usage. Other implementations
   * will cause problems if you don't what you are doing.</p>
   *
   * @param settings Initial values for window.
   * @since unreleased
   */
  public Window(final WindowSettings settings) {

    if (settings == null) {

      logger.error("WindowSettings can't be NULL!");
      logger.error("Window couldn't be created!");
      return;

    }

    title = settings.getTitle();

    GLFW.glfwDefaultWindowHints();

    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0);
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, true ? 1 : 0);

    windowID = GLFW.glfwCreateWindow(300, 300, title, 0, 0);

    if (windowID == 0) {

      logger.error("Window couldn't be created!");
      return;

    }

    if (true) {
      GLFW.glfwShowWindow(windowID);
    }

    logger.info("Window '" + windowID + "' has been created");

  }

  /**
   * <p>Update the window.</p>
   *
   * <p>Currently this means swapping of buffers and polling the events. Both of these
   * are required for basic window functionality.</p>
   *
   * <p>Not calling this constantly will cause the window to freeze and other unwanted behavior.</p>
   *
   * @since unreleased
   */
  public void update() {

    GLFW.glfwSwapBuffers(windowID);

    GLFW.glfwPollEvents();

  }

  /**
   * <p>Destroy the window properly. This should always used for closing the window. An exception
   * to this is if GLFW was uninitialized as it takes care of the open window's in it's context.</p>
   *
   * <p>Window is only closed if {@link #isOpen()} returns <code>TRUE</code>.</p>
   *
   * @return <code>TRUE</code> if the window was closed, <code>FALSE</code> if not.
   * @since unreleased
   */
  public boolean destroy() {

    if (isOpen()) {

      GLFW.glfwDestroyWindow(windowID);

      logger.info("Window '" + windowID + "' has been destroyed");

      windowID = 0;

      return true;

    }

    return false;

  }

  /**
   * <p>Checks whether the window should close or not. This doesn't mean that window will be
   * closed, that must be taken care of separately.</p>
   *
   * <p>Usually this means that X (MS Windows) was pressed or a keybinding was used. This will
   * not return <code>TRUE</code> if the program was killed.</p>
   *
   * <p><code>TRUE</code> is also returned if {@link #isOpen()} returns <code>FALSE</code>.</p>
   *
   * @return <code>TRUE</code> if the window should close, <code>FALSE</code> if not.
   * @since unreleased
   */
  public boolean isCloseRequested() {

    return isOpen() ? GLFW.glfwWindowShouldClose(windowID) : true;

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
   * @since unreleased
   */
  public boolean isOpen() {

    return windowID != 0;

  }

  /**
   * <p>Sets the window's title.</p>
   *
   * <p><code>NULL</code> is treated as an empty string. Whether the
   * title is visible or where it's located, depends on the system.</p>
   *
   * <p>Title is only set if {@link #isOpen()} returns <code>TRUE</code>.</p>
   *
   * @param title The title to be used.
   * @return Whether the title was changed or not.
   * @see #getTitle()
   * @since unreleased
   */
  public boolean setTitle(String title) {

    if (isOpen()) {

      if (title == null) title = "";

      GLFW.glfwSetWindowTitle(windowID, title);

      return true;

    }

    return false;

  }

  /**
   * <p>Gets the window's current title.</p>
   *
   * <p>The result may be invalid if the title was not set the proper way with
   * {@link #setTitle(String)} nor given on window creation.</p>
   *
   * @return Title set with {{@link #setTitle(String)}} or <code>NULL</code> if none set.
   * @see #setTitle(String)
   * @since unreleased
   */
  public String getTitle() {

    return title;

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
   * @since unreleased
   */
  public long getWindowID() {

    return windowID;

  }

}
