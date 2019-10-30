package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.SerachBean;
import com.example.ren.wanandroid.bean.TencentListBean;
import com.example.ren.wanandroid.model.TencentModel;
import com.example.ren.wanandroid.view.TencentNewsView;

/**
 * Created by ren on 2019/10/27.
 */

public class TencentNewsPresenter extends BasePresenter<TencentNewsView> {

    private TencentModel tencentModel;

    @Override
    protected void initModel() {
        tencentModel = new TencentModel();
    }

    public void getData(int id) {
        tencentModel.setData(id, new ResultCallback<TencentListBean>() {
            @Override
            public void OnSuccess(TencentListBean bean) {
                if (baseview!=null&&bean.getData().getDatas()!=null&&bean.getData().getDatas().size()>0){
                    baseview.setData(bean.getData().getDatas());
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

    public void getSerachBean(String trim) {
        tencentModel.getSerarchBean(trim, new ResultCallback<TencentListBean>() {
            @Override
            public void OnSuccess(TencentListBean bean) {
                if (baseview!=null&&bean.getData().getDatas()!=null&&bean.getData().getDatas().size()>0){
                    baseview.setSerachBean(bean.getData().getDatas());
                }
            }

            @Override
            public void OnFail(String str) {
                    baseview.showToast(str);
            }
        });
    }
}
