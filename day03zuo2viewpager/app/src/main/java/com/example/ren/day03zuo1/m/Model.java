package com.example.ren.day03zuo1.m;

import com.example.ren.day03zuo1.ApiService;
import com.example.ren.day03zuo1.BaseApp;
import com.example.ren.day03zuo1.Bean;
import com.example.ren.day03zuo1.Callback;
import com.example.ren.day03zuo1.DatasBean;
import com.example.ren.day03zuo1.db.DatasBeanDao;
import com.example.ren.day03zuo1.p.Presenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ren on 2019/9/20.
 */

public class Model {
    public void insertbean(final Callback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Bean> observable = apiService.getjson1();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<DatasBean> datas = bean.getDatas();
                        callback.OnSuccess(datas);
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

    public void insert(Callback callback, DatasBean bean) {
        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        dao.insertOrReplace(bean);
        callback.OnFail("关注成功");
    }

    public void delete(DatasBean bean, Callback callback) {
        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        dao.delete(bean);
        callback.OnFail("取消关注");
    }
}
