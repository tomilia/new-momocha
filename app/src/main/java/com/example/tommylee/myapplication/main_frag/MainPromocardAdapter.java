package com.example.tommylee.myapplication.main_frag;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.bumptech.glide.Glide;
import com.example.tommylee.myapplication.MainActivity;
import com.example.tommylee.myapplication.R;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
public class MainPromocardAdapter extends PagerAdapter{
    private  Context context;
    private LayoutInflater layoutInflater;
    private Integer[] imgsrc={R.drawable.spa,R.drawable.spa1,R.drawable.spa2};
    RatingBar ratingBar;
    Button button;
    public MainPromocardAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return imgsrc.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View item_view= layoutInflater.inflate(R.layout.promoting_card,container,false);
        ImageView imgv = (ImageView)item_view.findViewById(R.id.imageView2);
        TextView text0 = (TextView)item_view.findViewById(R.id.shopname);
        TextView text1 = (TextView)item_view.findViewById(R.id.district);
        TextView text2 = (TextView)item_view.findViewById(R.id.spend);
        //final TextView text3 = (TextView)item_view.findViewById(R.id.service);
       // ImageView imgstar = (ImageView)item_view.findViewById(R.id.starimg);


        Glide.with(context).load(imgsrc[position]).into(imgv);
        text0.setText("推拿保健按摩中心"+position);//shopname
        text1.setText("南山區");//district
        text2.setText("$100-201");//spend
        //text3.setText("評分");//service
        //Glide.with(context).load(imgsrc[stars]).into(imgstar);
        ratingBar = (RatingBar) item_view.findViewById(R.id.ratingBar2);
        ratingBar.setMax(5);
        //button = (Button)findViewById(R.id.button);

        /*ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(item_view.getContext(), "Stars: " + v,Toast.LENGTH_SHORT).show();
            }
        });*/


        ViewPager vp=(ViewPager)container;
        vp.addView(item_view,0);



        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp=(ViewPager)container;
        View view=(View) object;
        vp.removeView(view);
    }
}


