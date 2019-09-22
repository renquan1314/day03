package com.example.ren.day03zuo1;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ren on 2019/9/20.
 */

public interface ApiService {
    String url = "http://static.owspace.com/";

    @GET("?c=api&a=getList&page_id=0")
    Observable<Bean> getjson1();
}
