package com.example.ren.wanandroid.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.base.BaseActivity;
import com.example.ren.wanandroid.fragments.HomeFragment;
import com.example.ren.wanandroid.presenter.MainPresenter;
import com.example.ren.wanandroid.view.Mainview;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class MainActivity extends BaseActivity<MainPresenter> implements Mainview {

    private Toolbar mToolbar;
    private FrameLayout mFrame;
    private NavigationView mNav;
    private DrawerLayout mDrawer;
    /**
     * 首页
     */
    public TextView mToolbarTv;
    private RelativeLayout mRel;
    public FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private AppBarLayout mAppbar;
    private TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "4562daea85", true, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);

        initView();
        init();
        initListener();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }


    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void initListener() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawer.closeDrawer(Gravity.LEFT);
                switch (item.getItemId()) {
                    case R.id.menu1:
                        item.setChecked(true);
                        break;
                    case R.id.menu2:
                        mNav.setCheckedItem(R.id.menu2);
                        String s = id.getText().toString();
                        if (s.equals("")){
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivityForResult(intent,1);
                            Toast.makeText(MainActivity.this, "请先登录~~", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "收藏页面", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.menu3:
                        mNav.setCheckedItem(R.id.menu3);
                        break;
                    case R.id.menu4:
                        mNav.setCheckedItem(R.id.menu4);
                        break;
                }
                return false;
            }
        });


        View view = mNav.getHeaderView(0);
        ImageView imgae = view.findViewById(R.id.nav_head);
        id = view.findViewById(R.id.head_tv);
        imgae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent,1);
            }
        });

        mDrawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mRel.setX(drawerView.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        });

    }

    private void init() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.app_name, R.string.app_name);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNav.setItemIconTintList(null);


        homeFragment = new HomeFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (!homeFragment.isAdded()) {
            fragmentTransaction.add(R.id.frame, homeFragment);
        }
        fragmentTransaction.commit();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mNav = (NavigationView) findViewById(R.id.nav);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mToolbarTv = (TextView) findViewById(R.id.toolbar_tv);
        mRel = (RelativeLayout) findViewById(R.id.rel);
        mAppbar = (AppBarLayout) findViewById(R.id.appbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
//                Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
//                startActivity(intent);
                break;
            case R.id.option_2:
                Snackbar.make(mDrawer, "飘过~~~~~", Snackbar.LENGTH_SHORT)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==2){
            String call = data.getStringExtra("call");
            id.setText(call);
        }
    }
}



