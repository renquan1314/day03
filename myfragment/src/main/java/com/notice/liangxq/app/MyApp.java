package com.notice.liangxq.app;

import android.app.Application;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.app
 * 文件名：MyApp
 * 创建者：liangxq
 * 创建时间：2019/10/22  21:45
 * 描述：TODO
 */
public class MyApp extends Application{
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp=this;
    }

    public static MyApp getMyApp(){
        return myApp;
    }
}
