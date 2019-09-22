package com.example.ren.day03zuo3.v;

import com.example.ren.day03zuo3.Bean;

import java.util.List;

/**
 * Created by ren on 2019/9/22.
 */

public interface IView {
    void insertBean(List<Bean.BodyBean.ResultBean> re);
    void showToast(String str);
}
