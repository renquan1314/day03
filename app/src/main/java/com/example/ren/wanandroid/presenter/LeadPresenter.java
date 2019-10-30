package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.TabBean;
import com.example.ren.wanandroid.model.LeadModel;
import com.example.ren.wanandroid.view.LeadView;
import com.example.ren.wanandroid.view.ZhishiView;

/**
 * Created by ren on 2019/10/24.
 */

public class LeadPresenter extends BasePresenter<LeadView> {

    private LeadModel leadModel;

    @Override
    protected void initModel() {
        leadModel = new LeadModel();
    }

    public void getData() {
        leadModel.getData(new ResultCallback<TabBean>() {
            @Override
            public void OnSuccess(TabBean bean) {
                    if (bean.getData()!=null&&bean.getData().size()>0){
                        baseview.setData(bean.getData());
                    }
            }

            @Override
            public void OnFail(String str) {
                        baseview.showToast("失败");
            }
        });
    }
}
