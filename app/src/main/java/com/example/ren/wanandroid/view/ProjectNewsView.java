package com.example.ren.wanandroid.view;

import com.example.ren.wanandroid.base.Baseview;
import com.example.ren.wanandroid.bean.ProjectListBean;

import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public interface ProjectNewsView extends Baseview {

    void setData(List<ProjectListBean.DataBean.DatasBean> datas);
    void showToast(String s);
}
