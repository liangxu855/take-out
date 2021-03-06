package com.hasee.bh_takeout.AddressManage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hasee.bh_takeout.AddressManage.adapter.AddressAdapter;
import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.AddressDao;
import com.hasee.bh_takeout.model.dao.bean.AddressBean;
import com.hasee.bh_takeout.model.dao.bean.UserBean;
import com.hasee.bh_takeout.model.dao.bean.UserDao;
import com.hasee.bh_takeout.ui.activity.BaseActivity;

import java.util.List;

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
    private AddressAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_address);
        ButterKnife.inject(this);

        UserDao  userDao = new UserDao(this);
        List<UserBean> all = userDao.findAll();
        UserBean userBean = all.get(all.size() - 1);

        AddressDao addressDao = new AddressDao(this);

        AddressBean addressBean1 = new AddressBean("是大腿", "男", "13546768498", "北京市丰台区", "科学城恒富终结", "家", 0, 0);
        addressBean1.user = userBean;
        addressDao.addAddress(addressBean1);
        List<AddressBean> bean = addressDao.findAll();
        if(bean.size()==0){
            bean.add(addressBean1);
        }
        rvReceiptAddress.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new AddressAdapter(this,bean);
        rvReceiptAddress.setAdapter(adapter);

        //adapter.notifyDataSetChanged();
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyApplication.getContext(),NewAddressActivity.class);
                intent.putExtra("type",0);
                startActivityForResult(intent,49);
            }
        });
    }

}
