package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.TabBean;

import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public interface LeadView extends Baseview {

    void setData(List<TabBean.DataBean> data);
    void showToast(String str);
}
