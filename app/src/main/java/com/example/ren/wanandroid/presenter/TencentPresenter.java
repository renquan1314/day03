package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.TencentBean;
import com.example.ren.wanandroid.model.TencentModel;
import com.example.ren.wanandroid.view.TencentView;
import com.example.ren.wanandroid.view.ZhishiView;

/**
 * Created by ren on 2019/10/24.
 */

public class TencentPresenter extends BasePresenter<TencentView> {

    private TencentModel tencentModel;

    @Override
    protected void initModel() {
        tencentModel = new TencentModel();
    }

    public void getData() {
        tencentModel.getData(new ResultCallback<TencentBean>() {
            @Override
            public void OnSuccess(TencentBean bean) {
                if (baseview!=null&&bean.getData()!=null&&bean.getData().size()>0){
                    baseview.setData(bean.getData());
                }
            }

            @Override
            public void OnFail(String str) {
                if (baseview!=null){
                    baseview.showToast(str);
                }
            }
        });
    }
}
