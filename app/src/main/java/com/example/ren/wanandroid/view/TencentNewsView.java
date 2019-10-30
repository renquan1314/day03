package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.SerachBean;
import com.example.ren.wanandroid.bean.TencentListBean;

import java.util.List;

/**
 * Created by ren on 2019/10/27.
 */

public interface TencentNewsView extends Baseview {
    void setData(List<TencentListBean.DataBean.DatasBean> datas);
    void showToast(String string);

    void setSerachBean(List<TencentListBean.DataBean.DatasBean> serarchbean);
}
