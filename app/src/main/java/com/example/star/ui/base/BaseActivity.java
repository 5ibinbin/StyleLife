package com.example.star.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.star.R;
import com.example.star.ui.BaseAppManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by：Cral-Gates on 16/8/27 10:13
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/8/27
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 上下文
     */
    protected Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        /*
        * initialize bundle data
        * */
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            getBundleExtras(extras);
        }
        /*
        * initialize contentView
        * */
        if (getContentViewLayoutId() != 0){
            setContentView(getContentViewLayoutId());
        }else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        /*
        * set view
        * */
        initViewsAndEvents();
        /*
        * set status
        * */
        setStatusBar();
        /*
        * add activity to activityManager
        * */
        BaseAppManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        /*
        * butterKnife bind
        * */
        ButterKnife.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorgreen);
    }
    /*
     * initialize ContentView layout
     * */
    protected abstract int getContentViewLayoutId();
    /*
    * get bundle data
    * */
    protected abstract void getBundleExtras(Bundle exras);
    /*
    * initialize view and add event
    * */
    protected abstract void initViewsAndEvents();
    /*
    * start Activity
    * */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
    /*
    * start Activity then finish
    * */
    protected void readyGoThenKill(Class<?> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        this.finish();
    }
    /*
    * start Activity with bundle then finish
    * */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle){
        Intent intent = new Intent(this, clazz);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }
    /*
    * start Activity for result
    * */
    protected void readyGoForResult(Class<?> clazz, int requestCode){
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }
    /*
    * statusbar
    * */
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags = bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
