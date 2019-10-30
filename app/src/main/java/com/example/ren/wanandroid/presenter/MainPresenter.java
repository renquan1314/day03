package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.model.MainModel;

/**
 * Created by ren on 2019/10/23.
 */

public class MainPresenter extends BasePresenter {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
    }
}
