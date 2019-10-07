package me.joosua.maingine.settings;

import me.joosua.maingine.glfw.window.Window;

/**
 * <p>WindowSettings is a class for storing all the window's settings.</p>
 *
 * <p>Nothing here affects any window immediately but the values can be passed
 * for {@link me.joosua.maingine.glfw.window.Window Window} when creating one.</p>
 *
 * @since unreleased
 */
public class WindowSettings {

  private String title;

  private boolean visible;

  // What it does and what it defaults to.

  // can it be changed

  // Exceptions

  /**
   * <p>Sets the window's title. By default the title is empty.</p>
   *
   * <p>Title can be changed after creating the window
   * with {@link Window#setTitle(String)}.</p>
   *
   * <p><code>NULL</code> is treated as an empty string. Whether the
   * title is visible or where it's located, depends on the system.</p>
   *
   * @param title The title to be used.
   * @see #getTitle()
   * @see Window#setTitle(String)
   * @since unreleased
   */
  public void setTitle(String title) {

    this.title = title;

  }

  /**
   * <p>Gets the window's title set with {@link #setTitle(String)}. By default the
   * title is empty.</p>
   *
   * <p>Current window title can be gotten with {@link Window#getTitle()}</p>
   *
   * @return Title set with {{@link #setTitle(String)}} or empty if none (<code>NULL</code>) set.
   * @see #setTitle(String)
   * @see Window#getTitle()
   * @since unreleased
   */
  public String getTitle() {

    return title == null ? "" : title;

  }

  /**
   * <p>Whether the window is visible or not. By default the window is not visible.</p>
   *
   * <p>Visibility can be changed after creating the window
   * with {@link Window#setVisibility(boolean)}.</p>
   *
   * <p>Note that this doesn't mean minimizing. Nothing from the window is visible.</p>
   *
   * @param visible <code>TRUE</code> to make the window visible, <code>FALSE</code> to not.
   * @see #isVisible() 
   * @see Window#setVisibility(boolean)
   * @since unreleased
   */
  public void setVisibility(boolean visible) {

    this.visible = visible;

  }

  /**
   * <p>Gets the window's visibility set with {@link #setVisibility(boolean)}. By default the
   * visibility is <code>FALSE</code>.</p>
   *
   * <p>Current window title can be gotten with {@link Window#isVisible()}</p>
   *
   * @return Value set with {{@link #setVisibility(boolean)}} or (<code>FALSE</code>) by default.
   * @see #setVisibility(boolean)
   * @see Window#isVisible()
   * @since unreleased
   */
  public boolean isVisible() {

    return visible;

  }

}
