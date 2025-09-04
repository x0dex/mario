package org.mario.jade;

import org.mario.util.Time;
import org.mario.jade.LevelEditorScene;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;

public class Window {
  private String  title;
  private int     w,h;
  private long    glfwWindow;
  public  float   r,g,b,a;
  private boolean fadeToBlack = false;

  private static Window window = null;

  private static Scene currentScene;

  private Window() {
    this.w = 1920;
    this.h = 1080;
    this.title = "Mario";
    a = 1f;
  }

  public static Window get() {
    if (Window.window == null)
      Window.window = new Window();
    return Window.window; 
  }

  public static void changeScene(int newScene) {
    switch(newScene) {
      case 0:
        currentScene = new LevelEditorScene();
        currentScene.init();
        currentScene.start();
        break;
      case 1:
        currentScene = new LevelScene();
        currentScene.init();
        currentScene.start();
        break;
      default:
        assert false : "Unkown scene '" + newScene + "'";
        break;
    }
  }

  public static Scene getScene() {
    return get().currentScene;
  }

  public void run() {
    System.out.println("Hello LWGJL " + Version.getVersion() + "!");
    init();
    loop();

    glfwFreeCallbacks(glfwWindow);
    glfwDestroyWindow(glfwWindow);

    glfwTerminate();
    glfwSetErrorCallback(null).free();
  }

  public void init() {
    GLFWErrorCallback.createPrint(System.err).set();

    if (!glfwInit())
      throw new IllegalStateException("Unable to initialize GLFW.");

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE,   GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE,  GLFW_TRUE);
    glfwWindowHint(GLFW_VISIBLE,    GLFW_TRUE);

    glfwWindow = glfwCreateWindow(this.w, this.h, this.title, NULL, NULL);
    if (glfwWindow == NULL)
      throw new IllegalStateException("Failed to create the GLFW window.");

    glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
    glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
    glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
    glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);
    

    glfwMakeContextCurrent(glfwWindow);
    glfwSwapInterval(1);
    glfwShowWindow(glfwWindow);
    GL.createCapabilities();
    Window.changeScene(0);
  }

  public void loop() {
    float beginTime = Time.getTime();
    float endTime;
    float dt = -1f;

    while (!glfwWindowShouldClose(glfwWindow)) {
      glfwPollEvents();
      glClearColor(r,g,b,a);
      glClear(GL_COLOR_BUFFER_BIT);
      if (dt >= 0) currentScene.update(dt);
      glfwSwapBuffers(glfwWindow);
      endTime = Time.getTime();
      dt = endTime - beginTime;
      beginTime = endTime;
    }
  }
}
