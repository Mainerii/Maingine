package me.joosua.maingine.glfw.window;

import java.nio.IntBuffer;
import me.joosua.maingine.settings.WindowSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;

/**
 * <p>Window class takes care of everything to do with windows.</p>
 *
 * <p>This also functions as a wrapper for GLFW and no GLFW's window command
 * should be used outside of this class for proper functionality.</p>
 *
 * @since 0.0.2
 */
public class Window {

  private static final Logger logger = LogManager.getLogger(Window.class);

  private long windowID;

  private String title;
  private boolean visible;
  private boolean resizable;
  private int width;
  private int height;

  /**
   * <p>Create a window.</p>
   *
   * <p>This is the only way to create the window properly. Do not use GLFW functions
   * by themselves to create the window for correct Maingine usage. Other implementations
   * will cause problems if you don't what you are doing.</p>
   *
   * <p>Creating a window will automatically make it's OpenGL context current
   * (same as calling {@link #selectWindow()}).</p>
   *
   * @param settings Initial values for window.
   * @since 0.0.2
   */
  public Window(final WindowSettings settings) {

    if (settings == null) {

      logger.error("WindowSettings can't be NULL!");
      logger.error("Window couldn't be created!");
      return;

    }

    title = settings.getTitle();
    visible = settings.isVisible();
    resizable = settings.isResizable();

    Vector2i size = settings.getSize();
    width = size.x == 0 ? 1 : size.x;
    height = size.y == 0 ? 1 : size.y;

    GLFW.glfwDefaultWindowHints();

    GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0);
    GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? 1 : 0);

    windowID = GLFW.glfwCreateWindow(width, height, title, 0, 0);

    GLFW.glfwMakeContextCurrent(windowID);
    GLFW.glfwSwapInterval(0);

    if (windowID == 0) {

      logger.error("Window couldn't be created!");
      return;

    }

    selectWindow();

    GL.createCapabilities();

    if (visible) {
      GLFW.glfwShowWindow(windowID);
    }

    logger.info("Window '" + windowID + "' has been created");

  }

  /**
   * <p>Poll the events.</p>
   *
   * <p>This is required for basic window functionality. User input will be read here.</p>
   *
   * <p>Not calling this constantly might cause the
   * window to freeze and other unwanted behavior.</p>
   *
   * @since 0.0.3
   */
  public void pollEvents() {

    GLFW.glfwPollEvents();

  }

  /**
   * <p>Render to screen.</p>
   *
   * <p>Buffers will be swapped here. The previously drawn one will be shown and new
   * one prepared for drawing.</p>
   *
   * @since 0.0.3
   */
  public void render() {

    GLFW.glfwSwapBuffers(windowID);

  }

  /**
   * <p>Destroy the window properly. This should always used for closing the window. An exception
   * to this is if GLFW was uninitialized as it takes care of the open window's in it's context.</p>
   *
   * <p>Window is only closed if {@link #isOpen()} returns <code>TRUE</code>.</p>
   *
   * @return <code>TRUE</code> if the window was closed, <code>FALSE</code> if not.
   * @since 0.0.2
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
   * @since 0.0.2
   */
  public boolean isCloseRequested() {

    return isOpen() ? GLFW.glfwWindowShouldClose(windowID) : true;

  }

  /**
   * <p>Select window for drawing.</p>
   *
   * <p>This makes the OpenGL context of the window
   * the current one.</p>
   *
   * <p>The context is only changed if {@link #isOpen()} returns
   * <code>TRUE</code>.</p>
   *
   * @return Whether the window was selected.
   */
  public boolean selectWindow() {

    if (isOpen()) {

      GLFW.glfwMakeContextCurrent(windowID);

      return true;

    }

    return false;

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
   * @since 0.0.2
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
   * @since 0.0.2
   */
  public boolean setTitle(String title) {

    if (isOpen()) {

      if (title == null) title = "";

      this.title = title;

      GLFW.glfwSetWindowTitle(windowID, title);

      return true;

    }

    return false;

  }

  /**
   * <p>Gets the window's current title.</p>
   *
   * <p>The result may be invalid if the title was not set the proper way with
   * {@link #setTitle(String)} nor given on window creation. The previous known value
   * will be given if the window has been destroyed.</p>
   *
   * @return Title set with {{@link #setTitle(String)}} or <code>NULL</code> if none set.
   * @see #setTitle(String)
   * @since 0.0.2
   */
  public String getTitle() {

    return title;

  }

  /**
   * <p>Sets whether the window is visible or not.</p>
   *
   * <p>Visibility is only set if {@link #isOpen()} returns <code>TRUE</code>.</p>
   *
   * <p>Note that this doesn't mean minimizing. Nothing from the window is visible.</p>
   *
   * @param visible <code>TRUE</code> to make the window visible, <code>FALSE</code> to not.
   * @return Whether the visibility was set or not.
   * @see #isVisible()
   * @since 0.0.2
   */
  public boolean setVisibility(boolean visible) {

    if (isOpen()) {

      this.visible = visible;

      if (visible) {
        GLFW.glfwShowWindow(windowID);
      } else {
        GLFW.glfwHideWindow(windowID);
      }

      return true;

    }

    return false;

  }

  /**
   * <p>Whether the window is visible or not.</p>
   *
   * <p>The result may be invalid if the visibility was not set the proper way with
   * {@link #setVisibility(boolean)} nor given on window creation. <code>FALSE</code> will be given
   * if the window has been destroyed or hasn't been created.</p>
   *
   * @return <code>TRUE</code> if the window is visible, <code>FALSE</code> if not.
   * @see #setVisibility(boolean)
   * @since 0.0.2
   */
  public boolean isVisible() {

    return isOpen() && visible;

  }

  /**
   * <p>Whether the window is resizable or not.</p>
   *
   * <p>Resizability can't be changed.</p>
   *
   * <p>This is always either the value passed on window creation or <code>FALSE</code>
   * if {@link #isOpen()} returns <code>FALSE</code>.</p>
   *
   * @return <code>TRUE</code> if the window is resizable, <code>FALSE</code> if not.
   * @since 0.0.2
   */
  public boolean isResizable() {

    return isOpen() && resizable;

  }

  /**
   * <p>Set the window size.</p>
   *
   * <p>Value of <code>0</code> or below will be replaced with <code>1</code>.</p>
   *
   * <p>Size will only be changed if {@link #isOpen()} returns <code>TRUE</code>.</p>
   *
   * @param width Horizontal size of the window
   * @param height Vertical size of the window
   * @see #getSize() 
   * @return Whether the size was changed.
   * @since 0.0.4
   */
  public boolean setSize(int width, int height) {

    if (isOpen()) {

      width = width <= 0 ? 1 : width;
      height = height <= 0 ? 1 : height;

      this.width = width;
      this.height = height;

      GLFW.glfwSetWindowSize(windowID, width, height);

      return true;

    }

    return false;

  }

  /**
   * <p>Get the window's size.</p>
   *
   * <p>The value is only correct if {@link #pollEvents()} has been called
   * after size change. {@link #pollEvents()} is called on every engine update and
   * should not be called elsewhere.</p>
   *
   * <p>If {@link #isOpen()} doesn't return <code>TRUE</code>,
   * <code>NULL</code> will be returned.</p>
   *
   * @see #setSize(int, int)
   * @return The current window size or <code>NULL</code> if window is not open.
   * @since 0.0.4
   */
  public Vector2i getSize() {

    if (isOpen()) {

      IntBuffer width = BufferUtils.createIntBuffer(1);
      IntBuffer height = BufferUtils.createIntBuffer(1);

      GLFW.glfwGetWindowSize(windowID, width, height);

      return new Vector2i(width.get(), height.get());

    }

    return null;

  }

  /**
   * <p>Gets the window's ID. The ID can be used to execute GLFW functions but only
   * do so if you are familiar with Maingine and know what you're doing as it may
   * cause bugs.</p>
   *
   * <p>The ID will be <code>0</code> if the window isn't open and invalid
   * if the window wasn't managed properly.</p>
   *
   * @return The window's ID
   * @see #isOpen()
   * @since 0.0.2
   */
  public long getWindowID() {

    return windowID;

  }

}
