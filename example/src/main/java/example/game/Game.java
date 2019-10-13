package example.game;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.engine.gamestate.GameState;
import me.joosua.maingine.glfw.window.Window;

public class Game extends GameState {

  private Engine engine;
  private Window window;

  public Game(Engine engine, Window window) {

    this.engine = engine;
    this.window = window;

  }

  public void init() {

  }

  public void update(double delta) {

    window.setTitle(String.format("UPS: %s, FPS: %s", engine.getUps(), engine.getFps()));

  }

  public void render() {

  }

  public void unset() {

  }

}
