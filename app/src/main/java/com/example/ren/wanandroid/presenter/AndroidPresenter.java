package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.BannerBean;
import com.example.ren.wanandroid.bean.HomeBean;
import com.example.ren.wanandroid.model.AndroidModel;
import com.example.ren.wanandroid.view.Wanandroidview;

/**
 * Created by ren on 2019/10/23.
 */

public class AndroidPresenter extends BasePresenter<Wanandroidview> {

    private AndroidModel androidModel;

    @Override
    protected void initModel() {
        androidModel = new AndroidModel();
    }

    public void getHomeBean(int page) {
        androidModel.getHomebean(page,new ResultCallback<HomeBean>() {
            @Override
            public void OnSuccess(HomeBean bean) {
                if (baseview!=null){
                    if (bean.getData().getDatas()!=null&&bean.getData().getDatas().size()>0){
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

    public void getBannerBean() {
        androidModel.getBannerbean(new ResultCallback<BannerBean>() {
            @Override
            public void OnSuccess(BannerBean bean) {
                if (baseview!=null){
                    if (bean.getData()!=null&&bean.getData().size()>0){
                        baseview.setbannerbean(bean.getData());
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
