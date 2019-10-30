package com.example.ren.wanandroid.base;

/**
 * Created by ren on 2019/10/24.
 */

public interface ResultCallback<T> {
    void OnSuccess(T bean);
    void OnFail(String str);
}
