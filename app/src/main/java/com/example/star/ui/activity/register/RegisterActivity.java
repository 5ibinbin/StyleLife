package com.example.star.ui.activity.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.star.R;
import com.example.star.constant.IntConstant;
import com.example.star.ui.activity.MainActivity;
import com.example.star.ui.activity.login.LoginActivity;
import com.example.star.ui.base.BaseActivity;
import com.example.star.utils.json.GsonUtils;
import com.example.star.utils.log.LogUtils;
import com.example.star.utils.timecount.TimeCountUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by：Cral-Gates on 16/9/12 16:42
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/12
 * Description:
 */
public class RegisterActivity extends BaseActivity{

    @Bind(R.id.backLogin) TextView backLogin;
    @Bind(R.id.set_code) TextView resetCode;
    @Bind(R.id.editPhone) EditText mEditPhone;
    @Bind(R.id.editCode) EditText mEditCode;
    @Bind(R.id.registerMain) Button mBtnRegister;

    private TimeCountUtils mTimeCountUtils;
    private String phoneNum;
    private String codeNum;
    private String telRegex;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void getBundleExtras(Bundle exras) {

    }

    @Override
    protected void initViewsAndEvents() {
        init();
    }

    @Override
    protected void getLoadingTargetView() {

    }

    private void init() {
        telRegex = "[1][3578]\\d{9}";
    }

    @OnClick({R.id.backLogin, R.id.set_code, R.id.registerMain})
    public void clickListener(View view){
        switch (view.getId()){
            case R.id.backLogin:
                readyGoThenKill(LoginActivity.class);
                break;
            case R.id.set_code:
                getCode();
                break;
            case R.id.registerMain:
                getPwd();
                break;
        }
    }

    private void getPwd(){
        codeNum = mEditCode.getText().toString();
        if (codeNum.isEmpty()){
            Toast.makeText(this, R.string.register_ill_code, Toast.LENGTH_SHORT).show();
            return;
        }
        AVUser.signUpOrLoginByMobilePhoneInBackground(phoneNum, codeNum, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                avUser.setPassword(codeNum+"");
                if (e == null){
                    readyGoThenKill(MainActivity.class);
                    LogUtils.i(GsonUtils.toJson(e));
                    LogUtils.i(codeNum);
                }
            }
        });
    }

    private void getCode() {
        phoneNum = mEditPhone.getText().toString();
        if (phoneNum.isEmpty()){
            Toast.makeText(this, R.string.register_null_phone, Toast.LENGTH_SHORT).show();
            return;
        }else if(!phoneNum.matches(telRegex)){
            Toast.makeText(this, R.string.register_ill_phone, Toast.LENGTH_SHORT).show();
            return;
        }else {

            AVOSCloud.requestSMSCodeInBackground(phoneNum, new RequestMobileCodeCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null){
                        mTimeCountUtils = new TimeCountUtils(resetCode, IntConstant.TOTALTIIME, IntConstant.TIMEBREAK);
                        mTimeCountUtils.start();
                    }
                    LogUtils.i(GsonUtils.toJson(e));
                }
            });
        }
    }
}
