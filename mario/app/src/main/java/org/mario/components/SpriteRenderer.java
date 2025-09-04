package org.mario.components;

import org.mario.jade.Component;

import org.joml.Vector4f;

public class SpriteRenderer extends Component {

  private Vector4f color;

  public SpriteRenderer(Vector4f color) {
    this.color = color;
  }

  @Override
  public void start() { System.out.println("I am starting"); }
  
  @Override
  public void update(float dt) { System.out.println("I am updating"); }

  public Vector4f getColor() { return this.color; }
}
