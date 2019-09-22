package com.example.ren.day03zuo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.ren.day03zuo1.db.DatasBeanDao;

import java.util.ArrayList;

/**
 * Created by ren on 2019/9/20.
 */

public class MyReAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DatasBean> lists;

    public MyReAdapter(Context context, ArrayList<DatasBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.layout_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final MyHolder myHolder = (MyHolder) holder;
        myHolder.tv1.setText(lists.get(position).getTitle());
        myHolder.tv2.setText(lists.get(position).getAuthor());
        Glide.with(context).load(lists.get(position).getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myHolder.image);

        DatasBeanDao dao = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
        DatasBean unique = dao.queryBuilder().where(DatasBeanDao.Properties.Id.eq(lists.get(position).getId())).unique();

        if (unique!=null){
            myHolder.btn.setText("取消");
        }
        myHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.getposition(position,myHolder.btn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv1;
        TextView tv2;
        Button btn;
        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.re_image);
            tv1 = itemView.findViewById(R.id.re_tv1);
            tv2 = itemView.findViewById(R.id.re_tv2);
            btn = itemView.findViewById(R.id.re_btn);
        }
    }

    public interface OnClickListener{
        void getposition(int position,Button btn);
    }
    OnClickListener s;

    public void setS(OnClickListener s) {
        this.s = s;
    }
}
