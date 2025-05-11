package io.ionic.starter;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class WidgetUpdateReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    Log.d("MyWidget", "AlarmManager disparó actualización");

    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
    ComponentName widget = new ComponentName(context, WidgetProvider.class);
    int[] widgetIds = appWidgetManager.getAppWidgetIds(widget);

    for (int widgetId : widgetIds) {
      new WidgetProvider().updateWidget(context, appWidgetManager, widgetId);
    }
  }
}

