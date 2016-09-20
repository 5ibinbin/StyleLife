package com.example.star.view.loading;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.star.R;
import com.example.star.view.popup.CustomPopupwindow;

/**
 * Created by：Cral-Gates on 16/9/19 19:08
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/19
 * Description:
 */
public class PopUpLoading extends CustomPopupwindow{

    private View contentView;

    public PopUpLoading(Context context) {
        super(context);
        this.mActivity = (Activity) context;
    }

    @Override
    public void init() {
        super.defaultSetting();
        this.setAnimationStyle(R.style.NoAnimation);
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        contentView = inflater.inflate(R.layout.loading, null);
        setContentView(contentView);
    }
}

