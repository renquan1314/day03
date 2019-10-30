package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.TabBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/25.
 */

public class LeadModel extends BaseModel {
    public void getData(final ResultCallback<TabBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<TabBean> observable = apiService.getTabBean();
        observable.compose(RxUtils.<TabBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        if (tabBean!=null){
                            callback.OnSuccess(tabBean);
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
