package com.hasee.bh_takeout.AddressManage.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hasee.bh_takeout.AddressManage.ui.NewAddressActivity;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.bean.AddressBean;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/23 0023.
 */

public class AddressAdapter extends RecyclerView.Adapter {

    private List<AddressBean> allAddressInfo;
    private Activity activity;
    public AddressAdapter(Activity activity,List<AddressBean> bean) {
        this.activity = activity;
        this.allAddressInfo = bean;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // View view = View.inflate(MyApplication.getContext(), R.layout.item_receipt_address, parent);
        View view = LayoutInflater.from(activity).inflate(R.layout.item_receipt_address, parent, false);

//        AddressDao addressDao = new AddressDao(activity);
//        allAddressInfo = addressDao.findAll();


        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final AddressViewHolder addressViewHolder = (AddressViewHolder) holder;
        AddressBean addressBean = null;
        String phoneNumber = null;
        String name = null;
        String sex= null;
        String label = null;
        String detailAddress= null;
        String receiptAddress= null;
        if(allAddressInfo.size() != 0){
        addressBean = allAddressInfo.get(position);
        phoneNumber = addressBean.phone;
        name = addressBean.name;
        sex = addressBean.sex;
        label = addressBean.label;
        detailAddress = addressBean.detailAddress;//详细地址
        receiptAddress = addressBean.receiptAddress;
        addressViewHolder.ivEdit.setVisibility(View.VISIBLE);
        }else{
            name  = "收货人";
            phoneNumber = "无";
            sex = "无";
            detailAddress = "无";
            receiptAddress = "无";
            label = "无";
            addressViewHolder.ivEdit.setVisibility(View.GONE);
        }

        addressViewHolder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = addressViewHolder.getAdapterPosition();
                Intent intent = new Intent(activity,NewAddressActivity.class);
                intent.putExtra("type",1);
                intent.putExtra("id",allAddressInfo.get(position)._id);
                activity.startActivityForResult(intent,50);
            }
        });
        addressViewHolder.tvLabel.setText(label);
        addressViewHolder.tvPhone.setText(phoneNumber);
        addressViewHolder.tvName.setText(name);
        addressViewHolder.tvSex.setText(sex);
        addressViewHolder.tvAddress.setText(detailAddress);
    }

    @Override
    public int getItemCount() {
        if(allAddressInfo.size()==0){
            return 1;
        }
        return allAddressInfo.size();

    }


    class AddressViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_sex)
        TextView tvSex;
        @InjectView(R.id.tv_phone)
        TextView tvPhone;
        @InjectView(R.id.tv_label)
        TextView tvLabel;
        @InjectView(R.id.tv_address)
        TextView tvAddress;
        @InjectView(R.id.iv_edit)
        ImageView ivEdit;

        public AddressViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
