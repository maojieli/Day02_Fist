package com.jiyun.asus.day02_fist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiyun.asus.day02_fist.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp_container;
    private ViewPagerAdapter viewPagerAdapter;
    private ImageView iv_splash_first;
    private ImageView iv_splash_second;
    private ImageView iv_splash_third;
    private LinearLayout ll_splash_container;
    private Button btn_join;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("count", MODE_WORLD_READABLE);
        int count = sharedPreferences.getInt("count", 0);
        if (count==0){
            initView();
        }else {
            startActivity(new Intent(SplashActivity.this,Two_Activity.class));
            finish();
        }

        initView();
    }

    private void initView() {
        vp_container = (ViewPager) findViewById(R.id.vp_container);
        iv_splash_first = (ImageView) findViewById(R.id.iv_splash_first);
        iv_splash_second = (ImageView) findViewById(R.id.iv_splash_second);
        iv_splash_third = (ImageView) findViewById(R.id.iv_splash_third);
        ll_splash_container = (LinearLayout) findViewById(R.id.ll_splash_container);
        ll_splash_container.setOnClickListener(this);
        btn_join = (Button) findViewById(R.id.btn_join);
        btn_join.setOnClickListener(this);
        View view1 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.imageview_first, null);
        View view2 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.imageview_second, null);
        View view3 = LayoutInflater.from(SplashActivity.this).inflate(R.layout.imageview_third, null);
        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPagerAdapter = new ViewPagerAdapter(views, SplashActivity.this);
        vp_container.setAdapter(viewPagerAdapter);
        vp_container.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        iv_splash_first.setBackgroundResource(R.drawable.shape2);
                        iv_splash_second.setBackgroundResource(R.drawable.shape1);
                        iv_splash_third.setBackgroundResource(R.drawable.shape1);
                        break;
                    case 1:
                        ll_splash_container.setVisibility(View.VISIBLE);
                        btn_join.setVisibility(View.GONE);
                        iv_splash_first.setBackgroundResource(R.drawable.shape1);
                        iv_splash_second.setBackgroundResource(R.drawable.shape2);
                        iv_splash_third.setBackgroundResource(R.drawable.shape1);
                        break;
                    case 2:
                        ll_splash_container.setVisibility(View.GONE);
                        btn_join.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_join:
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("count",1);
                edit.commit();
                startActivity(new Intent(SplashActivity.this,Two_Activity.class));
                finish();
                break;
        }
    }
}
