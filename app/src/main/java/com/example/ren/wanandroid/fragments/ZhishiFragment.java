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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyTixiReAdapter;
import com.example.ren.wanandroid.app.DetalsActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.TixiBean;
import com.example.ren.wanandroid.presenter.ZhishiPresenter;
import com.example.ren.wanandroid.view.ZhishiView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class ZhishiFragment extends BaseFragment<ZhishiPresenter> implements ZhishiView {
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.float_btn)
    ImageView mFloatBtn;
    private MyTixiReAdapter adapter;



    public static ZhishiFragment getInstance() {
        ZhishiFragment zhihuFragment = new ZhishiFragment();
        Bundle bundle = new Bundle();
        zhihuFragment.setArguments(bundle);
        return zhihuFragment;
    }

    @Override
    protected ZhishiPresenter initPresenter() {
        return new ZhishiPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhishi;
    }

    @Override
    protected void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyTixiReAdapter(getActivity());
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast.makeText(getActivity(), "没有多余的干货了┗( ▔, ▔ )┛", Toast.LENGTH_SHORT).show();
                mSmart.finishLoadMore(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mSmart.finishRefresh(true);
            }
        });

        adapter.setS(new MyTixiReAdapter.OnClickListener() {
            @Override
            public void getPosition(int position) {
                Intent intent = new Intent(getActivity(), DetalsActivity.class);
                List<TixiBean.DataBean> lists = adapter.lists;
                intent.putExtra("list", (Serializable) lists);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecycler.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    protected void initData() {
        basePresenter.getData();
    }

    @Override
    public void setData(List<TixiBean.DataBean> data) {
        adapter.setData(data);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

}
