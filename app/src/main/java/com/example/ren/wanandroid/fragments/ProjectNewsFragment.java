package com.example.ren.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyReProjectAdapter;
import com.example.ren.wanandroid.app.ProjectActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.ProjectListBean;
import com.example.ren.wanandroid.presenter.ProjectNewsPresenter;
import com.example.ren.wanandroid.view.ProjectNewsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class ProjectNewsFragment extends BaseFragment<ProjectNewsPresenter> implements ProjectNewsView {
    @BindView(R.id.project_recycler)
    RecyclerView mProjectRecycler;
    @BindView(R.id.float_btn)
    ImageView mFloatBtn;
    private MyReProjectAdapter adapter;
    private int id;



    public static ProjectNewsFragment getInstance(int id) {
        ProjectNewsFragment tencentFragment = new ProjectNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        tencentFragment.setArguments(bundle);
        return tencentFragment;
    }

    @Override
    protected ProjectNewsPresenter initPresenter() {
        return new ProjectNewsPresenter();
    }

    @Override
    protected void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        mProjectRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProjectRecycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyReProjectAdapter(getActivity());
        mProjectRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projectnews;
    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData(id);
    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setOnClickListener(new MyReProjectAdapter.OnClickListener() {
            @Override
            public void getPosition(int position) {
                Intent intent = new Intent(getActivity(), ProjectActivity.class);
                intent.putExtra("link", adapter.list.get(position).getLink());
                startActivity(intent);
            }
        });

        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProjectRecycler.smoothScrollToPosition(0);
            }
        });
    }

    @Override
    public void setData(List<ProjectListBean.DataBean.DatasBean> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }


}
