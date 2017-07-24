package com.hasee.bh_takeout.presenter.fragment;

import com.google.gson.Gson;
import com.hasee.bh_takeout.bean.ResponseInfo;
import com.hasee.bh_takeout.model.dao.bean.UserBean;
import com.hasee.bh_takeout.presenter.BasePresenter;
import com.hasee.bh_takeout.ui.activity.LoginActivity;

import retrofit2.Call;

/**
 * Created by Hasee on 2017/7/24.
 */

public class LoginPresenter extends BasePresenter {

    private LoginActivity loginActivity;

    public LoginPresenter(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    /**
     * 获取登录数据(账号密码)
     */
    public void getData(String username, String password) {
        Call<ResponseInfo> call = responseInfoAPI.login(username, password);
        call.enqueue(new CallbackAdapter());
    }
      /**
     * 获取登录数据(短信)
     */
    public void getData(String phone,int type) {
        Call<ResponseInfo> call = responseInfoAPI.login(phone, type);
        call.enqueue(new CallbackAdapter());
    }


    @Override
    protected void failed(String msg) {
        loginActivity.failed(msg);
    }

    @Override
    protected void parserData(String data) {
        // 解析数据：data
        Gson gson = new Gson();
        UserBean info = gson.fromJson(data, UserBean.class);
        loginActivity.success(info);// 更新界面
    }
}
