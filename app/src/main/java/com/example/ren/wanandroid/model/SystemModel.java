package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.SystemBean;
import com.example.ren.wanandroid.bean.TixiBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObserverResourceWrapper;

/**
 * Created by ren on 2019/10/25.
 */

public class SystemModel extends BaseModel {

    public void getData(int id, final ResultCallback<SystemBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<SystemBean> observable = apiService.getSystembean(id);
        observable.compose(RxUtils.<SystemBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<SystemBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SystemBean systemBean) {
                        if (systemBean!=null){
                            callback.OnSuccess(systemBean);
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
