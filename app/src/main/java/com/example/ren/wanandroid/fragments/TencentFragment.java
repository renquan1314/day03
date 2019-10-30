package com.example.ren.wanandroid.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyViewTabAdapter;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.TencentBean;
import com.example.ren.wanandroid.presenter.TencentPresenter;
import com.example.ren.wanandroid.view.TencentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class TencentFragment extends BaseFragment<TencentPresenter> implements TencentView {
    @BindView(R.id.tencent_tab)
    TabLayout mTencentTab;
    @BindView(R.id.tencent_pager)
    ViewPager mTencentPager;
    private MyViewTabAdapter adapter;

    public static TencentFragment getInstance() {
        TencentFragment tencentFragment = new TencentFragment();
        Bundle bundle = new Bundle();
        tencentFragment.setArguments(bundle);
        return tencentFragment;
    }

    @Override
    protected TencentPresenter initPresenter() {
        return new TencentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tencent;
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
    public void setData(List<TencentBean.DataBean> data) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            list.add(data.get(i).getName());
            fragments.add(TencentNewsFragment.getInstance(data.get(i).getId(),data.get(i).getName()));
        }
        adapter = new MyViewTabAdapter(getChildFragmentManager(), list, fragments);
        mTencentPager.setAdapter(adapter);
        mTencentTab.setupWithViewPager(mTencentPager);
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }
}
