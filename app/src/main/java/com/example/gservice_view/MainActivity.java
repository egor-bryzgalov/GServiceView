package com.example.gservice_view;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

     private final String Url = "http://95.31.244.137:10002/GLS37";
     private final WebViewClient webViewClient = new WebViewClient() {

         @SuppressWarnings("deprecation")
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
             view.loadUrl(url);
             return true;
         }

         @TargetApi(Build.VERSION_CODES.N)
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
             view.loadUrl(request.getUrl().toString());
             return true;
         }

         @Override
         public void onPageFinished(WebView view, String url)
         {
             view.getSettings().setJavaScriptEnabled(true);
             view.setWebChromeClient(new WebChromeClient());
             try {
                 view.loadUrl("javascript:(function() { "
                            + this.readFile("script.js")
                         +"})()");
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

         private String readFile(String fileName) throws IOException {
             InputStream stream = getAssets().open(fileName);
             int size = stream.available();
             byte[] buffer = new byte[size];
             stream.read(buffer);
             stream.close();
             return new String(buffer);
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);

        webView.loadUrl(this.Url);
        webView.setWebViewClient(this.webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);
        getSupportActionBar().hide();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}