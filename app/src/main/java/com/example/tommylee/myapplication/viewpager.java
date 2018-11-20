package com.example.tommylee.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.view.Display;
import android.graphics.Point;
import android.widget.LinearLayout;
import android.util.Log;

import com.bumptech.glide.Glide;

/**
 * Created by tommylee on 29/10/2017.
 */

public class viewpager extends PagerAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images;
    public viewpager(Context context,Integer[] images){
        this.context=context;
        this.images=images;
    }
    @Override
    public int getCount(){
        return images.length;
    }
    @Override
    public boolean isViewFromObject(View view,Object object){
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position)
    {
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slidingimage_layout,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView);

        Glide.with(context)
                .load(images[position])
                .into(imageView);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Display display =((AppCompatActivity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        ViewPager vp=(ViewPager)container;
        vp.addView(view,0);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container,int position,Object object){
        ViewPager vp=(ViewPager)container;
        View view=(View) object;
        vp.removeView(view);
    }

}
