package com.example.star.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.star.R;
import com.example.star.constant.StrConstant;
import com.example.star.ui.base.BaseActivity;
import com.example.star.utils.log.LogUtils;

public class MainActivity extends BaseActivity {

    private SharedPreferences mSharedPreferences = null;
    private String userToken = null;
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getBundleExtras(Bundle exras) {

    }
    @Override
    protected void initViewsAndEvents() {
        mSharedPreferences = this.getSharedPreferences(StrConstant.USERINFO_NAME, MODE_PRIVATE);
        userToken = mSharedPreferences.getString(StrConstant.USERINFO_TOKEN, "");
        LogUtils.i(userToken);
    }
}