package com.example.star.ui.activity.guide;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.star.R;
import com.example.star.constant.IntConstant;
import com.example.star.constant.StrConstant;
import com.example.star.ui.activity.login.LoginActivity;
import com.example.star.ui.base.BaseActivity;

/**
 * Created by：Cral-Gates on 16/9/10 08:29
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/10
 * Description:
 */
public class SplashActivity extends BaseActivity{

    private boolean isFirstIn = false;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case IntConstant.GO_GUIDE:
                    readyGoThenKill(GuideActivity.class);
                    break;
                case IntConstant.GO_MAIN:
                    readyGoThenKill(LoginActivity.class);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getBundleExtras(Bundle exras) {

    }

    @Override
    protected void initViewsAndEvents() {
        init();
    }
    private void init() {

        SharedPreferences preferences = getSharedPreferences(
                StrConstant.SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        isFirstIn = preferences.getBoolean(StrConstant.ISFIRSTIN, true);

        if (isFirstIn){
            mHandler.sendEmptyMessageDelayed(IntConstant.GO_GUIDE, IntConstant.SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(IntConstant.GO_MAIN, IntConstant.SPLASH_DELAY_MILLIS);
        }
    }

}

