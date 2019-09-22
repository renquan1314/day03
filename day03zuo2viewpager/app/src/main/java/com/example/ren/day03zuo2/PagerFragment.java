package com.example.ren.day03zuo2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ViewPager mPager;
    private ArrayList<Fragment> fragments;
    private MyPagerAdapter adapter;
    private int i;
    private ArrayList<Bean.ResultsBean> list;
    private TextView mPageTv;
    /**
     * 返回
     */
    private Button mBtn;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        initView(view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Message(Content content) {
        i = content.getI();
        list = content.getList();

        fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            BlankFragment blankFragment = new BlankFragment();
            fragments.add(blankFragment);
            String url = list.get(i).getUrl();
            Bundle bundle = new Bundle();
            bundle.putInt("i", i);
            bundle.putString("img", url);
            blankFragment.setArguments(bundle);
        }
        adapter = new MyPagerAdapter(getChildFragmentManager(), fragments);
        mPager.setAdapter(adapter);
        mPager.setCurrentItem(i);
        showtv(i, list.size());
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showtv(position + 1, list.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void showtv(int i, int size) {
        mPageTv.setText(i + "/" + size);
    }

    private void initView(View view) {
        mPager = (ViewPager) view.findViewById(R.id.pager);
        mPageTv = (TextView) view.findViewById(R.id.page_tv);
        mBtn = (Button) view.findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
                break;
        }
    }
}
