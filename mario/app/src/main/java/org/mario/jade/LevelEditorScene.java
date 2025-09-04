package org.mario.jade;

import org.joml.Vector2f;
import org.joml.Vector4f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.mario.components.SpriteRenderer;

public class LevelEditorScene extends Scene {

  @Override
  public void init() {
    this.camera = new Camera(new Vector2f(-250,0));

    int xOffset = 10;
    int yOffset = 10;

    float totalWidth  = (float)(600 - xOffset * 2);
    float totalHeight = (float)(300 - yOffset * 2);
    float sizeX       = totalWidth / 100f;
    float sizeY       = totalHeight / 100f;
    float padding = 3;

    for (int x = 0; x < 100; ++x) {
      for (int y = 0; y < 100; ++y) {
        float xPos = xOffset + (x*sizeX) + (padding*x);
        float yPos = yOffset + (y*sizeY) + (padding*y);

        GameObject go = new GameObject(
          "Obj" + x + "" + y, 
          new Transform(new Vector2f(xPos, yPos),new Vector2f(sizeX, sizeY))
        );
        go.addComponent(new SpriteRenderer(new Vector4f(xPos / totalWidth,yPos/totalHeight, 1,1)));
        this.addGameObjectToScene(go); 
      }
    } 
  }

  @Override
  public void update(float dt) {
    System.out.println("FPS: " + (1f / dt));
    for (GameObject go : this.gameObjects)
      go.update(dt);
    this.renderer.render();
  }
}
