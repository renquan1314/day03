package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.BannerBean;
import com.example.ren.wanandroid.bean.HomeBean;

import java.util.List;

/**
 * Created by ren on 2019/10/23.
 */

public interface Wanandroidview extends Baseview {

    void setData(List<HomeBean.DataBean.DatasBean> datas);

    void showToast(String str);

    void setbannerbean(List<BannerBean.DataBean> bannerbean);
}
