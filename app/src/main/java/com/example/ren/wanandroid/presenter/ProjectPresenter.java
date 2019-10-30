package com.example.ren.wanandroid.presenter;

import com.example.ren.wanandroid.base.BasePresenter;
import com.example.ren.wanandroid.base.ResultCallback;
import com.example.ren.wanandroid.bean.ProjectBean;
import com.example.ren.wanandroid.model.ProjectModel;
import com.example.ren.wanandroid.view.ProjectView;
import com.example.ren.wanandroid.view.ZhishiView;

/**
 * Created by ren on 2019/10/24.
 */

public class ProjectPresenter extends BasePresenter<ProjectView> {

    private ProjectModel projectModel;

    @Override
    protected void initModel() {
        projectModel = new ProjectModel();
    }

    public void getData() {
        projectModel.getData(new ResultCallback<ProjectBean>() {
            @Override
            public void OnSuccess(ProjectBean bean) {
                if(baseview!=null&&bean.getData()!=null&&bean.getData().size()>0){
                    baseview.setData(bean.getData());
                }
            }

            @Override
            public void OnFail(String str) {
                    baseview.showToast(str);
            }
        });
    }
}
