package org.github.traffic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class TrafficActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView view = new WebView(this);
        view.getSettings().setJavaScriptEnabled(true);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        final Activity activity = this;
        view.setWebChromeClient(new WebChromeClient() {
          public void onProgressChanged(WebView view, int progress) {
            // Activities and WebViews measure progress with different scales.
            // The progress meter will automatically disappear when we reach 100%
            activity.setProgress(progress * 1000);
          }
        });
        view.setWebViewClient(new WebViewClient() {
          public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(activity, "Error: " + description, Toast.LENGTH_SHORT).show();
          }
        });

        view.loadUrl("file:///android_asset/traffic.html");
        setContentView(view);
    }
}