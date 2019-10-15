package me.joosua.maingine.maths;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 * <p>Transformation is universal class which
 * can be used for all objects.</p>
 *
 * <p>Transformation has objects position, rotation and scale.</p>
 *
 * @since unreleased
 */
public class Transformation {

  private Vector3f position;
  private Vector3f rotation;
  private Vector3f scale;

  private Matrix4f transformation;

  private boolean modified;

  /**
   * <p>Create transformation with defaults.</p>
   *
   * <p>Position and rotation will be all <code>0</code>s and
   * scale will be <code>1</code>s.</p>
   *
   * @see #Transformation(Vector3f, Vector3f, Vector3f)
   * @since unreleased
   */
  public Transformation() {

    position = new Vector3f(0, 0, 0);
    rotation = new Vector3f(0, 0, 0);
    scale = new Vector3f(1, 1, 1);

    calculateTransformation();

  }

  /**
   * <p>Create transformation with specific values already
   * set from the beginning.</p>
   *
   * @param position Position of vertices (object origin)
   * @param rotation Degrees rotated around X, Y and Z-axis.
   * @param scale Multiplier of all vertices
   * @since unreleased
   */
  public Transformation(Vector3f position, Vector3f rotation, Vector3f scale) {

    this.position = position;
    this.rotation = rotation;
    this.scale = scale;

    calculateTransformation();

  }

  private void calculateTransformation() {



  }

  /**
   * <p>Get position of transformation.</p>
   *
   * @return Position
   * @since unreleased
   */
  public Vector3f getPosition() {

    return position;

  }

  /**
   * <p>Get rotation of transformation.</p>
   *
   * <p>X, Y and Z components of the vector will be
   * the rotation axis.</p>
   *
   * @return Rotation
   * @since unreleased
   */
  public Vector3f getRotation() {

    return position;

  }

  /**
   * <p>Get scale of transformation.</p>
   *
   * @return Scale
   * @since unreleased
   */
  public Vector3f getScale() {

    return scale;

  }

}
