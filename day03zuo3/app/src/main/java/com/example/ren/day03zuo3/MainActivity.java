package com.example.ren.day03zuo3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ren.day03zuo3.p.Presenter;
import com.example.ren.day03zuo3.v.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private ArrayList<Bean.BodyBean.ResultBean> list;
    private MyReAdapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initrec();
        presenter = new Presenter(this);
        presenter.insertbean();
    }

    private void initrec() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
        adapter = new MyReAdapter(this,list);
        mRecycler.setAdapter(adapter);

        adapter.setS(new MyReAdapter.OnClickListener() {
            @Override
            public void getPosition(int position) {
                Bean.BodyBean.ResultBean bean = list.get(position);
                EventBus.getDefault().postSticky(bean);
                startActivity(new Intent(MainActivity.this,MainActivity1.class));
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mToolbar.setTitle("");
        mToolbar.setOverflowIcon(getDrawable(R.drawable.avd_hide_password_1));
        setSupportActionBar(mToolbar);
    }

    @Override
    public void insertBean(List<Bean.BodyBean.ResultBean> re) {
        list.addAll(re);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(1, 1, 1, "1");
//        item.setShowAsAction();
        return super.onCreateOptionsMenu(menu);
    }
}
