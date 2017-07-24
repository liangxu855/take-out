package com.hasee.bh_takeout.AddressManage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/23 0023.
 */

public class ReceiptAddressActivity extends BaseActivity {
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rv_receipt_address)
    RecyclerView rvReceiptAddress;
    @InjectView(R.id.ll_add_address)
    LinearLayout llAddAddress;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_receipt_address);
        ButterKnife.inject(this);
        super.onCreate(savedInstanceState);

    }

}
