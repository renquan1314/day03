package com.example.ren.wanandroid.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.app.MainActivity;
import com.example.ren.wanandroid.base.BaseFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View view;
    private FrameLayout mFra;
    private TabLayout mTab;

    private static final int TYPE_ANDROID = 0;
    private static final int TYPE_ZHISHI = 1;
    private static final int TYPE_TENCENT = 2;
    private static final int TYPE_LEAD = 3;
    private static final int TYPE_PROJECT = 4;
    int _position;
    private ArrayList<BaseFragment> fragments;
    public FloatingActionButton mFloatBtn;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initlistener();
        return view;
    }

    private void initlistener() {
        final MainActivity activity = (MainActivity) getActivity();

        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        activity.mToolbarTv.setText("首页");
                        switchfragment(TYPE_ANDROID);
                        break;
                    case 1:
                        activity.mToolbarTv.setText("知识体系");
                        switchfragment(TYPE_ZHISHI);
                        break;
                    case 2:
                        activity.mToolbarTv.setText("公众号");
                        switchfragment(TYPE_TENCENT);
                        break;
                    case 3:
                        activity.mToolbarTv.setText("导航");
                        switchfragment(TYPE_LEAD);
                        break;
                    case 4:
                        activity.mToolbarTv.setText("项目");
                        switchfragment(TYPE_PROJECT);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void switchfragment(int type) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        BaseFragment baseFragment = fragments.get(type);
        BaseFragment fragment = fragments.get(_position);
        if (!baseFragment.isAdded()) {
            transaction.add(R.id.fra, baseFragment);
        }
        transaction.hide(fragment);
        transaction.show(baseFragment);
        transaction.commit();
        _position = type;



    }

    private void initView(View view) {
        mFra = (FrameLayout) view.findViewById(R.id.fra);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        init();


        fragments = new ArrayList<>();
        fragments.add(AndroidFragment.getInstance());
        fragments.add(ZhishiFragment.getInstance());
        fragments.add(TencentFragment.getInstance());
        fragments.add(LeadFragment.getInstance());
        fragments.add(ProjectFragment.getInstance());

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (!fragments.get(0).isAdded()) {
            fragmentTransaction.add(R.id.fra, fragments.get(0)).commit();
        }


    }


    protected void init() {
        for (int i = 0; i < 5; i++) {
            mTab.addTab(mTab.newTab().setCustomView(initdata(i)));
        }
    }

    private View initdata(int i) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_tablayout, null);
        ImageView image = view.findViewById(R.id.tab_image);
        TextView tv = view.findViewById(R.id.tab_tv);
        switch (i) {
            case 0:
                tv.setText("首页");
                image.setImageResource(R.drawable.tab_select1);
                break;
            case 1:
                tv.setText("知识体系");
                image.setImageResource(R.drawable.tab_select2);
                break;
            case 2:
                tv.setText("公众号");
                image.setImageResource(R.drawable.tab_select3);
                break;
            case 3:
                tv.setText("导航");
                image.setImageResource(R.drawable.tab_select4);
                break;
            case 4:
                tv.setText("项目");
                image.setImageResource(R.drawable.tab_select5);
                break;
        }
        return view;
    }


}
