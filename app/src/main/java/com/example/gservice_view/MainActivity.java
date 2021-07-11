package com.example.gservice_view;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);

        webView.loadUrl(this.Url);
        webView.setWebViewClient(this.webViewClient);
        webView.getSettings().setJavaScriptEnabled(true);

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
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}