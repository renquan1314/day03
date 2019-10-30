package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.BannerBean;
import com.example.ren.wanandroid.bean.HomeBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.HttpUtils;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/23.
 */

public class AndroidModel extends BaseModel {
    public void getHomebean(int page, final ResultCallback<HomeBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<HomeBean> observable = apiService.getJson(page);
        observable.compose(RxUtils.<HomeBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                            if (homeBean!=null){
                                callback.OnSuccess(homeBean);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {
                            callback.OnFail("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getBannerbean(final ResultCallback<BannerBean> callback) {
        ApiService server = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<BannerBean> observable = server.getBanner();
        observable.compose(RxUtils.<BannerBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        if (bannerBean!=null){
                            callback.OnSuccess(bannerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            callback.OnFail("失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
