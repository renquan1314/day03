package com.example.ren.day03zuo2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ren on 2019/9/20.
 */

public class MyReAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Bean.ResultsBean> list;

    public MyReAdapter(Context context, ArrayList<Bean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyHolder myHolder = (MyHolder) holder;
        Glide.with(context).load(list.get(position).getUrl()).into(myHolder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.getposition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.re_image);
        }
    }

    public interface OnClickListener{
        void getposition(int position);
    }

    OnClickListener s;

    public void setS(OnClickListener s) {
        this.s = s;
    }
}
