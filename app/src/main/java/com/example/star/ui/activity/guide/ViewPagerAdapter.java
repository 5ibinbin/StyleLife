package com.example.star.ui.activity.guide;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by：Cral-Gates on 16/9/3 08:22
 * EMail：zhangxia2013105@gmail.com
 * Date: 16/9/3
 * Description:
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> mViews;
    private Activity mActivity;

    public ViewPagerAdapter(List<View> views, GuideActivity activity) {
        mViews = views;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mViews != null ?mViews.size():0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView((mViews.get(position)));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}


