package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.TencentBean;

import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public interface TencentView extends Baseview {

    void setData(List<TencentBean.DataBean> data);
    void showToast(String string);
}
