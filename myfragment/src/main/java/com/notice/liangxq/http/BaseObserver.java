package com.notice.liangxq.http;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.http
 * 文件名：BaseObserver
 * 创建者：liangxq
 * 创建时间：2019/10/22  22:07
 * 描述：TODO
 */
public abstract class BaseObserver<T> implements Observer<T>{
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if(e instanceof ApiException){
            ApiException apiException= (ApiException) e;
            apiException.getMessage();
            Log.e("liangxq", "onError: "+apiException.getMessage() );
        }else if(e instanceof HttpException){
            Log.e("liangxq", "onError: "+e.getMessage() );
        }else{
            Log.e("liangxq", "onError: "+e.getMessage() );
        }
        onFail(e.getMessage());
    }

    @Override
    public void onNext(T t) {
        onSucess(t);
    }

    @Override
    public void onComplete() {
        if(compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    public abstract void onSucess(T t);

    public abstract void onFail(String error);
}
