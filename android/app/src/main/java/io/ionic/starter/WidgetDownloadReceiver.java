package io.ionic.starter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class WidgetDownloadReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    String json = PreferencesBridge.getValue(context, "widget_entry", null);
    if (json == null || json.isEmpty()) {
      Log.e("WidgetDownloadReceiver", "No hay JSON guardado");
      return;
    }

    new Thread(() -> {
      try {
        JSONArray array = new JSONArray(json);
        ArrayList<Bitmap> bitmaps = ImageDownloader.getAllBitmapsFromJSONArray(array);

        ImageCache.getInstance().setEntries(array);
        ImageCache.getInstance().setBitmaps(bitmaps);

        Log.d("WidgetDownloadReceiver", "Imágenes cacheadas desde Ionic");

      } catch (Exception e) {
        Log.e("WidgetDownloadReceiver", "Error cacheando imágenes", e);
      }
    }).start();
  }
}

