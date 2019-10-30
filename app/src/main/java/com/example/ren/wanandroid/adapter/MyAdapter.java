package com.example.ren.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ren.wanandroid.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ren on 2019/10/25.
 */

public class MyAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<BaseFragment> fragments;

    public MyAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
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
