package com.example.ren.day03zuo3.m;

import com.example.ren.day03zuo3.ApiService;
import com.example.ren.day03zuo3.Bean;
import com.example.ren.day03zuo3.p.Presenter;
import com.example.ren.day03zuo3.v.Callback;

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
 * Created by ren on 2019/9/22.
 */

public class Model {
    public void addbean(final Callback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<Bean> observable = apiService.getjson();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.BodyBean.ResultBean> result = bean.getBody().getResult();
                        callback.OnSuccess(result);
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
