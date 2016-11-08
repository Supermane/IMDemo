package com.wpl.imdemo;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by 王鹏龙 on 2016/11/7.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 初始化融云
         */
        RongIM.init(this);
    }
}
