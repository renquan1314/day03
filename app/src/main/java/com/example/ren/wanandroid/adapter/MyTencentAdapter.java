package com.example.ren.wanandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ren.wanandroid.R;
import com.example.ren.wanandroid.bean.TencentListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/10/27.
 */

public class MyTencentAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<TencentListBean.DataBean.DatasBean> list = new ArrayList<>();

    public MyTencentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.system_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TencentListBean.DataBean.DatasBean bean = list.get(position);
        MyHolder myHolder = (MyHolder) holder;
        myHolder.tv1.setText(bean.getAuthor());
        myHolder.tv2.setText(bean.getSuperChapterName()+" / "+bean.getAuthor());
        myHolder.tv3.setText(bean.getTitle());
        myHolder.tv4.setText(bean.getNiceDate());

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
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        public MyHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.system_tv1);
            tv2 = itemView.findViewById(R.id.system_tv2);
            tv3 = itemView.findViewById(R.id.system_tv3);
            tv4 = itemView.findViewById(R.id.system_tv4);
        }
    }

    public void setData(List<TencentListBean.DataBean.DatasBean> datas) {
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
