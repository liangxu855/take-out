package com.hasee.bh_takeout.AddressManage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hasee.bh_takeout.R;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class EditAddressActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_receipt_address);
        super.onCreate(savedInstanceState);

        this.setResult(123);
    }

}
