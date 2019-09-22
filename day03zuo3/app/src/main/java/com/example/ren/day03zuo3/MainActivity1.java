package com.example.ren.day03zuo3;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {

    private ArrayList<Bean.BodyBean.ResultBean> list = new ArrayList<>();
    private RecyclerView mRecycler;
    private MyReAdapter adapter;
    private TabLayout mTab;
    private Toolbar mToolbar1;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        EventBus.getDefault().register(this);
        initView();
        initrec();
        initTab();
    }

    private void initTab() {
        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();
//        getSupportFragmentManager().beginTransaction().add(R.id.tab,fragmentA).add(R.id.tab,fragmentB).add(R.id.tab,fragmentC).commit();
        mTab.addTab(mTab.newTab().setText("介绍"));
        mTab.addTab(mTab.newTab().setText("课程"));
        mTab.addTab(mTab.newTab().setText("专题"));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        getSupportFragmentManager().beginTransaction().show(fragmentA).hide(fragmentB).hide(fragmentC).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().show(fragmentB).hide(fragmentA).hide(fragmentC).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().show(fragmentC).hide(fragmentB).hide(fragmentA).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initrec() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyReAdapter(this, list);
        mRecycler.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void Message(Bean.BodyBean.ResultBean resultBean) {
        list.add(resultBean);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mTab = (TabLayout) findViewById(R.id.tab);
        mToolbar1 = (Toolbar) findViewById(R.id.toolbar1);
    }


}
