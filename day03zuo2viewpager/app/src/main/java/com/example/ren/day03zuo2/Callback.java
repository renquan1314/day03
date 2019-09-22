package com.example.ren.day03zuo2;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public interface Callback {
    void OnSuccess(List<Bean.ResultsBean> list);
    void OnFail(String string);
}
