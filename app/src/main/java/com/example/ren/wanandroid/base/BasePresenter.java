package com.example.ren.wanandroid.base;

import com.example.ren.wanandroid.model.MainModel;

/**
 * Created by ren on 2019/10/23.
 */

public abstract class BasePresenter<T extends Baseview> {
    public T baseview;

    public BasePresenter(){
        initModel();
    }

    protected abstract void initModel();

    public void setData(T baseview) {
        this.baseview = baseview;
    }

    public void destroy() {
        baseview = null;
    }
}
