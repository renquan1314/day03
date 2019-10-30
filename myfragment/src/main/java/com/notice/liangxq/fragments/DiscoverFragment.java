package com.notice.liangxq.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.notice.liangxq.bean.Bean;
import com.notice.liangxq.http.BaseObserver;
import com.notice.liangxq.http.BaseResponse;
import com.notice.liangxq.http.HttpManager;
import com.notice.liangxq.http.MyServer;
import com.notice.liangxq.utils.RxUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends BaseFragment {



    @Override
    protected void initData() {
        HttpManager.getInstance().getServer(MyServer.URL, MyServer.class).get()
                .compose(RxUtils.<BaseResponse<List<Bean>>>rxScheduleThread())
                .compose(RxUtils.<List<Bean>>rxChangeResult())
                .subscribe(new BaseObserver<List<Bean>>() {
            @Override
            public void onSucess(List<Bean> beans) {

                Log.e("liangxq", "onSucess: "+beans.toString() );
            }

            @Override
            public void onFail(String error) {
                Log.e("liangxq", "onSucess: "+error);
            }
        });
    }

    @Override
    protected void initView(View view) {
    }

    @Override
    public int createLayout() {
        return R.layout.fragment_discover;
    }


}
