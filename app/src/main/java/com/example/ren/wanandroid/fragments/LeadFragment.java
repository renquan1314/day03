package com.example.ren.wanandroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.adapter.MyAdapter;
import com.example.ren.wanandroid.app.VerticalViewPager;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.TabBean;
import com.example.ren.wanandroid.presenter.LeadPresenter;
import com.example.ren.wanandroid.view.LeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * Created by ren on 2019/10/24.
 */

public class LeadFragment extends BaseFragment<LeadPresenter> implements LeadView {
    private static final String TAG = "LeadFragment";
    @BindView(R.id.mtab)
    VerticalTabLayout mMtab;
    @BindView(R.id.viewpager_lead)
    VerticalViewPager mViewpagerLead;
    @BindView(R.id.float_btn)
    ImageView mFloatBtn;
    private ArrayList<String> tabs = new ArrayList<>();
    private MyAdapter adapter;



    public static LeadFragment getInstance() {
        LeadFragment leadFragment = new LeadFragment();
        Bundle bundle = new Bundle();
        leadFragment.setArguments(bundle);
        return leadFragment;
    }

    @Override
    protected LeadPresenter initPresenter() {
        return new LeadPresenter();
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {
        super.initData();
        basePresenter.getData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_lead;
    }

    @Override
    protected void initListener() {
        super.initListener();
        mFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMtab.setTabSelected(0);
            }
        });
    }

    @Override
    public void setData(List<TabBean.DataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            tabs.add(data.get(i).getName());
        }
        Log.i(TAG, "setData: " + tabs.toString());

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            fragments.add(LeadNewsFragment.getInstance(data.get(i).getArticles(), data.get(i).getName()));
        }
        adapter = new MyAdapter(getChildFragmentManager(), fragments);
        mViewpagerLead.setAdapter(adapter);
        mMtab.setupWithViewPager(mViewpagerLead);

        mMtab.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new ITabView.TabTitle.Builder()
                        .setContent(tabs.get(position))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return 0;
            }
        });
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }


}
