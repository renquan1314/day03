package com.example.ren.wanandroid.model;

import com.example.ren.wanandroid.base.BaseModel;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.ProjectListBean;
import com.example.ren.wanandroid.http.HttpManager;
import com.example.ren.wanandroid.utils.ApiService;
import com.example.ren.wanandroid.utils.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by ren on 2019/10/25.
 */

public class ProjectNewsModel extends BaseModel {
    public void getData(int id, final ResultCallback<ProjectListBean> callback) {
        ApiService apiService = HttpManager.getInstance().getServer(ApiService.URL, ApiService.class);
        Observable<ProjectListBean> bean = apiService.getProjectListBean(id);
        bean.compose(RxUtils.<ProjectListBean>rxObserableSchedulerHelper())
                .subscribe(new Observer<ProjectListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectListBean projectListBean) {
                        if (projectListBean!=null){
                            callback.OnSuccess(projectListBean);
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
}
