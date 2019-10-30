package com.notice.liangxq.utils;

import com.notice.liangxq.http.ApiException;
import com.notice.liangxq.http.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.utils
 * 文件名：RxUtils
 * 创建者：liangxq
 * 创建时间：2019/10/23  1:56
 * 描述：TODO
 */
public class RxUtils {

    //Rxjava 线程切换封装
    public static <T> ObservableTransformer<T,T>rxScheduleThread(){
        return  new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> ObservableTransformer<BaseResponse<T>,T>rxChangeResult(){
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
                        if(tBaseResponse.getErrorCode()==0){
                            return creatObserver(tBaseResponse.getData());
                        }else {
                            return Observable.error(new ApiException(tBaseResponse.getErrorCode(),tBaseResponse.getErrorMsg()));
                        }
                    }
                });
            }
        };
    }


    private static <T> Observable<T> creatObserver(final T t){
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }
}
