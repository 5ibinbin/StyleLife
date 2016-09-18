package com.example.star.ui.activity.guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.star.R;
import com.example.star.constant.StrConstant;
import com.example.star.ui.activity.login.LoginActivity;
import com.example.star.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by：Cral-Gates on 16/9/3 07:58
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/3
 * Description:
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @Bind(R.id.start_access)
    Button mStart_login;
    private ViewPagerAdapter mViewPagerAdapter;
    /*
    * 存放视图
    * */
    private List<View> mViews;
    /*
    * 小圆点
    * */
    private ImageView[] dots;
    /*
    * 当前位置
    * */
    private int currentIndex;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void getBundleExtras(Bundle exras) {

    }

    @Override
    protected void initViewsAndEvents() {
        initViews();
        initDots();
    }

    @Override
    protected void getLoadingTargetView() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setDotsSelect(position);
        if(position == 3){
            for (int i = 0; i< dots.length; i++){
                dots[i].setVisibility(View.GONE);
            }
            mStart_login.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i< dots.length; i++){
                dots[i].setVisibility(View.VISIBLE);
            }
            mStart_login.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void initViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        mViews = new  ArrayList<>();
        mViews.add(layoutInflater.inflate(R.layout.viewpager_one, null));
        mViews.add(layoutInflater.inflate(R.layout.viewpager_two, null));
        mViews.add(layoutInflater.inflate(R.layout.viewpager_three, null));
        mViews.add(layoutInflater.inflate(R.layout.viewpager_four, null));

        mViewPagerAdapter = new ViewPagerAdapter(mViews, this);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    @OnClick({R.id.start_access})
    public void clickListener(View view){
        switch (view.getId()){
            case R.id.start_access:
                readyGoThenKill(LoginActivity.class);
                setGuide();
                break;
        }
    }

    private void initDots() {
        dots = new ImageView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++){
            dots[i] = (ImageView)mLinearLayout.getChildAt(i);
            dots[i].setEnabled(true);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    private void setDotsSelect(int position) {
        if (position < 0 || position > mViews.size() - 1 || currentIndex == position){
            return;
        }
        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = position;
    }

    private void setGuide() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(StrConstant.SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StrConstant.ISFIRSTIN,false);
        editor.commit();
    }
}
