package me.joosua.maingine.objects;

import me.joosua.maingine.engine.gamestate.GameStateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

public class Object {

  private static final Logger logger = LogManager.getLogger(GameStateManager.class);

  public Object(float[] vertices, int[] indices) {

    this(vertices, indices, null);

  }

  public Object(float[] vertices, int[] indices, Vector3f fixedScale) {

    if (vertices.length % 3 != 0) {
      logger.warn("Object's vertices must dividable by 3. Not loaded!");
      return;
    }

    vertices = fixedScale(vertices, fixedScale);

  }

  private float[] fixedScale(float[] vertices, Vector3f scale) {

    if (scale != null && !scale.equals(1, 1, 1)) {

      for (int i = 0; i < vertices.length; i += 3) {

        vertices[i] *= scale.x;
        vertices[i + 1] *= scale.y;
        vertices[i + 2] *= scale.z;

      }

    }

    return vertices;

  }

}
