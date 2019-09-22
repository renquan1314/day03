package com.example.ren.day03zuo2;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ren on 2019/9/20.
 */

public interface ApiService {
    String url = "http://gank.io/api/";

    @GET("data/%E7%A6%8F%E5%88%A9/20/1")
    Observable<Bean> getjson();
}
