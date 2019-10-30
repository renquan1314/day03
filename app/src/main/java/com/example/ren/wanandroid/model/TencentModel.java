package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.ProjectListBean;
import com.example.ren.wanandroid.bean.SerachBean;
import com.example.ren.wanandroid.bean.TencentBean;
import com.example.ren.wanandroid.bean.TencentListBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/27.
 */

public class TencentModel extends BaseModel {
    public void getData(final ResultCallback<TencentBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<TencentBean> bean = apiService.getTencentBean();
        bean.compose(RxUtils.<TencentBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<TencentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TencentBean tencentBean) {
                        if (tencentBean!=null){
                            callback.OnSuccess(tencentBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.OnFail("失败"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setData(int id, final ResultCallback<TencentListBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<TencentListBean> observable = apiService.getTencentListBean(id);
        observable.compose(RxUtils.<TencentListBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<TencentListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TencentListBean tencentListBean) {
                        if (tencentListBean!=null){
                            callback.OnSuccess(tencentListBean);
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

    public void getSerarchBean(String trim, final ResultCallback<TencentListBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<TencentListBean> bean = apiService.getSearchBean(trim);
        bean.compose(RxUtils.<TencentListBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<TencentListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TencentListBean serachBean) {
                        if (serachBean!=null){
                            callback.OnSuccess(serachBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            callback.OnFail(e.getMessage()+"失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
