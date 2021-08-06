package com.androstock.webviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ProgressBar mProgressBar;
    WebView mWebView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "https://www.amazon.in/";

        mProgressBar = findViewById(R.id.progress);
        mWebView = findViewById(R.id.webview);

        mProgressBar.setMax(100);

        mWebView.setWebViewClient(new HelpClient());


        mWebView.setWebChromeClient(new WebChromeClient() {
                                        @Override
                                        public void onProgressChanged(WebView view, int newProgress) {
                                            mProgressBar.setProgress(newProgress);
                                            setTitle("Loading...");
                                            if (newProgress == 100) {
                                                setTitle("");
                                                mProgressBar.setProgress(0);
                                                mProgressBar.setVisibility(View.GONE);
                                            }
                                            super.onProgressChanged(view, newProgress);
                                        }
                                    }


        );

        WebSettings webSettings = mWebView.getSettings();

        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(url);

    }

    private class HelpClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}