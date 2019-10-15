package me.joosua.maingine.objects.premade;

import me.joosua.maingine.objects.Object;
import org.joml.Vector3f;

public class Rectangle extends Object {

  public static final float[] vertices = {
      -1f, -1f, 0f,   // Top left corner
      1f, -1f, 0f,    // Top right corner
      -1f, 1f, 0f,    // Bottom left corner
      1f, 1f, 0f,     // Bottom right corner
  };

  public static final int[] indices = {
      0, 1, 2,
      2, 3, 1,
  };

  public Rectangle() {

    super(vertices, indices);

  }

  public Rectangle(Vector3f fixedScale) {

    super(vertices, indices, fixedScale);

  }

}
