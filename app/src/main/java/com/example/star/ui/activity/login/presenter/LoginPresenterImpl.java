package com.example.star.ui.activity.login.presenter;

import com.example.star.model.entity.LoginResult;
import com.example.star.model.http.RestClient;
import com.example.star.ui.activity.login.view.ILoginCallBack;
import com.example.star.ui.activity.login.view.ILoginPersenter;
import com.example.star.utils.json.GsonUtils;
import com.example.star.utils.log.LogUtils;

import rx.Subscriber;

/**
 * Created by：Cral-Gates on 16/9/18 20:24
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/18
 * Description:
 */
public class LoginPresenterImpl implements ILoginPersenter {

    private ILoginCallBack mILoginCallBack = null;

    public LoginPresenterImpl(ILoginCallBack ILoginCallBack) {
        mILoginCallBack = ILoginCallBack;
    }

    @Override
    public void login(String username, String psw) {
        if (username.isEmpty()){
            mILoginCallBack.setUsernameError();
            mILoginCallBack.hideProgress();
            return;
        }
        if (psw.isEmpty()){
            mILoginCallBack.setPasswordError();
            mILoginCallBack.hideProgress();
            return;
        }
        RestClient.getInstance().login(username, psw, new Subscriber<LoginResult>() {
            @Override
            public void onCompleted() {
                mILoginCallBack.hideProgress();
                mILoginCallBack.readyGoHome();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e(GsonUtils.toJson(e));
                mILoginCallBack.hideProgress();
            }

            @Override
            public void onNext(LoginResult loginResult) {
                mILoginCallBack.showProgress();
                mILoginCallBack.storageUserInfo(loginResult);
                LogUtils.d(GsonUtils.toJson(loginResult));
            }
        });
    }
}
