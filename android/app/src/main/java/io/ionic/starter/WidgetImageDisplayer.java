package io.ionic.starter;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONArray;
import org.json.JSONObject;

public class WidgetImageDisplayer {

  private static int currentIndex = 0;

  public static void showNext(Context context) {
    ImageCache cache = ImageCache.getInstance();
    Log.d("MyWidget", "In ShowNext");
    if (!cache.isLoaded()) {
      Log.d("MyWidget", "Imágenes no cargadas todavía");
      return;
    }

    JSONArray entries = cache.getEntries();
    Bitmap bitmap = cache.getBitmaps().get(currentIndex);

    try {
      JSONObject entry = entries.getJSONObject(currentIndex);
      String text = entry.optString("description", "Sin texto");

      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
      views.setTextViewText(R.id.widget_text, text);

      if (bitmap != null) {
        views.setImageViewBitmap(R.id.widget_image, bitmap);
      } else {
        Log.d("MyWidget", "Is null image");
        views.setImageViewResource(R.id.widget_image, R.drawable.splash);
      }

      AppWidgetManager manager = AppWidgetManager.getInstance(context);
      ComponentName widget = new ComponentName(context, WidgetProvider.class);
      manager.updateAppWidget(widget, views);

      currentIndex = (currentIndex + 1) % entries.length();

    } catch (Exception e) {
      Log.e("MyWidget", "Error mostrando imagen", e);
    }
  }
}
