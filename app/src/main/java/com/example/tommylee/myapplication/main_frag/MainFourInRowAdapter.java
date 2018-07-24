package com.example.tommylee.myapplication.main_frag;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tommylee.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Created by tommylee on 5/1/2018.
 */

public class MainFourInRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> e=new ArrayList<>();
    Context context;
    LayoutInflater inflater;
    int[] image;
    public MainFourInRowAdapter(Context context, ArrayList<String> mData, int[] mImage) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        e=mData;
        image=mImage;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        ArticleHolder viewHolder2 = (ArticleHolder)holder;

        Glide.with(context).load(image[position]).into((viewHolder2).imageView);
        viewHolder2.tv1.setText(e.get(position));

        // Set a random color for TextView background



    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.articlerow, parent, false);
        ArticleHolder viewHolder = new ArticleHolder(v);
        return viewHolder;


    }
    @Override
    public int getItemCount(){
        return e.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        TextView tv1;
        ImageView imageView;

        public ArticleHolder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.articletitle);
            imageView= (ImageView) itemView.findViewById(R.id.articleimage);

        }

    }
}

