package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ren.wanandroid.R;

public class SystemActivity extends AppCompatActivity {

    private WebView mSystemWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        initView();
    }

    private void initView() {
        mSystemWeb = (WebView) findViewById(R.id.system_web);
        String link = getIntent().getStringExtra("link");
        mSystemWeb.loadUrl(link);
        mSystemWeb.setWebViewClient(new WebViewClient());
    }
}
