package com.example.ren.day03zuo2;

import java.util.ArrayList;

/**
 * Created by ren on 2019/9/20.
 */

public class Content {
    private int i;
    private ArrayList<Bean.ResultsBean> list;

    public Content(int i, ArrayList<Bean.ResultsBean> list) {
        this.i = i;
        this.list = list;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public ArrayList<Bean.ResultsBean> getList() {
        return list;
    }

    public void setList(ArrayList<Bean.ResultsBean> list) {
        this.list = list;
    }
}
