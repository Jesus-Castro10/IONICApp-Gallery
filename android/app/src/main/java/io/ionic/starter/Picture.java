package io.ionic.starter;

import android.graphics.Bitmap;

public class Picture {
  private String description;
  private Bitmap image;

  public Picture() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Bitmap getImage() {
    return image;
  }

  public void setImage(Bitmap image) {
    this.image = image;
  }
}
