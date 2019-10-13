package me.joosua.maingine.engine.gamestate;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameStateManager {

  private static final Logger logger = LogManager.getLogger(GameStateManager.class);

  private HashMap<String, GameState> gameStates = new HashMap<>();

  private GameState currentGameState;

  /**
   * <p>Update is called on every tick.</p>
   *
   * <p>If the current game state is <code>NULL</code>,
   * nothing will be done.</p>
   *
   * @param delta Time each update takes (multiply time-related values with this).
   * @since unreleased
   */
  public void update(double delta) {

    if (currentGameState != null) {
      currentGameState.update(delta);
    }

  }

  /**
   * <p>Render is called before every screen buffer swap.
   * All draw calls should be made here.</p>
   *
   * <p>If the current game state is <code>NULL</code>,
   * nothing will be done.</p>
   *
   * @since unreleased
   */
  public void render() {

    if (currentGameState != null) {
      currentGameState.render();
    }

  }

  /**
   * <p>Add a new game state.</p>
   *
   * <p>If the name is already used or the name is <code>NULL</code>,
   * the game state will not be added.</p>
   *
   * @param name An unique name
   * @param gameState The new game state
   * @return Whether the game state was added.
   * @see #removeGameState(String)
   * @since unreleased
   */
  public boolean addGameState(String name, GameState gameState) {

    if (name == null) {

      logger.error("GameState not added! GameState name can't be NULL.");
      return false;

    }

    if (gameStates.containsKey(name)) {

      logger.error("GameState not added! GameState '" + name + "' already exists.");
      return false;

    }

    gameStates.put(name, gameState);

    return true;

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
   * @see #addGameState(String, GameState) 
   * @since unreleased
   */
  public boolean removeGameState(String name) {

    if (!gameStates.containsKey(name)) {

      logger.error("Game state '" + name + "' can't be removed as it doesn't exists!");
      return false;

    }

    if (gameStates.get(name) == currentGameState) {
      selectGameState(null);
    }

    gameStates.remove(name);

    return true;

  }

  /**
   * <p>Get a game state by its name.</p>
   *
   * @param name Name of the game state wanted
   * @return The game state or <code>NULL</code> if not found
   * @see #getGameStateName(GameState)
   * @since unreleased
   */
  public GameState getGameState(String name) {

    return gameStates.get(name);

  }

  /**
   * <p>Get the name of a game state.</p>
   *
   * <p>If the same game state has been added multiple times,
   * the name of the first one found will be returned.</p>
   *
   * @param gameState The game state to the name of
   * @return The name of the game state given or <code>NULL</code> if not found.
   * @see #getGameState(String) 
   * @since unreleased
   */
  public String getGameStateName(GameState gameState) {

    for (Map.Entry<String, GameState> entry : gameStates.entrySet()) {

      if (entry.getValue() == gameState) {
        return entry.getKey();
      }

    }

    return null;

  }

  /**
   * <p>Select a game state.</p>
   *
   * <p>Unset of the previous game state will be called and
   * init of the new one.</p>
   *
   * <p>Set game state to <code>NULL</code> to use none.</p>
   *
   * <p>If the game state doesn't exists, nothing will be done
   * and <code>FALSE</code> will be returned.</p>
   *
   * @param name Name of the game state to be used.
   * @return Whether the game state was changed or not.
   * @see #getCurrentGameState() 
   * @since unreleased
   */
  public boolean selectGameState(String name) {

    if (name != null && !gameStates.containsKey(name)) {

      logger.error("Game state '" + name + "' can't be selected as it doesn't exists!");
      return false;

    }

    if (currentGameState != null) {
      currentGameState.unset();
    }

    if (name == null) {

      this.currentGameState = null;
      return true;

    }

    this.currentGameState = gameStates.get(name);

    if (currentGameState != null) {
      currentGameState.init();
    }

    return true;

  }

  /**
   * <p>Get the game state currently in use.</p>
   *
   * @return The current game state or <code>NULL</code> if no game state in use.
   * @see #selectGameState(String)
   * @since unreleased
   */
  public GameState getCurrentGameState() {

    return currentGameState;

  }

  /**
   * <p>Get all the game states and their names.</p>
   *
   * <p>DO NOT modify anything on the list to prevent
   * unexpected behaviour.</p>
   *
   * @return List of the game states
   */
  public final HashMap<String, GameState> getGameStates() {

    return gameStates;

  }

}
