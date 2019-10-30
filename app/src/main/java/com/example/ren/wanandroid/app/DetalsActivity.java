package com.example.ren.wanandroid.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyViewTabAdapter;
import com.example.ren.wanandroid.base.BaseActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.TixiBean;
import com.example.ren.wanandroid.fragments.SystemFragment;
import com.example.ren.wanandroid.presenter.DetalsPresenter;
import com.example.ren.wanandroid.view.DetalsView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetalsActivity extends BaseActivity<DetalsPresenter> implements DetalsView {

    private Toolbar mDetalsToolbar;
    private TabLayout mDetalsTab;
    private ViewPager mViewpager;
    private int _position;
    private MyViewTabAdapter adapter;
    private List<TixiBean.DataBean> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detals);
        initView();
    }

    @Override
    protected DetalsPresenter initPresenter() {
        return new DetalsPresenter();
    }


    private void initView() {
        mDetalsToolbar = (Toolbar) findViewById(R.id.detals_toolbar);
        mDetalsTab = (TabLayout) findViewById(R.id.detals_tab);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

        list = (List<TixiBean.DataBean>) getIntent().getSerializableExtra("list");
        int postition = getIntent().getIntExtra("position", 1);
        _position =  postition;

        mDetalsToolbar.setTitle("                  "+list.get(_position).getName());
        List<TixiBean.DataBean.ChildrenBean> children = this.list.get(_position).getChildren();
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < children.size() ; i++) {
            strings.add(children.get(i).getName());
        }
        for (int i = 0; i < strings.size(); i++) {
            fragments.add(SystemFragment.getInstance(list.get(_position).getChildren().get(i).getId()));
        }
        adapter = new MyViewTabAdapter(getSupportFragmentManager(), strings, fragments);
        mViewpager.setAdapter(adapter);
        mDetalsTab.setupWithViewPager(mViewpager);
    }


}
