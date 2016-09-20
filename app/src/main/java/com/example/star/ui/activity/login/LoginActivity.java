package com.example.star.ui.activity.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.R;
import com.example.star.constant.StrConstant;
import com.example.star.ui.activity.MainActivity;
import com.example.star.ui.activity.login.presenter.LoginPresenterImpl;
import com.example.star.ui.activity.login.view.ILoginCallBack;
import com.example.star.ui.activity.register.RegisterActivity;
import com.example.star.ui.base.BaseActivity;
import com.example.star.view.loading.PopUpLoading;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by：Cral-Gates on 16/8/27 12:15
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/27
 * Description:
 */
public class LoginActivity extends BaseActivity implements ILoginCallBack {

    @Bind(R.id.login) Button btnLogin;
    @Bind(R.id.username) EditText edtUserName;
    @Bind(R.id.password) EditText edtPassword;
    @Bind(R.id.register) TextView txtRegister;

    private String userName;
    private String passWord;

    private SharedPreferences mSharedPreferences = null;

    private PopUpLoading mPopUpLoading = null;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void getBundleExtras(Bundle exras) {

    }

    @Override
    protected void initViewsAndEvents() {
        mPopUpLoading = new PopUpLoading(this);
    }

    @OnClick({R.id.login, R.id.register})
    public void clickListener(View view) {
        switch (view.getId()) {
            case R.id.register:
                readyGoThenKill(RegisterActivity.class);
                break;
            case R.id.login:
                showProgress();
                userName = edtUserName.getText().toString();
                passWord = edtPassword.getText().toString();
                new LoginPresenterImpl(this).login(userName, passWord);
                break;
        }
    }

    @Override
    public void showProgress() {
        mPopUpLoading.show();
    }

    @Override
    public void hideProgress() {
        mPopUpLoading.dismiss();
    }

    @Override
    public void setUsernameError() {
        Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPasswordError() {
        Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void readyGoHome() {
        readyGoThenKill(MainActivity.class);
    }
    @Override
    public void storageUserInfo(String token) {
        mSharedPreferences = this.getSharedPreferences(StrConstant.USERNAME_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(StrConstant.USERINFO_TOKEN, token);
    }
}
