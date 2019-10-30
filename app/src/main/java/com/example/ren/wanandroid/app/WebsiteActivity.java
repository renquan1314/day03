package com.example.ren.wanandroid.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.utils.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebsiteActivity extends AppCompatActivity {

    @BindView(R.id.flow)
    FlowLayout mFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        ButterKnife.bind(this);


    }
}
