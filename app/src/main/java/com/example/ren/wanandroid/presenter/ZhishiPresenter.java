package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.TixiBean;
import com.example.ren.wanandroid.model.ZhishiModel;
import com.example.ren.wanandroid.view.ZhishiView;

/**
 * Created by ren on 2019/10/24.
 */

public class ZhishiPresenter extends BasePresenter<ZhishiView> {

    private ZhishiModel zhishiModel;

    @Override
    protected void initModel() {
        zhishiModel = new ZhishiModel();
    }

    public void getData() {
        zhishiModel.getbean(new ResultCallback<TixiBean>() {
            @Override
            public void OnSuccess(TixiBean bean) {
                if (baseview!=null){
                    if (bean.getData()!=null&&bean.getData().size()>0){
                        baseview.setData(bean.getData());
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
