package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.SystemBean;

import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public interface SystemView extends Baseview {

    void setData(List<SystemBean.DataBean.DatasBean> datas);
    void showToast(String str);
}
