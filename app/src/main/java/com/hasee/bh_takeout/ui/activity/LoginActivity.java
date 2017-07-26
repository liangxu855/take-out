package com.hasee.bh_takeout.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.bean.User;
import com.hasee.bh_takeout.model.dao.bean.UserBean;
import com.hasee.bh_takeout.model.dao.bean.UserDao;
import com.hasee.bh_takeout.presenter.api.ResponseInfoAPI;
import com.hasee.bh_takeout.presenter.fragment.LoginPresenter;
import com.hasee.bh_takeout.ui.fragment.UserFragment;

import java.util.List;

import javax.inject.Inject;

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

    LoginPresenter loginPresenter;
    UserDao userDao;
    UserBean userBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        loginPresenter = new LoginPresenter(this);
        userDao = new UserDao(this);
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
                etUserCode.setText("123456");
                break;
            case R.id.login:
                loginData();
                break;
        }
    }

    private void loginData() {
        if (loginType == LOGINUSEPASSWORD) {
            String username = etUserUsername.getText().toString().trim();
            String password = etUserPassword.getText().toString();
            if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else {
                loginPresenter.getData(username, password);
            }

        } else if (loginType == LOGINUSEPHONE) {
            String phone = etUserPhone.getText().toString().trim();
            String code = etUserCode.getText().toString().trim();
            if (TextUtils.isEmpty(phone) && TextUtils.isEmpty(code)) {
                Toast.makeText(this, "手机号或验证码不能为空", Toast.LENGTH_SHORT).show();
                return;
            } else {
                loginPresenter.getData(phone, Integer.parseInt(code));
            }

        }
    }


    public void failed(String msg) {
        etUserUsername.setText("");
        etUserPassword.setText("");
        etUserPhone.setText("");
        etUserCode.setText("");
        Toast.makeText(this, "登陆失败", Toast.LENGTH_SHORT).show();
    }

    public void success(UserBean info) {
        this.userBean = info;

        Toast.makeText(this, info.toString(), Toast.LENGTH_SHORT).show();
        info.login = true;
        List<UserBean> userBeanList = userDao.findAll();
        for (UserBean user : userBeanList) {
            if (TextUtils.equals(user.getName(), info.getName())) {
                userDao.update(info);
                break;
            }
        }
        userDao.addUserBean(info);
        Intent mIntent = new Intent();
        mIntent.putExtra("info", info);
        // 设置结果，并进行传送
        this.setResult(UserFragment.REQUESTCODE, mIntent);
        this.finish();
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

    public UserBean getUserBean() {
        if (userBean != null) {
            return userBean;
        }
        return null;
    }
}
