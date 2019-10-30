package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ren.wanandroid.R;

public class ProjectActivity extends AppCompatActivity {

    private WebView mWebProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        initView();
    }

    private void initView() {
        mWebProject = (WebView) findViewById(R.id.web_project);
        String link = getIntent().getStringExtra("link");
        mWebProject.loadUrl(link);
        mWebProject.setWebViewClient(new WebViewClient());
    }
}
