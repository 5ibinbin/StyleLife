package com.example.star.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.star.R;

/**
 * Created by：Cral-Gates on 16/9/19 19:10
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/19
 * Description:
 */
public abstract class CustomPopupwindow extends PopupWindow{

    public Activity mActivity;
    public int defaultGravity = Gravity.CENTER;

    public CustomPopupwindow(Context context) {
        super(context);
        this.mActivity = (Activity) context;
        init();
    }

    public abstract void init();

    public void defaultSetting() {
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(mActivity.getWindowManager().getDefaultDisplay().getWidth() * 3/4 +100);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.Animation);
        ColorDrawable cd = new ColorDrawable(0x000000);
        this.setBackgroundDrawable(cd);
    }

    public void backgroundAplha(float alpha){
        WindowManager.LayoutParams lp=mActivity.getWindow().getAttributes();
        lp.alpha = alpha;
        mActivity.getWindow().setAttributes(lp);
    }

    public void show(){
        backgroundAplha(0.4f);
        if (!this.isShowing()){
            showAtLocation(mActivity.getWindow().getDecorView(), defaultGravity, 0, 0);
        }else{
            dismiss();
        }
    }

    public void setDefaultGravity(int gravity){
        this.defaultGravity = gravity;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void dismiss() {
        backgroundAplha(1.0f);
        super.dismiss();
    }
}
