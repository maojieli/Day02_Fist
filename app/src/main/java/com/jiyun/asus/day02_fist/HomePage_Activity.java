package com.jiyun.asus.day02_fist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.google.gson.Gson;
import com.jiyun.asus.day02_fist.adapter.HomePageRecAdapter;
import com.jiyun.asus.day02_fist.beans.HomePageBean;
import com.jiyun.asus.day02_fist.utlis.OkHttpUlist;

import com.recker.flybanner.FlyBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.jiyun.asus.day02_fist.R.id.fb;

public class HomePage_Activity extends AppCompatActivity {

    @Bind(R.id.rl)
    RecyclerView rl;
    @Bind(R.id.tb)
    Toolbar tb;
    @Bind(R.id.textView)
    TextView textView;
    private ArrayList<Integer> integers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_);
        ButterKnife.bind(this);
        tb.setTitle("");
        setSupportActionBar(tb);
        integers = new ArrayList<>();
        integers.add(R.mipmap.a9);
        integers.add(R.mipmap.b1);
        integers.add(R.mipmap.b3);
        OkHttpUlist.getOkHttpUlist(HomePage_Activity.this).sendGet("http://v.juhe.cn/weixin/query?key=a332c6b34264527ac142764eaed9364d&pno=3", new com.android.volley.Response.Listener<String>() {

            private HomePageRecAdapter homePageRecAdapter;
            private FlyBanner fb;

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HomePageBean homePageBean = gson.fromJson(response, HomePageBean.class);
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                rl.setLayoutManager(staggeredGridLayoutManager);
                final List<HomePageBean.ResultBean.ListBean> list = homePageBean.getResult().getList();
                homePageRecAdapter = new HomePageRecAdapter(list);
                rl.setAdapter(homePageRecAdapter);
                RecyclerViewHeader recyclerViewHeader = RecyclerViewHeader.fromXml(HomePage_Activity.this, R.layout.homepageheader_main);
                recyclerViewHeader.attachTo(rl);
                fb = recyclerViewHeader.findViewById(R.id.fb);
               fb.setImages(integers);
                homePageRecAdapter.setOnClickLister(new HomePageRecAdapter.OnClickLister() {
                    @Override
                    public void click(int position) {
                        String url = list.get(position).getUrl();
                        Intent intent = new Intent(HomePage_Activity.this, Contents_Activity.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



    }
}
