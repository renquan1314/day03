package com.notice.liangxq.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.adapter
 * 文件名：MyFragmentAdapter
 * 创建者：liangxq
 * 创建时间：2019/10/21  10:25
 * 描述：TODO
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter{
    private List<Fragment>fragments;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
