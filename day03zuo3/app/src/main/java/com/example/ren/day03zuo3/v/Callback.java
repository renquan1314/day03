package com.example.ren.day03zuo3.v;

import com.example.ren.day03zuo3.Bean;

import java.util.List;

/**
 * Created by ren on 2019/9/22.
 */

public interface Callback {
    void OnSuccess(List<Bean.BodyBean.ResultBean> list);
    void OnFail(String string);
}
