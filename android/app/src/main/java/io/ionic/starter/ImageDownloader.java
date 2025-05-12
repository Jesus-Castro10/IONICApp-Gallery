package io.ionic.starter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;

public class ImageDownloader {

  public static ArrayList<Bitmap> getAllBitmapsFromJSONArray(JSONArray array) {
    ArrayList<Bitmap> bitmapList = new ArrayList<>();

    for (int i = 0; i < array.length(); i++) {
      try {
        JSONObject obj = array.getJSONObject(i);
        String imageUrl = obj.optString("url", "");
        if (!imageUrl.isEmpty()) {
          Bitmap bitmap = downloadImage(imageUrl);
          bitmapList.add(bitmap);
        } else {
          bitmapList.add(null);
        }

      } catch (Exception e) {
        Log.e("MyWidget", "Error procesando JSON en índice " + i, e);
        bitmapList.add(null);
      }
    }

    return bitmapList;
  }

  private static Bitmap downloadImage(String urlString) {
    try {
      URL url = new URL(urlString);
      InetAddress address = InetAddress.getByName("mohtjinmlocgdsipupju.supabase.co");
      Log.d("MyWidget", "IP: " + address.getHostAddress());
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setDoInput(true);
      connection.connect();
      InputStream input = connection.getInputStream();
      Bitmap original = BitmapFactory.decodeStream(input);

      if (original == null) {
        Log.e("MyWidget", "La imagen no se pudo decodificar");
        return null;
      }

      // Redimensionar a máximo 400x200 para widget 4x2
      int maxWidth = 400;
      int maxHeight = 200;

      int originalWidth = original.getWidth();
      int originalHeight = original.getHeight();

      if (originalWidth > maxWidth || originalHeight > maxHeight) {
        float scale = Math.min(
          (float) maxWidth / originalWidth,
          (float) maxHeight / originalHeight
        );

        int newWidth = Math.round(originalWidth * scale);
        int newHeight = Math.round(originalHeight * scale);

        Bitmap resized = Bitmap.createScaledBitmap(original, newWidth, newHeight, true);
        Log.d("MyWidget", "Imagen redimensionada: " + newWidth + "x" + newHeight);
        Log.d("MyWidget" , "Bytes " + resized.getByteCount());
        return resized;
      }
    Log.d("MyWidget" , "Bytes " + original);
      return original;
    } catch (Exception e) {
      Log.e("MyWidget", "Error descargando imagen: " + urlString + " - " + e.getMessage());
      return null;
    }
  }
}
