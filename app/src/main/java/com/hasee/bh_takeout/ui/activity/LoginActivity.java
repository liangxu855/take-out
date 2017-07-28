package com.hasee.bh_takeout.ui.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.model.dao.bean.UserBean;
import com.hasee.bh_takeout.model.dao.bean.UserDao;
import com.hasee.bh_takeout.presenter.fragment.LoginPresenter;
import com.hasee.bh_takeout.ui.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

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
    /*------------ 短信验证相关 ------------*/
    private EventHandler eventHandler;
    private int TIME = 60;//倒计时60s这里应该多设置些因为mob后台需要60s,我们前端会有差异的建议设置90，100或者120
    public String country = "86";//这是中国区号，如果需要其他国家列表，可以使用getSupportedCountries();获得国家区号
    private String phone;
    private static final int CODE_REPEAT = 1; //重新发送

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initSMSSDK();
        loginPresenter = new LoginPresenter(this);
        userDao = new UserDao(this);
    }

    private void initSMSSDK() {
        //回调完成
        //提交验证码成功
        //获取验证码成功
        //返回支持发送验证码的国家列表
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        toast("验证成功");
                        loginPresenter.getData(phone, 2);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        toast("获取验证码成功");
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    String str = data.toString();
                    toast(str);
                }
            }
        };

        SMSSDK.registerEventHandler(eventHandler); //注册短信回调
    }

    //吐司的一个小方法
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
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
                //  etUserCode.setText("123456");
                sendMessage();
                break;
            case R.id.login:
                loginData();
                break;
        }
    }

    private void sendMessage() {
        phone = etUserPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            //定义需要匹配的正则表达式的规则
            String REGEX_MOBILE_SIMPLE = "[1][3578]\\d{9}";
            //把正则表达式的规则编译成模板
            Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
            //把需要匹配的字符给模板匹配，获得匹配器
            Matcher matcher = pattern.matcher(phone);
            // 通过匹配器查找是否有该字符，不可重复调用重复调用matcher.find()
            if (matcher.find()) {//匹配手机号是否存在
                alterWarning();
            } else {
                toast("手机号格式错误");
            }
        } else {
            toast("请先输入手机号");
        }
    }

    //弹窗确认下发
    private void alterWarning() {
        // 2. 通过sdk发送短信验证
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setMessage("我们将要发送到" + phone + "验证"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                // 2. 通过sdk发送短信验证（请求获取短信验证码，在监听（eh）中返回）
                SMSSDK.getVerificationCode(country, phone);
                //做倒计时操作
                Toast.makeText(LoginActivity.this, "已发送" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, "已取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
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
            String code = etUserCode.getText().toString().trim();
            String phone = etUserPhone.getText().toString().trim();
            if (!TextUtils.isEmpty(code)||TextUtils.isEmpty(phone)) {
                //验证
                SMSSDK.submitVerificationCode( country,  phone,  code);
            } else {
                Toast.makeText(this, "手机号或验证码不能为空", Toast.LENGTH_SHORT).show();
                return;
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
        String phone = etUserPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)){
            userBean.setPhone(phone);
        }
        Toast.makeText(this, info.toString(), Toast.LENGTH_SHORT).show();
        info.login = true;
        List<UserBean> userBeanList = userDao.findAll();
        if (userBeanList == null) {
            userDao.addUserBean(info);
        } else {
            for (UserBean user : userBeanList) {
                if (TextUtils.equals(user.getName(), info.getName())) {
                    userDao.update(info);
                    break;
                }
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

    public interface OnSendMessageHandler {

        //#if def{lang} == cn
        /**
         * 此方法在发送验证短信前被调用，传入参数为接收者号码
         * 返回true表示此号码无须实际接收短信
         */
        //#elif def{lang} == en

        /**
         * This method will be called before verification message being to sent,
         * input params are the message receiver
         * return true means this number won't actually receive the message
         */
        //#endif
        public boolean onSendMessage(String country, String phone);

    }
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onResume() {
        super.onResume();
        if(isNeedCheck){
            checkPermissions(needPermissions);
        }
    }

    /**
     *
     * @since 2.5.0
     *
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param
     * @return
     * @since 2.5.0
     *
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     *
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                showMissingPermissionDialog();
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     *
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("权限申请");
        builder.setMessage("aaa");

        // 拒绝, 退出应用
        builder.setNegativeButton("拒绝",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton("同意",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }

    /**
     *  启动应用的设置
     *
     * @since 2.5.0
     *
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
