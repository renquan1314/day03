package com.example.ren.day03zuo2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ren.day03zuo2.p.Presenter;
import com.example.ren.day03zuo2.v.Iview;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Iview{

    private RecyclerView mRecycler;
    private ArrayList<Bean.ResultsBean> list;
    private MyReAdapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRec();
        presenter = new Presenter(this);
        presenter.addBean();
    }

    private void initRec() {
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        list = new ArrayList<>();
        adapter = new MyReAdapter(this, list);
        mRecycler.setAdapter(adapter);

        adapter.setS(new MyReAdapter.OnClickListener() {
            @Override
            public void getposition(int position) {
//                Intent intent = new Intent(MainActivity.this, PagerFragment.class);
//                intent.putExtra("position",position);
//                intent.putExtra("list",list);
//                startActivity(intent);
                EventBus.getDefault().post(new Content(position,list));
                FragmentManager manager = getSupportFragmentManager();
                PagerFragment pagerFragment = new PagerFragment();
                manager.beginTransaction().add(R.id.fram,pagerFragment).commit();
            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }

    @Override
    public void insertBean(List<Bean.ResultsBean> re) {
        list.addAll(re);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
