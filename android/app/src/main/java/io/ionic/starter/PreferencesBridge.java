package io.ionic.starter;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesBridge {
  public static String getValue(Context context, String key, String defaultValue) {
    SharedPreferences preferences = context.getSharedPreferences("CapacitorStorage", Context.MODE_PRIVATE);
    return preferences.getString(key, defaultValue);
  }

  public static int getInt(Context context, String key, int defaultValue) {
    SharedPreferences preferences = context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
    return preferences.getInt(key, defaultValue);
  }

  public static void setInt(Context context, String key, int value) {
    SharedPreferences preferences = context.getSharedPreferences("WidgetPrefs", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt(key, value);
    editor.apply();
  }
}
