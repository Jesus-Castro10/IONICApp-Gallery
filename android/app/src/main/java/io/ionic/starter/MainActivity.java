package io.ionic.starter;

import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

  @Override
  public void onStart() {
    super.onStart();

    bridge.getWebView().addJavascriptInterface(new Object() {
      @JavascriptInterface
      public void triggerWidgetDownload() {
        Intent intent = new Intent("io.ionic.starter.WIDGET_DOWNLOAD_AND_CACHE");
        intent.setPackage(getApplicationContext().getPackageName());
        getApplicationContext().sendBroadcast(intent);
      }
    }, "AndroidWidgetBridge");
  }
}
