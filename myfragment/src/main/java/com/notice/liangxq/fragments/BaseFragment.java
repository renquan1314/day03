package com.notice.liangxq.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.fragments
 * 文件名：BaseFragment
 * 创建者：liangxq
 * 创建时间：2019/9/25  21:34
 * 描述：TODO
 */
public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    private Unbinder unbinder;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }


    //接收Activity的传值
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(createLayout(), container, false);
            Log.e("liangxq", "onCreateView: "+this.getClass().getName() );
        }
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        initView(view);
        initData();
    }

    //初始化数据
    protected abstract void initData();

    //初始化控件
    protected abstract void initView(View view);

    //创建布局文件
    public abstract int createLayout();


    /**
     * 创建Fragment
     *
     * @param param1
     * @param fragmentClass
     * @return
     */
    public static Fragment newInstance(String param1, Class<?> fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            Bundle args = new Bundle();
            args.putString("", param1);
            fragment.setArguments(args);
        } catch (java.lang.InstantiationException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    //视图销毁
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("liangxq", "onDestroyView: " + this.getClass().getName());
        if(unbinder!=null){
            unbinder.unbind();
        }
        ((ViewGroup)rootView.getParent()).removeView(rootView);
    }


    //fragment销毁
    @Override
    public void onDetach() {

        super.onDetach();
        Log.e("liangxq", "onDetach: " + this.getClass().getName());
    }
}
