package com.example.ren.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MySystemReAdapter;
import com.example.ren.wanandroid.app.SystemActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.SystemBean;
import com.example.ren.wanandroid.presenter.SystemPresenter;
import com.example.ren.wanandroid.view.SystemView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class SystemFragment extends BaseFragment<SystemPresenter> implements SystemView {
    @BindView(R.id.system_recycler)
    RecyclerView mSystemRecycler;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private MySystemReAdapter adapter;
    private int id;


    public static SystemFragment getInstance(int id) {
        SystemFragment leadFragment = new SystemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        leadFragment.setArguments(bundle);
        return leadFragment;
    }

    @Override
    protected SystemPresenter initPresenter() {
        return new SystemPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initView() {
        super.initView();
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        mSystemRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSystemRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new MySystemReAdapter(getActivity());
        mSystemRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getActivity(), "没有多余的干货了٩꒰▽ ꒱۶⁼³₌₃", Toast.LENGTH_SHORT).show();
                mSmart.finishLoadMore(true);
            }
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmart.finishRefresh(2000);
            }
        });

        adapter.setS(new MySystemReAdapter.OnClickListener() {
            @Override
            public void getPosition(int position) {
                Intent intent = new Intent(getActivity(), SystemActivity.class);
                intent.putExtra("link",adapter.lists.get(position).getLink());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        basePresenter.getData(id);
    }

    @Override
    public void setData(List<SystemBean.DataBean.DatasBean> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
