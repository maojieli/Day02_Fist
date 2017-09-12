package com.jiyun.asus.day02_fist.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jiyun.asus.day02_fist.SplashActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/9/12.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private final ArrayList<View> views;
    private final SplashActivity splashActivity;

    public ViewPagerAdapter(ArrayList<View> views, SplashActivity splashActivity) {
        this.views = views;
        this.splashActivity = splashActivity;
    }


    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
