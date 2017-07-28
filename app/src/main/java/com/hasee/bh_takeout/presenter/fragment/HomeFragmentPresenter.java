package com.hasee.bh_takeout.presenter.fragment;

import com.google.gson.Gson;
import com.hasee.bh_takeout.bean.HomeInfo;
import com.hasee.bh_takeout.bean.ResponseInfo;
import com.hasee.bh_takeout.presenter.BasePresenter;
import com.hasee.bh_takeout.ui.fragment.HomeFragment;

import retrofit2.Call;


/**
 * 首页的业务处理
 */

public class HomeFragmentPresenter extends BasePresenter {

    private HomeFragment fragment;

    public HomeFragmentPresenter(HomeFragment fragment) {
        this.fragment = fragment;
    }
    /**
     * 获取首页数据的步骤：
     * 1.需要在联网的API接口中增加一个获取首页数据的方法（访问方式和请求参数配置）
     * 2.异步获取首页数据
     * 3.数据处理
     * 4.展示数据到界面
     */


    /**
     * 获取服务器端首页数据
     */
    public void getData() {
        Call<ResponseInfo> call = responseInfoAPI.home();
        call.enqueue(new CallbackAdapter());
    }

    /**
     * 错误
     * @param msg
     */
    protected void failed(String msg) {
        fragment.failed(msg);
    }

    /**
     * 解析服务器返回数据
     * @param data
     */
    public  void parserData(String data) {
        // 解析数据：data
        Gson gson=new Gson();
        HomeInfo info = gson.fromJson(data, HomeInfo.class);
      //  fragment.success(info);// 更新界面
        fragment.getMAdapter().setData(info);

    }
}
