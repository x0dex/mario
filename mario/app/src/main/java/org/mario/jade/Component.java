package org.mario.jade;

public abstract class Component {
  public GameObject gameObject = null;
  public void start() { return; }
  public abstract void update(float dt);
}
