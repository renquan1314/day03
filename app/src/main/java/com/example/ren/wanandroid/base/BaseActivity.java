package com.example.ren.wanandroid.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ren on 2019/10/23.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements Baseview{
    public T basePresenter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basePresenter = initPresenter();
        if (basePresenter!=null){
            basePresenter.setData(this);
        }
    }

    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basePresenter.destroy();
        basePresenter = null;
    }
}
