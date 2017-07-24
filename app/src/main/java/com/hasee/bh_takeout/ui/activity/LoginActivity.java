package com.hasee.bh_takeout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @InjectView(R.id.iv_user_password_login)
    TextView ivUserPasswordLogin;
    @InjectView(R.id.tv_user_code)
    TextView tvUserCode;
    @InjectView(R.id.login)
    TextView login;
    @InjectView(R.id.imageView)
    ImageView imageView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.iv_user_back, R.id.iv_user_password_login, R.id.tv_user_code, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_back:

                break;
            case R.id.iv_user_password_login:

                break;
            case R.id.tv_user_code:
                break;
            case R.id.login:
                break;
        }
    }
}
