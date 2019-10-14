package me.joosua.maingine.engine.gamestate;

public abstract class GameState {

  /**
   * <p>Init is called when the game state is selected.</p>
   *
   * @since 0.0.4
   */
  public abstract void init();

  /**
   * <p>Update is called on every tick.</p>
   *
   * @param delta Time each update takes (Multiply time-related values with this).
   * @since 0.0.4
   */
  public abstract void update(double delta);

  /**
   * <p>Render is called before every screen buffer swap.</p>
   *
   * <p>All draw calls should be made here.</p>
   *
   * @since 0.0.4
   */
  public abstract void render();

  /**
   * <p>Unset is called when the game state is changed to another.</p>
   *
   * @since 0.0.4
   */
  public abstract void unset();

}
