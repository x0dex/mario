package org.mario.jade;

import java.util.List;
import java.util.ArrayList;

import org.mario.renderer.Renderer;

public abstract class Scene {
  protected Camera           camera;
  private   boolean          isRunning   = false;
  protected List<GameObject> gameObjects = new ArrayList<>();
  protected Renderer renderer            = new Renderer();

  public void start() {
    for (GameObject go : gameObjects) {
      go.start();
      this.renderer.add(go);
    }
    
    isRunning = true;
  }

  public void addGameObjectToScene(GameObject go) {
    if (!isRunning) {
      gameObjects.add(go);
    }
    else {
      gameObjects.add(go);
      go.start();
      this.renderer.add(go);
    }
  }

  public Camera camera() { return this.camera; }

  public abstract void update(float dt);
  public          void init() {return;}
}
