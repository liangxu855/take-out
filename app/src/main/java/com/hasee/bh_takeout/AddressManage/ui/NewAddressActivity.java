package com.hasee.bh_takeout.AddressManage.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.bean.AddressBean;
import com.hasee.bh_takeout.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class NewAddressActivity extends BaseActivity {
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
        btOk.setOnClickListener(onClickListener);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = etName.getText().toString().trim();
            String phoneNumber = etPhone.getText().toString().trim();
            String receiptAddress = tvReceiptAddress.getText().toString().trim();
            String detailAddress = etDetailAddress.getText().toString().trim();
            int checkedRadioButtonId = rgSex.getCheckedRadioButtonId();
            if(TextUtils.isEmpty(name)){
                Toast.makeText(NewAddressActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(NewAddressActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            }
//            if(TextUtils.isEmpty(receiptAddress)||TextUtils.isEmpty(detailAddress)){
//                Toast.makeText(NewAddressActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
//            }
            AddressBean addressBean = new AddressBean(name,null,phoneNumber,receiptAddress,detailAddress,null,0,0);
            //AddressDao addressDao = new AddressDao(MyApplication.getContext());
            //addressDao.addAddress(addressBean);
        }
    };
}
