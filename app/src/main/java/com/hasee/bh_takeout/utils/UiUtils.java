package com.hasee.bh_takeout.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

import com.hasee.bh_takeout.MyApplication;

/**
 * Created by itheima.
 */

public class UiUtils {
    public static Context getContext() {
        return MyApplication.getContext();
    }
    public static Resources getResources() {
        return getContext().getResources();
    }

    public static int STATUE_BAR_HEIGHT=0;// 记录装填栏的高度

    /**
     * 依据Id查询指定控件的父控件
     * @param v 指定控件
     * @param id 父容器标识
     * @return
     */
    public static ViewGroup getContainder(View v, int id) {
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent.getId() == id) {
            return parent;
        }
        return getContainder(parent, id);
    }

    public static int dip2Px(int dip) {
        /*
        1.  px/(ppi/160) = dp
        2.  px/dp = density
         */
        //取得当前手机px和dp的倍数关系
        float density = getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + .5f);
        return px;
    }
}
