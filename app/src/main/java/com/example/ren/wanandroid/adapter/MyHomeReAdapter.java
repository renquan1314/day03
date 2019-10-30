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
import com.example.ren.wanandroid.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2019/10/24.
 */

public class MyHomeReAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<String> strings;
    public ArrayList<String> titls;

    public ArrayList<HomeBean.DataBean.DatasBean> lists = new ArrayList<>();
    public MyHomeReAdapter(Context context, ArrayList<String> strings, ArrayList<String> titls) {
        this.context = context;
        this.strings = strings;
        this.titls = titls;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1){
           return new MyHolder1(LayoutInflater.from(context).inflate(R.layout.layout_banner,parent,false));
        }else {
           return new MyHolder2(LayoutInflater.from(context).inflate(R.layout.layout_recycler,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);

        if (itemViewType==1){
            MyHolder1 myHolder1 = (MyHolder1) holder;
            myHolder1.banner.setBannerTitles(titls);
            myHolder1.banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            myHolder1.banner.setImages(strings);
            myHolder1.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            myHolder1.banner.setBannerAnimation(Transformer.FlipHorizontal);
            myHolder1.banner.setDelayTime(1500);
            myHolder1.banner.start();
        }else {
            MyHolder2 myHolder2 = (MyHolder2) holder;
            myHolder2.tv1.setText(lists.get(position-1).getChapterName());
            myHolder2.tv2.setText(lists.get(position-1).getSuperChapterName());
            myHolder2.tv3.setText(lists.get(position-1).getTitle());
            myHolder2.tv4.setText(lists.get(position-1).getNiceDate());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickListeners.onClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 1;
        }else {
            return 2;
        }
    }

    public void setData(List<HomeBean.DataBean.DatasBean> datas) {
        lists.addAll(datas);
        notifyDataSetChanged();
    }

    class MyHolder1 extends RecyclerView.ViewHolder {
        Banner banner;
        public MyHolder1(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.re_banner);
        }
    }

    class MyHolder2 extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        public MyHolder2(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.re_tv1);
            tv2 = itemView.findViewById(R.id.re_tv2);
            tv3 = itemView.findViewById(R.id.re_tv3);
            tv4 = itemView.findViewById(R.id.re_tv4);
        }
    }

    public interface OnClickListener{
        void onClickListener(int position);
    }

    OnClickListener OnClickListeners;

    public void setOnClickListeners(OnClickListener onClickListeners) {
        OnClickListeners = onClickListeners;
    }
}
