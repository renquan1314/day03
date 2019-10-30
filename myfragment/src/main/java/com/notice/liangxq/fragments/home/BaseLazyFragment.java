package com.notice.liangxq.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.notice.liangxq.fragments.BaseFragment;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.fragments.home
 * 文件名：BaseLazyFragment
 * 创建者：liangxq
 * 创建时间：2019/10/21  10:21
 * 描述：TODO
 */
public abstract class BaseLazyFragment extends BaseFragment{

    private boolean isPrePared;
    private boolean isLazyed;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isPrePared=true;
        lazyLoad();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    private void lazyLoad(){
        if(getUserVisibleHint()&&isPrePared&&!isLazyed){
            isLazyed=true;
        }
    }
}
