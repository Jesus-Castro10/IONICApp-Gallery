package io.ionic.starter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WidgetProvider extends AppWidgetProvider {

  private final Handler handler = new Handler();
  private Runnable updateRunnable;

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    Log.d("MyWidget", "In update");

    updateRunnable = new Runnable() {
      @Override
      public void run() {
        for (int appWidgetId : appWidgetIds) {
          updateWidget(context, appWidgetManager, appWidgetId);
        }
        handler.postDelayed(this, 5000); // cada 5 segundos
      }
    };

    handler.post(updateRunnable);
  }

  public void updateWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
    String json = PreferencesBridge.getValue(context, "widget_entry", null);
    Log.d("MyWidget", "In updateWidget");
    Log.d("MyWidget", "JSON: " + json);

    if (json != null) {
      try {
        JSONArray array = new JSONArray(json);
        if (array.length() == 0) return;

        int currentIndex = PreferencesBridge.getInt(context, "current_index", 0);
        if (currentIndex >= array.length()) currentIndex = 0;

        JSONObject obj = array.getJSONObject(currentIndex);
        String text = obj.optString("text", "Sin texto");
        String imageUrl = obj.optString("image", "");

        PreferencesBridge.setInt(context, "current_index", currentIndex + 1);

        if (!imageUrl.isEmpty()) {
          new Thread(() -> {
            Bitmap bitmap = downloadImage(imageUrl);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
            views.setTextViewText(R.id.widget_text, text);

            if (bitmap != null) {
              views.setImageViewBitmap(R.id.widget_image, bitmap);
            } else {
              //views.setImageViewResource(R.id.widget_image, R.drawable.default_image);
              Log.e("MyWidget", "Bitmap nulo");
            }

            appWidgetManager.updateAppWidget(appWidgetId, views);
          }).start();
        }

      } catch (Exception e) {
        Log.e("MyWidget", "Error parseando JSON", e);
      }
    }
  }

  private Bitmap downloadImage(String urlString) {
    try {
      HttpURLConnection connection = null;
      try {
        URL url = new URL(urlString);
        connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
      }catch (Exception e){
        Log.e("MyWidget", "Error de conexion " + e);
      }


      int responseCode = connection.getResponseCode();
      Log.d("MyWidget", "HTTP response code: " + responseCode);

      InputStream input = connection.getInputStream();
      return BitmapFactory.decodeStream(input);
    } catch (Exception e) {
      Log.e("MyWidget", "Error cargando imagen", e);
      return null;
    }
  }

  @Override
  public void onEnabled(Context context) {
    Log.d("MyWidget", "Widget activado");

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, WidgetUpdateReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

    alarmManager.setRepeating(
      AlarmManager.RTC_WAKEUP,
      System.currentTimeMillis(),
      5000, // cada 5 segundos
      pendingIntent
    );
  }

  @Override
  public void onDisabled(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, WidgetUpdateReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

    alarmManager.cancel(pendingIntent);
  }

}
