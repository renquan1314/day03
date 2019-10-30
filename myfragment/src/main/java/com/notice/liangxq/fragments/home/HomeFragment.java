package com.notice.liangxq.fragments.home;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.notice.liangxq.R;
import com.notice.liangxq.adapter.MyFragmentAdapter;
import com.notice.liangxq.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tab_inHome)
    TabLayout tabInHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        tabInHome.addTab(tabInHome.newTab().setText("推荐"));
        tabInHome.addTab(tabInHome.newTab().setText("歌手"));
        tabInHome.addTab(tabInHome.newTab().setText("演员"));
        tabInHome.addTab(tabInHome.newTab().setText("主持人"));
        List<Fragment>fragments=new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new SingerFragment());
        fragments.add(new ActorFragment());
        fragments.add(new CompereFragment());
        vpHome.setAdapter(new MyFragmentAdapter(getChildFragmentManager(),fragments));
        vpHome.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabInHome));
    }

    @Override
    public int createLayout() {
        return R.layout.fragment_blank;
    }

}
