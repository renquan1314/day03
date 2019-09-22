package com.example.ren.day03zuo1;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public interface Callback {
    void OnSuccess(List<DatasBean> re);
    void OnFail(String string);
}

