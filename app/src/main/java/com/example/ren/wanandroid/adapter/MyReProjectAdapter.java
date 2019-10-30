package com.example.ren.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.bean.ProjectListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/10/25.
 */

public class MyReProjectAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<ProjectListBean.DataBean.DatasBean> list = new ArrayList<>();
    public MyReProjectAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_project_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ProjectListBean.DataBean.DatasBean bean = list.get(position);
        MyHolder myHolder = (MyHolder) holder;
        myHolder.tv1.setText(bean.getDesc());
        myHolder.tv2.setText(bean.getDesc());
        myHolder.tv3.setText(bean.getNiceDate()+" "+bean.getAuthor());
        Glide.with(context).load(bean.getEnvelopePic()).into(myHolder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.getPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public MyHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.project_tv1);
            tv2 = itemView.findViewById(R.id.project_tv2);
            tv3 = itemView.findViewById(R.id.project_tv3);
            image = itemView.findViewById(R.id.project_image);
    }
    }

    public void setData(List<ProjectListBean.DataBean.DatasBean> datas) {
        list.addAll(datas);
        notifyDataSetChanged();
    }

    public interface OnClickListener{
        void getPosition(int position);
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
