package com.hasee.bh_takeout.AddressManage.ui;

import android.content.Intent;
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

import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.AddressDao;
import com.hasee.bh_takeout.model.dao.bean.AddressBean;
import com.hasee.bh_takeout.ui.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class NewAddressActivity extends BaseActivity implements View.OnClickListener{
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
    private AddressDao addressDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_receipt_address);
        ButterKnife.inject(this);
        super.onCreate(savedInstanceState);
        btOk.setOnClickListener(this);
        ibBack.setOnClickListener(this);
        ibDeleteAddress.setOnClickListener(this);

        addressDao = new AddressDao(MyApplication.getContext());
        tvReceiptAddress.setText("昌平区北七家");
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        if(type == 0 ){
            tvTitle.setText("新增地址");
            rbMan.isChecked();
            ibDeleteAddress.setVisibility(View.GONE);
        }else{
            tvTitle.setText("修改地址");
            ibDeleteAddress.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_ok:
                String name = etName.getText().toString().trim();
                String phoneNumber = etPhone.getText().toString().trim();
                String receiptAddress = tvReceiptAddress.getText().toString().trim();
                String detailAddress = etDetailAddress.getText().toString().trim();
                int checkedRadioButtonId = rgSex.getCheckedRadioButtonId();
                int rb_man = R.id.rb_man;
                int rb_women = R.id.rb_women;
                String sex = "";
                if(checkedRadioButtonId == rb_man){
                    sex = "男";
                }else if(checkedRadioButtonId == rb_women){
                    sex = "女";
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(NewAddressActivity.this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(NewAddressActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(receiptAddress)||TextUtils.isEmpty(detailAddress)){
                    Toast.makeText(NewAddressActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
                }


                AddressBean addressBean = new AddressBean(name,sex,phoneNumber,receiptAddress,detailAddress,"家",0,0);
                addressDao.addAddress(addressBean);
                break;
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_delete_address:
                addressDao.delete(0);
                break;
        }
    }
}
