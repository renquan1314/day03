package com.example.ren.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ren.wanandroid.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ren on 2019/10/25.
 */

public class MyProjectNewsAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> strings;
    private final ArrayList<BaseFragment> fragments;

    public MyProjectNewsAdapter(FragmentManager fm, ArrayList<String> strings, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.strings = strings;
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
