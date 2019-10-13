package example.game;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.engine.gamestate.GameState;

public class Game extends GameState {

  private Engine engine;

  public Game(Engine engine) {

    this.engine = engine;

  }

  public void init() {

  }

  public void update(double delta) {

    System.out.println(String.format("UPS: %s, FPS: %s", engine.getUps(), engine.getFps()));

  }

  public void render() {

  }

  public void unset() {

  }

}
