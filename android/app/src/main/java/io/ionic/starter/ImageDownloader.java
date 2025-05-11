package io.ionic.starter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownloader {

  public static ArrayList<Bitmap> getAllBitmapsFromJSONArray(JSONArray array) {
    ArrayList<Bitmap> bitmapList = new ArrayList<>();

    for (int i = 0; i < array.length(); i++) {
      try {
        JSONObject obj = array.getJSONObject(i);
        String imageUrl = obj.optString("image", "");

        if (!imageUrl.isEmpty()) {
          Bitmap bitmap = downloadImage(imageUrl);
          bitmapList.add(bitmap);
        } else {
          bitmapList.add(null);
        }

      } catch (Exception e) {
        Log.e("ImageDownloader", "Error procesando JSON en índice " + i, e);
        bitmapList.add(null);
      }
    }

    return bitmapList;
  }

  private static Bitmap downloadImage(String urlString) {
    try {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.connect();
      InputStream input = connection.getInputStream();
      return BitmapFactory.decodeStream(input);
    } catch (Exception e) {
      Log.e("ImageDownloader", "Error descargando imagen: " + urlString, e);
      return null;
    }
  }
}
