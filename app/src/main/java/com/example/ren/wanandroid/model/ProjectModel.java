package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.ProjectBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/25.
 */

public class ProjectModel extends BaseModel {
    public void getData(final ResultCallback<ProjectBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<ProjectBean> observable = apiService.getProjectBean();
        observable.compose(RxUtils.<ProjectBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<ProjectBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectBean projectBean) {
                            if (projectBean!=null){
                                callback.OnSuccess(projectBean);
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
