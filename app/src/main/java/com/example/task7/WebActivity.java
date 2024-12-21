package com.example.task7;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {
    private WebView mWeb;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initData();
        WebSettings settings = mWeb.getSettings();
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);


            mWeb.setWebViewClient(new WebViewClient());
            mWeb.loadUrl(url);
    }

    private void initData() {
        Intent intent =getIntent();
        url=intent.getStringExtra("URL");

    }

    private void initView() {
        mWeb=findViewById(R.id.webview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mWeb.canGoBack()){
            mWeb.goBack();
        }
        else{
            finish();
        }
    }
}