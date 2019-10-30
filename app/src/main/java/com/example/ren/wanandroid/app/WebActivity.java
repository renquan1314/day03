package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ren.wanandroid.R;

public class WebActivity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        String link = getIntent().getStringExtra("link");
        mWeb.loadUrl(link);
        mWeb.setWebViewClient(new WebViewClient());
    }
}
