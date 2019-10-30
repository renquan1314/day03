package com.example.ren.wanandroid.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.youth.banner.transformer.DefaultTransformer;

/**
 * Created by ren on 2019/10/25.
 */

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(Context context) {
        this(context, null);
    }
    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置viewpage的切换动画,这里设置才能真正实现垂直滑动的viewpager
        setPageTransformer(true, new DefaultTransformer());
    }
    /**
     * 拦截touch事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = super.onInterceptTouchEvent(swapEvent(ev));
        swapEvent(ev);
        return intercept;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapEvent(ev));
    }
    private MotionEvent swapEvent(MotionEvent event) {
        //获取宽高
        float width = getWidth();
        float height = getHeight();
        //将Y轴的移动距离转变成X轴的移动距离
        float swappedX = (event.getY() / height) * width;
        //将X轴的移动距离转变成Y轴的移动距离
        float swappedY = (event.getX() / width) * height;
        //重设event的位置
        event.setLocation(swappedX, swappedY);
        return event;
    }

    public class DefaultTransformer implements ViewPager.PageTransformer{

        @Override
        public void transformPage(@NonNull View page, float position) {
            float alpha = 0;
            if (0<=position && position<=1){
                alpha = 1- position;
            }else if (-1<position && position<0){
                alpha = position+1;
            }
            page.setAlpha(alpha);
            float transX = page.getWidth() * -position;
            page.setTranslationX(transX);
            float transY = position * page.getHeight();
            page.setTranslationY(transY);
        }
    }
}