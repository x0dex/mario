package org.mario.renderer;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_image_free;

public class Texture {
  private String filepath;
  private int texID;

  public Texture(String filepath) {
    this.filepath = filepath;

    texID = glGenTextures();
    glBindTexture(GL_TEXTURE_2D, texID);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S    ,  GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T    ,  GL_REPEAT);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    IntBuffer  w        = BufferUtils.createIntBuffer(1);
    IntBuffer  h        = BufferUtils.createIntBuffer(1);
    IntBuffer  channels = BufferUtils.createIntBuffer(1);
    ByteBuffer image    = stbi_load(filepath, w, h, channels, 0);

    if (image != null) {
      if (channels.get(0) == 3) {
        glTexImage2D(
          GL_TEXTURE_2D, 0     , GL_RGB, w.get(0), h.get(0), 
          0            , GL_RGB, GL_UNSIGNED_BYTE, image
        );
      }
      else if (channels.get(0) == 4) {
        glTexImage2D(
          GL_TEXTURE_2D, 0      , GL_RGBA, w.get(0), h.get(0), 
          0            , GL_RGBA,  GL_UNSIGNED_BYTE, image
        );
      }
      else
        assert false : "Error: (Texture) Unkown number of channels '" +
                        channels.get(0) + "'";
    }
    else
      assert false : "Error: (Texture) Could not load image '" + filepath + "'";
    
    stbi_image_free(image);
  }

  public void   bind() { glBindTexture(GL_TEXTURE_2D, texID); }
  public void unbind() { glBindTexture(GL_TEXTURE_2D,     0); }
}
