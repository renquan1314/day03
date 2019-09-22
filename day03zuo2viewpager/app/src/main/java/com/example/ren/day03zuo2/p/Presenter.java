package com.example.ren.day03zuo2.p;

import com.example.ren.day03zuo2.Bean;
import com.example.ren.day03zuo2.Callback;
import com.example.ren.day03zuo2.m.Model;
import com.example.ren.day03zuo2.v.Iview;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public class Presenter implements Callback{
    private Iview iview;
    private Model model;

    public Presenter(Iview iview) {
        this.iview = iview;
        this.model = new Model();
    }

    @Override
    public void OnSuccess(List<Bean.ResultsBean> list) {
        iview.insertBean(list);
    }

    @Override
    public void OnFail(String string) {
        iview.showToast(string);
    }

    public void addBean() {
        model.insertbean(this);
    }
}
