package me.joosua.maingine.settings;

public class WindowSettings {

  private String title;

  private boolean resizable;
  private boolean visible;

  private int width, height;

  /**
   * <p>Sets the window title.</p>
   *
   * <p>Whether the title is visible or where it's located, depends on the system.</p>
   *
   * @param title Title to be used
   * @see #getTitle()
   */
  public void setTitle(String title) {

    this.title = title;

  }

  /**
   * <p>Gets the window title.</p>
   *
   * @return Title set with {{@link #setTitle(String)}} or empty if none (<code>NULL</code>) set.
   * @see #setTitle(String)
   */
  public String getTitle() {

    return title == null ? "" : title;

  }

  /**
   * <p>Set whetever the window will be resizable or not. </p>
   *
   * <p>Thi is set to <code>FALSE</code> by default but it's recommended to use resizable
   * windows for best compatibility with different kinds of systems and hardware. Some systems
   * might allow forcing fullscreen even if this is <code>TRUE</code>.</p>
   *
   * @param resizable <code>TRUE</code> if the window will be resizable, <code>FALSE</code> if not.
   * @see #isResizable()
   */
  public void setResizable(boolean resizable) {

    this.resizable = resizable;

  }

  /**
   * <p>Whether the window will be resizable or not.</p>
   *
   * <p>This is set to <code>FALSE</code> by default.</p>
   *
   * @return <code>TRUE</code> if the window will be resizable, <code>FALSE</code> if not.
   * @see #setResizable(boolean)
   */
  public boolean isResizable() {

    return resizable;

  }

}
