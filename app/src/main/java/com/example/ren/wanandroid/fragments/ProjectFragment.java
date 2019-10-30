package com.example.ren.wanandroid.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyProjectNewsAdapter;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.ProjectBean;
import com.example.ren.wanandroid.presenter.ProjectPresenter;
import com.example.ren.wanandroid.view.ProjectView;
import com.flyco.tablayout.SlidingTabLayout;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ProjectView {
    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mProjectTabLayout;
    @BindView(R.id.project_divider)
    View mProjectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager mProjectViewpager;

    private List<ProjectBean.DataBean> tabs = new ArrayList<>();
    private MyProjectNewsAdapter adapter;

    public static ProjectFragment getInstance() {
        ProjectFragment projectFragment = new ProjectFragment();
        Bundle bundle = new Bundle();
        projectFragment.setArguments(bundle);
        return projectFragment;
    }

    @Override
    protected ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData();
    }

    @Override
    public void setData(List<ProjectBean.DataBean> data) {
        tabs.addAll(data);

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            strings.add(tabs.get(i).getName());
            fragments.add(ProjectNewsFragment.getInstance(tabs.get(i).getId()));
        }
        adapter = new MyProjectNewsAdapter(getChildFragmentManager(), strings, fragments);
        mProjectViewpager.setAdapter(adapter);
        mProjectTabLayout.setViewPager(mProjectViewpager);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }
}
