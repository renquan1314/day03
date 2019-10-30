package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.ProjectListBean;
import com.example.ren.wanandroid.model.ProjectNewsModel;
import com.example.ren.wanandroid.view.ProjectNewsView;
import com.example.ren.wanandroid.view.ProjectView;
import com.example.ren.wanandroid.view.TencentView;

/**
 * Created by ren on 2019/10/24.
 */

public class ProjectNewsPresenter extends BasePresenter<ProjectNewsView> {

    private ProjectNewsModel projectNewsModel;

    @Override
    protected void initModel() {
        projectNewsModel = new ProjectNewsModel();
    }

    public void getData(int id) {
        projectNewsModel.getData(id, new ResultCallback<ProjectListBean>() {
            @Override
            public void OnSuccess(ProjectListBean bean) {
                if (baseview!=null&&bean.getData().getDatas()!=null&&bean.getData().getDatas().size()>0){
                    baseview.setData(bean.getData().getDatas());
                }
            }

            @Override
            public void OnFail(String str) {
                if(baseview!=null)
                    baseview.showToast(str);
                }
        });
    }
}
