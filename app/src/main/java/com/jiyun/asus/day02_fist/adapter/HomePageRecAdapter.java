package com.jiyun.asus.day02_fist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.asus.day02_fist.R;
import com.jiyun.asus.day02_fist.beans.HomePageBean;

import java.util.List;

/**
 * Created by asus on 2017/9/12.
 */

public class HomePageRecAdapter extends RecyclerView.Adapter<HomePageRecAdapter.ViewHolder>{
    public interface OnClickLister{
        void click(int position);
    }
    public OnClickLister onClickLister;
    public void setOnClickLister(OnClickLister onClickLister){
        this.onClickLister=onClickLister;
    }
    private List<HomePageBean.ResultBean.ListBean> list;
    private Context context;

    public HomePageRecAdapter(List<HomePageBean.ResultBean.ListBean> list) {
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.homepageadapter_main, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getFirstImg()).into(holder.iv_adapter_image);
        holder.tv_adapter_content.setText(list.get(position).getTitle());
        holder.rlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private  ImageView iv_adapter_image;
        private  TextView tv_adapter_content;
        private  RelativeLayout rlt;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_adapter_image = itemView.findViewById(R.id.iv_adapter_image);
            tv_adapter_content = itemView.findViewById(R.id.tv_adapter_content);
            rlt = itemView.findViewById(R.id.rlt);
        }
    }
}
