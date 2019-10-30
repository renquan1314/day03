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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyHomeReAdapter;
import com.example.ren.wanandroid.app.WebActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.BannerBean;
import com.example.ren.wanandroid.bean.HomeBean;
import com.example.ren.wanandroid.presenter.AndroidPresenter;
import com.example.ren.wanandroid.view.Wanandroidview;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by ren on 2019/10/23.
 */

public class AndroidFragment extends BaseFragment<AndroidPresenter> implements Wanandroidview {
    @BindView(R.id.recycler)
    public RecyclerView mRecycler;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    @BindView(R.id.float_btn)
    ImageView mFloatBtn;

    private MyHomeReAdapter adapter;
    private int page = 0;
    public ArrayList<String> strings;
    public ArrayList<String> titls;
    private ArrayList<BaseFragment> fragments;



//    private static final int TYPE_ANDROID = 0;
//    private static final int TYPE_ZHISHI = 1;
//    private static final int TYPE_TENCENT = 2;
//    private static final int TYPE_LEAD = 3;
//    private static final int TYPE_PROJECT = 4;
//    int _position;


    public static AndroidFragment getInstance() {
        AndroidFragment androidFragment = new AndroidFragment();
        Bundle bundle = new Bundle();
        androidFragment.setArguments(bundle);
        return androidFragment;
    }


    @Override
    protected void initView() {
        super.initView();
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        strings = new ArrayList<>();
        titls = new ArrayList<>();
        adapter = new MyHomeReAdapter(getActivity(), strings, titls);
        mRecycler.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        basePresenter.getHomeBean(page);
        basePresenter.getBannerBean();
    }

    @Override
    protected void initListener() {
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                basePresenter.getHomeBean(page);
                mSmart.finishLoadMore(true);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.lists.clear();
                page = 0;
                basePresenter.getHomeBean(page);
                mSmart.finishRefresh(true);
            }
        });

        adapter.setOnClickListeners(new MyHomeReAdapter.OnClickListener() {
            @Override
            public void onClickListener(int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("link", adapter.lists.get(position).getLink());
                startActivity(intent);
            }
        });
        int id = R.mipmap.icon_up_arrow;
        Glide.with(getActivity()).load(id).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mFloatBtn);
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecycler.smoothScrollToPosition(0);
            }
        });


    }


    @Override
    protected AndroidPresenter initPresenter() {
        return new AndroidPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_android;
    }

    @Override
    public void setData(List<HomeBean.DataBean.DatasBean> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setbannerbean(List<BannerBean.DataBean> bannerbean) {
        for (int i = 0; i < bannerbean.size(); i++) {
            strings.add(bannerbean.get(i).getImagePath());
            titls.add(bannerbean.get(i).getTitle());
        }
    }



}
