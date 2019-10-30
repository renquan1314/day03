package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ren.wanandroid.R;

public class LeadActivity extends AppCompatActivity {

    private WebView mWebLead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        initView();
    }

    private void initView() {
        mWebLead = (WebView) findViewById(R.id.web_lead);
        String link = getIntent().getStringExtra("link");
        mWebLead.loadUrl(link);
        mWebLead.setWebViewClient(new WebViewClient());
    }
}
