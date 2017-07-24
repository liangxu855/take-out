package com.hasee.bh_takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hasee.bh_takeout.AddressManage.ui.ReceiptAddressActivity;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.bean.User;
import com.hasee.bh_takeout.model.dao.bean.UserBean;
import com.hasee.bh_takeout.ui.activity.LoginActivity;
import com.hasee.bh_takeout.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by ywf on 2017/3/24.
 */
public class UserFragment extends Fragment {
    @InjectView(R.id.tv_user_setting)
    ImageView tvUserSetting;
    @InjectView(R.id.iv_user_notice)
    ImageView ivUserNotice;
    @InjectView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @InjectView(R.id.login)
    ImageView login;
    @InjectView(R.id.username)
    TextView username;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.ll_userinfo)
    LinearLayout llUserinfo;
    @InjectView(R.id.address)
    ImageView address;

    public static final int REQUESTCODE = 1;

    public boolean isLogin = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.login, R.id.address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                /*------------ 进入登录页面 ------------*/
                toLogin();
                break;
            case R.id.address:
                /*------------ 进入地址列表界面 ------------*/
                toAddress();
                break;
        }
    }

    private void toLogin() {
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivityForResult(intent,REQUESTCODE);
    }

    private void toAddress() {
        if (isLogin){
            Intent intent = new Intent(getContext(),ReceiptAddressActivity.class);
            startActivity(intent);
        }else{
            toLogin();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUESTCODE:
                isLogin = true;
                UserBean user = (UserBean) data.getSerializableExtra("info");
                login.setVisibility(View.GONE);
                llUserinfo.setVisibility(View.VISIBLE);
                if (!TextUtils.isEmpty(user.getName())){
                    username.setText(user.getName());
                }
                if (!TextUtils.isEmpty(user.getPhone())){
                    phone.setText(user.getPhone());
                }
                break;
        }
    }
}
