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
import com.example.ren.wanandroid.bean.TixiBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public class MyTixiReAdapter extends RecyclerView.Adapter {
    private Context context;
    public List<TixiBean.DataBean> lists = new ArrayList<>();

    public MyTixiReAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_retixi,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TixiBean.DataBean bean = lists.get(position);
        String text = "";
        List<TixiBean.DataBean.ChildrenBean> children = bean.getChildren();
        for (int i = 0; i < children.size(); i++) {
            text+=children.get(i).getName()+"    ";
        }
        MyHolder myHolder = (MyHolder) holder;
        myHolder.tv1.setText(bean.getName());
        myHolder.tv2.setText(text);

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
        public MyHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.re_tixi_tv1);
            tv2 = itemView.findViewById(R.id.re_tixi_tv2);
        }
    }

    public void setData(List<TixiBean.DataBean> data) {
        lists.addAll(data);
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
