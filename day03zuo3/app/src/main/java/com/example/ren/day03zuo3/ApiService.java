package com.example.ren.day03zuo3;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ren on 2019/9/22.
 */

public interface ApiService {
    String url = "https://api.yunxuekeji.cn/";

    @GET("yunxue_app_api/content/getData/30/66597/1/10")
    Observable<Bean> getjson();

    String url2 = "https://api.yunxuekeji.cn/";

    @GET("yunxue_app_api/teacher/getTeacherPower/{page}")
    Observable<ResponseBody> getjson2(@Path("page") int page);
}
