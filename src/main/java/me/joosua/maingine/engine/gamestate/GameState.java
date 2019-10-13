package me.joosua.maingine.engine.gamestate;

public abstract class GameState {

  /**
   * <p>Init is called when the game state is selected.</p>
   *
   * @since unreleased
   */
  public abstract void init();

  /**
   * <p>Update is called on every tick.</p>
   *
   * @param delta Time each update takes (Multiply time-related values with this).
   * @since unreleased
   */
  public abstract void update(double delta);

  /**
   * <p>Render is called before every screen buffer swap.</p>
   *
   * <p>All draw calls should be made here.</p>
   *
   * @since unreleased
   */
  public abstract void render();

  /**
   * <p>Unset is called when the game state is changed to another.</p>
   *
   * @since unreleased
   */
  public abstract void unset();

}
