package com.example.ren.wanandroid.base;

import android.util.Log;

import com.example.ren.wanandroid.utils.ApiException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by ren on 2019/10/23.
 */

public abstract class BaseObserver<T>  implements Observer<T>{
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
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
    public void onComplete() {
        if (compositeDisposable!=null){
            compositeDisposable.clear();
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String error);
}
