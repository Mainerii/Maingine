package example.game;

import me.joosua.maingine.engine.Engine;
import me.joosua.maingine.engine.gamestate.GameState;
import me.joosua.maingine.glfw.window.Window;
import org.lwjgl.opengl.GL11;

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

    GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

  }

  public void unset() {

  }

}
