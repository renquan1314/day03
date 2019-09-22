package com.example.ren.day03zuo2.m;

import android.util.Log;

import com.example.ren.day03zuo2.ApiService;
import com.example.ren.day03zuo2.Bean;
import com.example.ren.day03zuo2.Callback;
import com.example.ren.day03zuo2.p.Presenter;

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
        Observable<Bean> observable = apiService.getjson();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.ResultsBean> results = bean.getResults();
                        Log.i("tag",results.toString());
                        callback.OnSuccess(results);
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
