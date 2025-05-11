package io.ionic.starter;

import android.graphics.Bitmap;
import org.json.JSONArray;

import java.util.ArrayList;

public class ImageCache {
  private static ImageCache instance;
  private ArrayList<Bitmap> bitmaps;
  private JSONArray entries;

  private ImageCache() {
    bitmaps = new ArrayList<>();
  }

  public static synchronized ImageCache getInstance() {
    if (instance == null) {
      instance = new ImageCache();
    }
    return instance;
  }

  public void setBitmaps(ArrayList<Bitmap> bitmaps) {
    this.bitmaps = bitmaps;
  }

  public ArrayList<Bitmap> getBitmaps() {
    return bitmaps;
  }

  public void setEntries(JSONArray entries) {
    this.entries = entries;
  }

  public JSONArray getEntries() {
    return entries;
  }

  public boolean isLoaded() {
    return entries != null && bitmaps != null && bitmaps.size() == entries.length();
  }
}
