package com.lnyp.imgdots.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lnyp.imgdots.R;
import com.lnyp.imgdots.bean.PointSimple;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    private ArrayList<PointSimple> pointList;
    private FrameLayout pointsLayout;
    private ImageView imageView;
    private Context context;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);
        imageView = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        pointsLayout = (FrameLayout) imgPointLayout.findViewById(R.id.pointsLayout);
    }

    public void setImage(int width, int height, String imgUrl) {
        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
        lp.width = width;
        lp.height = height;
        imageView.setLayoutParams(lp);
        ViewGroup.LayoutParams lp1 = pointsLayout.getLayoutParams();
        lp1.width = width;
        lp1.height = height;
        pointsLayout.setLayoutParams(lp1);
        Glide.with(context).load(imgUrl).asBitmap().into(imageView);
        addPoints(width, height);
    }

    public void setPoints(ArrayList<PointSimple> points) {
        this.pointList = points;
    }

    private void addPoints(int width, int height) {
        pointsLayout.removeAllViews();
        for (int i = 0; i < pointList.size(); i++) {
            double width_scale = pointList.get(i).width_scale;
            double height_scale = pointList.get(i).height_scale;
            LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
            imageView.setTag(i);
            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);
            imageView.setOnClickListener(this);
            pointsLayout.addView(view, layoutParams);
        }
    }

    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Toast.makeText(getContext(), "pos : " + pos, Toast.LENGTH_SHORT).show();
    }
}
