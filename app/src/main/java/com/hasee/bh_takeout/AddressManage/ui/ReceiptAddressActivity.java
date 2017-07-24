package com.hasee.bh_takeout.AddressManage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hasee.bh_takeout.AddressManage.adapter.AddressAdapter;
import com.hasee.bh_takeout.MyApplication;
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
        AddressAdapter adapter = new AddressAdapter();
        rvReceiptAddress.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        llAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(),NewAddressActivity.class);
                startActivityForResult(intent,49);
            }
        });
    }

}
