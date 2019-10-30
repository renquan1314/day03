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
import com.example.ren.wanandroid.bean.SystemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/10/25.
 */

public class MySystemReAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<SystemBean.DataBean.DatasBean> lists = new ArrayList<>();

    public MySystemReAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.system_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        SystemBean.DataBean.DatasBean bean = lists.get(position);
        MyHolder myHolder = (MyHolder) holder;
        myHolder.tv1.setText(bean.getAuthor());
        myHolder.tv2.setText(bean.getSuperChapterName());
        myHolder.tv3.setText(bean.getTitle());
        myHolder.tv4.setText(bean.getNiceDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.getPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
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

    public void setData(List<SystemBean.DataBean.DatasBean> datas) {
        lists.addAll(datas);
        notifyDataSetChanged();
    }

    public interface OnClickListener{
        void getPosition(int position);
    }

    OnClickListener s;

    public void setS(OnClickListener s) {
        this.s = s;
    }
}
