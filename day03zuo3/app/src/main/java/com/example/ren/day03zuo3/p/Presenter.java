package com.example.ren.day03zuo3.p;

import com.example.ren.day03zuo3.Bean;
import com.example.ren.day03zuo3.m.Model;
import com.example.ren.day03zuo3.v.Callback;
import com.example.ren.day03zuo3.v.IView;

import java.util.List;

/**
 * Created by ren on 2019/9/22.
 */

public class Presenter implements Callback{
    private IView view;
    private Model model;

    public Presenter(IView view) {
        this.view = view;
        this.model = new Model();
    }

    @Override
    public void OnSuccess(List<Bean.BodyBean.ResultBean> list) {
        view.insertBean(list);
    }

    @Override
    public void OnFail(String string) {
        view.showToast(string);
    }

    public void insertbean() {
        model.addbean(this);
    }
}
