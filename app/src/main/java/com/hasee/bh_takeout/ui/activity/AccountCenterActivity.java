package com.hasee.bh_takeout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hasee.bh_takeout.R;

/**
 * Created by laidoudou on 2017/7/26.
 */

public class AccountCenterActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这是订单支付的界面
        setContentView(R.layout.account_center_one);
    }
}
