package com.lnyp.imgdots.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.view.ImageLayout;

import java.util.ArrayList;
import java.util.List;

public class ImgBrowsePagerAdapter extends PagerAdapter {

    private List<ImgSimple> imgList;
    private List<View> views;
    private Context context;
    private int width;

    public ImgBrowsePagerAdapter(Context context, List<ImgSimple> imgList) {
        this.context = context;
        this.imgList = imgList;
        this.views = new ArrayList<>();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
    }

    @Override
    public int getCount() { // 获得size
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_img_browse, null);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.layoutContent);
        try {
            String imgUrl = imgList.get(position).url;
            float scale = imgList.get(position).scale;
            ArrayList<PointSimple> pointSimples = imgList.get(position).pointSimples;
            layoutContent.setPoints(pointSimples);
            int height = (int) (width * scale);
            layoutContent.setImgBg(width, height, imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        container.addView(view);
        return view;
    }
}