package com.example.ren.day03zuo1.p;

import com.example.ren.day03zuo1.Callback;
import com.example.ren.day03zuo1.DatasBean;
import com.example.ren.day03zuo1.m.Model;
import com.example.ren.day03zuo1.v.IView;

import java.util.List;

/**
 * Created by ren on 2019/9/20.
 */

public class Presenter implements Callback{
    private IView iView;
    private Model model;

    public Presenter(IView iView) {
        this.iView = iView;
        this.model = new Model();
    }

    @Override
    public void OnSuccess(List<DatasBean> re) {
            iView.insertBean(re);
    }

    @Override
    public void OnFail(String string) {
        iView.showToast(string);
    }



    public void addbean() {
        model.insertbean(this);
    }

    public void dbinsert(DatasBean bean) {
        model.insert(this,bean);
    }

    public void deletebean(DatasBean bean) {
        model.delete(bean,this);
    }
}
