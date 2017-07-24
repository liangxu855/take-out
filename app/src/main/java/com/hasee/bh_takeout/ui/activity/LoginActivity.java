package com.hasee.bh_takeout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hasee.bh_takeout.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 登录界面
 * Created by Hasee on 2017/7/24.
 */

public class LoginActivity extends BaseActivity {
    @InjectView(R.id.iv_user_back)
    ImageView ivUserBack;
    @InjectView(R.id.tv_user_code)
    TextView tvUserCode;
    @InjectView(R.id.login)
    TextView login;
    @InjectView(R.id.tv_user_login_type_change)
    TextView tvUserLoginTypeChange;

    /*------------ 短信登录 ------------*/
    @InjectView(R.id.ll_login_phone)
    LinearLayout llLoginPhone;
    @InjectView(R.id.et_user_code)
    EditText etUserCode;
    @InjectView(R.id.et_user_phone)
    EditText etUserPhone;

    /*------------ 密码登录 ------------*/
    @InjectView(R.id.et_user_username)
    EditText etUserUsername;
    @InjectView(R.id.et_user_password)
    EditText etUserPassword;
    @InjectView(R.id.ll_login_password)
    LinearLayout llLoginPassword;

    /*------------ 密码登陆：1；短信验证：2 ------------*/
    public static int LOGINUSEPASSWORD = 1;
    public static int LOGINUSEPHONE = 2;
    public int loginType = LOGINUSEPHONE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.iv_user_back, R.id.tv_user_login_type_change, R.id.tv_user_code, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_user_login_type_change:
                changeLoginType();
                break;
            case R.id.iv_user_back:
                finish();
                break;
            case R.id.tv_user_code:
                break;
            case R.id.login:
                onLogin();
                break;
        }
    }

    private void onLogin() {

    }


    private void changeLoginType() {
        if (loginType == LOGINUSEPHONE) {
            loginType = LOGINUSEPASSWORD;
            tvUserLoginTypeChange.setText("短信登录");
            llLoginPhone.setVisibility(View.GONE);
            llLoginPassword.setVisibility(View.VISIBLE);

        } else if (loginType == LOGINUSEPASSWORD) {
            loginType = LOGINUSEPHONE;
            tvUserLoginTypeChange.setText("密码登录");
            llLoginPhone.setVisibility(View.VISIBLE);
            llLoginPassword.setVisibility(View.GONE);
        }
    }
}
