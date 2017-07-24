package com.hasee.bh_takeout.AddressManage.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hasee.bh_takeout.AddressManage.ui.EditAddressActivity;
import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.AddressDao;
import com.hasee.bh_takeout.model.dao.bean.AddressBean;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/7/23 0023.
 */

public class AddressAdapter extends RecyclerView.Adapter {

    private List<AddressBean> allAddressInfo;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Activity activity = (Activity) MyApplication.getContext();
        AddressDao addressDao = new AddressDao(activity);
        allAddressInfo = addressDao.findAll();
        // View view = View.inflate(MyApplication.getContext(), R.layout.item_receipt_address, parent);
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.item_receipt_address, parent, false);
        final AddressViewHolder holder = new AddressViewHolder(view);
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(activity,EditAddressActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("AddressInfo", (Serializable) allAddressInfo.get(position));
                intent.putExtras(bundle);
                activity.startActivityForResult(intent,50);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AddressBean addressBean = allAddressInfo.get(position);
        String phoneNumber = addressBean.phone;
        String name = addressBean.name;
        String sex = addressBean.sex;
        String label = addressBean.label;
        String detailAddress = addressBean.detailAddress;//详细地址
        String receiptAddress = addressBean.receiptAddress;

        AddressViewHolder addressViewHolder = (AddressViewHolder) holder;
        addressViewHolder.tvLabel.setText(label);
        addressViewHolder.tvPhone.setText(phoneNumber);
        addressViewHolder.tvName.setText(name);
        addressViewHolder.tvSex.setText(sex);
        addressViewHolder.tvAddress.setText(detailAddress);
    }

    @Override
    public int getItemCount() {
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

        AddressViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
