package com.hasee.bh_takeout.AddressManage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

class NewAddressActivity extends BaseActivity {
    @InjectView(R.id.ib_back)
    ImageButton ibBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ib_delete_address)
    ImageButton ibDeleteAddress;
    @InjectView(R.id.rl_left)
    TextView rlLeft;
    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.rb_man)
    RadioButton rbMan;
    @InjectView(R.id.rb_women)
    RadioButton rbWomen;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.ib_delete_phone)
    ImageButton ibDeletePhone;
    @InjectView(R.id.tv_receipt_address)
    TextView tvReceiptAddress;
    @InjectView(R.id.et_detail_address)
    EditText etDetailAddress;
    @InjectView(R.id.tv_label)
    TextView tvLabel;
    @InjectView(R.id.ib_select_label)
    ImageView ibSelectLabel;
    @InjectView(R.id.bt_ok)
    Button btOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_receipt_address);
        ButterKnife.inject(this);
        super.onCreate(savedInstanceState);

    }
}
