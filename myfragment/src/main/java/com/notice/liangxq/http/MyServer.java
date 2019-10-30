package com.notice.liangxq.http;

import com.notice.liangxq.bean.Bean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.http
 * 文件名：MyServer
 * 创建者：liangxq
 * 创建时间：2019/10/22  21:06
 * 描述：TODO
 */
public interface MyServer {
    String URL="https://www.wanandroid.com/";
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<Bean>>>get();
}
