package com.example.ren.day03zuo3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/9/22.
 */

public class MyReAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Bean.BodyBean.ResultBean> list;

    public MyReAdapter(Context context, ArrayList<Bean.BodyBean.ResultBean> list) {
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
        myHolder.tv1.setText(list.get(position).getTeacherName());
        myHolder.tv2.setText(list.get(position).getTitle());

        List<?> teacherType = list.get(position).getTeacherType();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < teacherType.size(); i++) {
            strings.add(teacherType.get(i).toString());
        }
        myHolder.tv3.setText(strings.toString());

        Glide.with(context).load(list.get(position).getTeacherPic()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myHolder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (s!=null){
                   s.getPosition(position);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.re_image);
            tv1 = itemView.findViewById(R.id.re_tv1);
            tv2 = itemView.findViewById(R.id.re_tv2);
            tv3 = itemView.findViewById(R.id.re_tv3);
        }
    }

    public interface OnClickListener{
        void getPosition(int position);
    }
    OnClickListener s;

    public void setS(OnClickListener s) {
        this.s = s;
    }
}
