package com.example.ren.wanandroid.utils;

import com.example.ren.wanandroid.bean.BannerBean;
import com.example.ren.wanandroid.bean.HomeBean;
import com.example.ren.wanandroid.bean.ProjectBean;
import com.example.ren.wanandroid.bean.ProjectListBean;
import com.example.ren.wanandroid.bean.SerachBean;
import com.example.ren.wanandroid.bean.SystemBean;
import com.example.ren.wanandroid.bean.TabBean;
import com.example.ren.wanandroid.bean.TencentBean;
import com.example.ren.wanandroid.bean.TencentListBean;
import com.example.ren.wanandroid.bean.TixiBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by ren on 2019/10/23.
 */

public interface ApiService {
    String URL="https://www.wanandroid.com/";

    @GET("banner/json")
    Observable<BannerBean> getBanner();

    @GET("article/list/{page}/json")
    Observable<HomeBean> getJson(@Path("page") int page);

    @GET("tree/json")
    Observable<TixiBean> getToxibean();

    @GET("navi/json")
    Observable<TabBean> getTabBean();

    @GET("article/list/0/json")
    Observable<SystemBean> getSystembean(@Query("cid") int cid);

    @GET("project/tree/json")
    Observable<ProjectBean> getProjectBean();

    @GET("project/list/1/json")
    Observable<ProjectListBean> getProjectListBean(@Query("cid") int cid);

    @GET("wxarticle/chapters/json")
    Observable<TencentBean> getTencentBean();

    @GET("wxarticle/list/{News}/1/json")
    Observable<TencentListBean> getTencentListBean(@Path("News") int id);

    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<ResponseBody> login(@Field("username") String username,@Field("password")String password);

    //注册
    @POST("user/register")
    @FormUrlEncoded
    Observable<ResponseBody> register(@Field("username") String username,@Field("password") String password,@Field("repassword") String repassword);

    @GET("article/list/0/json")
    Observable<TencentListBean> getSearchBean(@Query("author") String author);
}
