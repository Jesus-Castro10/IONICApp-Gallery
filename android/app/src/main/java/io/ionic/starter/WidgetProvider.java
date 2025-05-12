package io.ionic.starter;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;

public class WidgetProvider extends AppWidgetProvider {

  private final Handler handler = new Handler();
  private final Runnable loopRunnable = new Runnable() {
    @Override
    public void run() {
      WidgetImageDisplayer.showNext(context);
      handler.postDelayed(this, 5000);
    }
  };

  private static Context context;

  @Override
  public void onUpdate(Context ctx, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    Log.d("MyWidget", "onUpdate llamado");
    context = ctx;

    new Thread(() -> {
      try {
        String json = PreferencesBridge.getValue(ctx, "widget_entries", null);
        JSONArray array = new JSONArray(json);
        ArrayList<Bitmap> bitmaps = ImageDownloader.getAllBitmapsFromJSONArray(array);

        ImageCache.getInstance().setEntries(array);
        ImageCache.getInstance().setBitmaps(bitmaps);

        Log.d("MyWidget", "Imágenes descargadas y cacheadas");

        handler.post(loopRunnable);

      } catch (Exception e) {
        Log.e("MyWidget", "Error inicializando widget", e);
      }
    }).start();
  }
}
