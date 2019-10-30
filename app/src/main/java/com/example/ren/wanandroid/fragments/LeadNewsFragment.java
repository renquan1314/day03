package com.example.ren.wanandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.app.LeadActivity;
import com.example.ren.wanandroid.base.BaseFragment;
import com.example.ren.wanandroid.bean.TabBean;
import com.example.ren.wanandroid.presenter.LeadNewsPresenter;
import com.example.ren.wanandroid.utils.FlowLayout;
import com.example.ren.wanandroid.view.LeadNewsView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/24.
 */

public class LeadNewsFragment extends BaseFragment<LeadNewsPresenter> implements LeadNewsView {
    @BindView(R.id.flow)
    FlowLayout mFlow;
    @BindView(R.id.lable_tv1)
    TextView mLableTv1;



    public static LeadNewsFragment getInstance(List<TabBean.DataBean.ArticlesBean> articles, String name) {
        LeadNewsFragment tencentFragment = new LeadNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",name);
        bundle.putSerializable("list", (Serializable) articles);
        tencentFragment.setArguments(bundle);
        return tencentFragment;
    }

    @Override
    protected LeadNewsPresenter initPresenter() {
        return new LeadNewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_leadnews;
    }

    @Override
    protected void initView() {

        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        mLableTv1.setText(name);
        final ArrayList<TabBean.DataBean.ArticlesBean> list = (ArrayList<TabBean.DataBean.ArticlesBean>) arguments.getSerializable("list");
        for (int i = 0; i < list.size(); i++) {
            //获取视图,视图可以自定义,可以添加自己想要的效果
            View view = View.inflate(getActivity(), R.layout.item_lable, null);
            TextView viewById = view.findViewById(R.id.lable_tv2);

            viewById.setText(list.get(i).getTitle());

            final String link = list.get(i).getLink();

            viewById.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LeadActivity.class);
                    intent.putExtra("link",link);
                    startActivity(intent);
                }
            });

            //加到容器中,parent是FlowLayout
            mFlow.addView(view);
        }
    }
}
