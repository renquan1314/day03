package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.TixiBean;

import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public interface ZhishiView extends Baseview {

    void setData(List<TixiBean.DataBean> data);

    void showToast(String str);
}
