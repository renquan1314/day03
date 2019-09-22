package com.example.ren.day03zuo2.v;

import com.example.ren.day03zuo2.Bean;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public interface Iview {
    void insertBean(List<Bean.ResultsBean> re);
    void showToast(String str);
}
