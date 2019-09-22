package com.example.ren.day03zuo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ren.day03zuo1.db.DatasBeanDao;
import com.example.ren.day03zuo1.p.Presenter;
import com.example.ren.day03zuo1.v.IView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IView{

    private RecyclerView mRecycler;
    private ArrayList<DatasBean> lists;
    private MyReAdapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRec();
        presenter = new Presenter(this);
        presenter.addbean();
    }

    private void initRec() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        lists = new ArrayList<>();
        adapter = new MyReAdapter(this,lists);
        mRecycler.setAdapter(adapter);

        adapter.setS(new MyReAdapter.OnClickListener() {
            @Override
            public void getposition(final int position, final Button btn) {

                DatasBean bean = lists.get(position);
                String s = btn.getText().toString();
                if(s.equals("关注")){
                    presenter.dbinsert(bean);
                    btn.setText("取消");
                }else if (s.equals("取消")){
                    presenter.deletebean(bean);
                    btn.setText("关注");
                }

            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
    }



    @Override
    public void insertBean(List<DatasBean> list) {
        lists.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


}
