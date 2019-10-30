package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.SystemBean;
import com.example.ren.wanandroid.model.SystemModel;
import com.example.ren.wanandroid.view.LeadView;
import com.example.ren.wanandroid.view.SystemView;

/**
 * Created by ren on 2019/10/24.
 */

public class SystemPresenter extends BasePresenter<SystemView> {

    private SystemModel model;

    @Override
    protected void initModel() {
        model = new SystemModel();
    }

    public void getData(int id) {
        model.getData(id,new ResultCallback<SystemBean>() {
            @Override
            public void OnSuccess(SystemBean bean) {
                if (baseview!=null){
                    if (bean.getData().getDatas()!=null && bean.getData().getDatas().size()>0){
                        baseview.setData(bean.getData().getDatas());
                    }
                }
            }

            @Override
            public void OnFail(String str) {
                    baseview.showToast(str);
            }
        });
    }
}
