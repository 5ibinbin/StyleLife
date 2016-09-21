package com.example.star.ui.activity.login.view;

import com.example.star.model.entity.LoginResult;

/**
 * Created by：Cral-Gates on 16/9/13 22:56
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/13
 * Description:
 */
public interface ILoginCallBack {

    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void readyGoHome();

    void storageUserInfo(LoginResult loginResult);
}
