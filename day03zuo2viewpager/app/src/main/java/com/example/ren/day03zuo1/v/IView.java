package com.example.ren.day03zuo1.v;

import com.example.ren.day03zuo1.DatasBean;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public interface IView {
    void insertBean(List<DatasBean> list);
    void showToast(String str);
}
