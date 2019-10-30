package com.example.ren.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyTencentAdapter;
import com.example.ren.wanandroid.app.WebActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.SerachBean;
import com.example.ren.wanandroid.bean.TencentListBean;
import com.example.ren.wanandroid.presenter.TencentNewsPresenter;
import com.example.ren.wanandroid.view.TencentNewsView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/27.
 */

public class TencentNewsFragment extends BaseFragment<TencentNewsPresenter> implements TencentNewsView {
    @BindView(R.id.tencent_et)
    EditText mTencentEt;
    @BindView(R.id.tencent_btn)
    Button mTencentBtn;
    @BindView(R.id.tencent_toolbar)
    AppBarLayout mTencentToolbar;
    @BindView(R.id.recycler_tencent)
    RecyclerView mRecyclerTencent;
    @BindView(R.id.smart_tencent)
    SmartRefreshLayout mSmartTencent;
    @BindView(R.id.float_btn)
    ImageView mFloatBtn;
    private MyTencentAdapter adapter;
    private int id;
    private ArrayList<TencentListBean.DataBean.DatasBean> datasBeans = new ArrayList<>();;


    public static TencentNewsFragment getInstance(int id, String name) {
        TencentNewsFragment tencentNewsFragment = new TencentNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("name", name);
        tencentNewsFragment.setArguments(bundle);
        return tencentNewsFragment;
    }

    @Override
    protected TencentNewsPresenter initPresenter() {
        return new TencentNewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tencent_news;
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData(id);
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle arguments = getArguments();
        id = arguments.getInt("id");
        String name = arguments.getString("name");
        mTencentEt.setHint(name + "带你发现更多干货");
        mRecyclerTencent.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerTencent.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyTencentAdapter(getActivity());
        mRecyclerTencent.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSmartTencent.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getActivity(), "没有多余干货了~~~~", Toast.LENGTH_SHORT).show();
                mSmartTencent.finishLoadMore(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmartTencent.finishRefresh(true);
            }
        });

        adapter.setOnClickListener(new MyTencentAdapter.OnClickListener() {
            @Override
            public void getPosition(int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("link", adapter.list.get(position).getLink());
                startActivity(intent);
            }
        });

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerTencent.smoothScrollToPosition(0);
            }
        });
    }

    @OnClick(R.id.tencent_btn)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tencent_btn:
                String trim = mTencentEt.getText().toString().trim();
//                mTencentEt.setText("");
                adapter.list.clear();
                basePresenter.getSerachBean(trim);
                adapter.setData(datasBeans);
                adapter.notifyDataSetChanged();
//                mTencentEt.setText("");
                break;
        }
    }

    @Override
    public void setData(List<TencentListBean.DataBean.DatasBean> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showToast(String string) {
//        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setSerachBean(List<TencentListBean.DataBean.DatasBean> serarchbean) {
        datasBeans.addAll(serarchbean);
        adapter.notifyDataSetChanged();
    }
}
