package me.joosua.maingine.engine.gamestate;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameStateManager {

  private static final Logger logger = LogManager.getLogger(GameStateManager.class);

  private HashMap<String, GameState> gameStates = new HashMap<>();

  private GameState currentGameState;
  private String currentGameStateName;

  /**
   * <p>Add a new game state.</p>
   *
   * <p>If the name is already used or the name is <code>NULL</code>, the game state will
   * not be added.</p>
   *
   * @param name An unique name
   * @param gameState The new game state
   * @return Whether the game state was added.
   * @since unreleased
   */
  public boolean addGameState(String name, GameState gameState) {

    if (name != null) {

      if (!gameStates.containsKey(name)) {

        gameStates.put(name, gameState);

        return true;

      } else {

        logger.error("GameState not added! GameState '" + name + "' already exists.");

        return false;

      }

    } else {

      logger.error("GameState not added! GameState name can't be NULL.");

      return false;

    }

  }

  /**
   * <p>Remove game state by name.</p>
   *
   * <p>If the game state to be removed is the currently used one,
   * no game state will be running.</p>
   *
   * <p>If the game state is not found, <code>FALSE</code> will be returned.</p>
   *
   * @param name Name of the game state to be removed.
   * @return Whether the game state was removed or not.
   * @since unreleased
   */
  public boolean removeGameState(String name) {

    if (gameStates.containsKey(name)) {

      if (name.equals(currentGameStateName)) {

        selectGameState(null);
        currentGameState = null;

      }

      gameStates.remove(name);

      return true;

    } else {

      return false;

    }


  }

  /**
   * <p>Get a game state by its name.</p>
   *
   * @param name Name of the game state wanted
   * @return The game state or <code>NULL</code> if not found
   * @since unreleased
   */
  public GameState getGameState(String name) {

    return gameStates.get(name);

  }

  /**
   * <p>Select a game state.</p>
   *
   * <p>Unset of the previous game state will be called and
   * init of the new one.</p>
   *
   * <p>Set game state to <code>NULL</code> to use none.</p>
   *
   * @param name Name of the game state to be used.
   * @since unreleased
   */
  public void selectGameState(String name) {

    if (currentGameState != null) {
      currentGameState.unset();
    }

    this.currentGameStateName = name;
    this.currentGameState = gameStates.get(name);

    if (currentGameState != null) {
      currentGameState.init();
    }

  }

  /**
   * <p>Get the game state currently in use.</p>
   *
   * @return The current game state
   * @since unreleased
   */
  public GameState getCurrentGameState() {

    return currentGameState;

  }

  /**
   * <p>Get the name of the current game state.</p>
   *
   * <p><code>NULL</code> means that no game state is used.</p>
   *
   * @return The name of the current game state
   * @since unreleased
   */
  public String getCurrentGameStateName() {

    return currentGameStateName;

  }

  /**
   * <p>Update is called on every tick.</p>
   *
   * @param delta Time each update takes (Multiply time-related values with this).
   * @since unreleased
   */
  public void update(double delta) {

    if (currentGameState != null) {
      currentGameState.update(delta);
    }

  }

  /**
   * <p>Render is called before every screen buffer swap.</p>
   *
   * <p>All draw calls should be made here.</p>
   *
   * @since unreleased
   */
  public void render() {

    if (currentGameState != null) {
      currentGameState.render();
    }

  }

}
