package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.TixiBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/24.
 */

public class ZhishiModel extends BaseModel {
    public void getbean(final ResultCallback<TixiBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<TixiBean> observable = apiService.getToxibean();
        observable.compose(RxUtils.<TixiBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<TixiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TixiBean tixiBean) {
                        if (tixiBean!=null){
                            callback.OnSuccess(tixiBean);
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
