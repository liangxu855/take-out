package com.hasee.bh_takeout;

import android.app.Application;
import android.content.Context;

import com.mob.MobApplication;

/**
 * Created by itheima.
 */

public class MyApplication extends MobApplication {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

    }


   


}
