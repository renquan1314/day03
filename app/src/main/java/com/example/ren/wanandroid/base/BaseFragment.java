package com.example.ren.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ren on 2019/10/23.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements Baseview{

    private Unbinder bind;
    public T basePresenter = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, inflate);
        basePresenter = initPresenter();
        if (basePresenter!=null){
            basePresenter.setData(this);
        }
        initView();
        initData();
        initListener();
        return inflate;
    }

    protected abstract T initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }
}
