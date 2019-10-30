package com.example.ren.wanandroid.app;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by ren on 2019/10/23.
 */

public class MyApp extends Application {
    private static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        CrashReport.initCrashReport(getApplicationContext(), "4562daea85", true);
    }

    public static MyApp getMyApp(){
        return myApp;
    }
}
