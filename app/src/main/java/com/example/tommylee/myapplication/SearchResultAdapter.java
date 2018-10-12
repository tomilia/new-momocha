package com.example.tommylee.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private static final String TAG ="search_result_adapter";

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
//    private ArrayList<String> mDist;
    private Context mContext;

    public SearchResultAdapter(Context context, ArrayList<String>ImageNames,ArrayList<String> Images,ArrayList<String> Dists){
        mImageNames = ImageNames;
        mImages = Images;
//        mDist = Dists;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_template, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        holder.imageName.setText(mImageNames.get(position));
        //holder.dist.setText(mDist.get(position));

    }




    @Override
    public int getItemCount(){
        return mImageNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;
  //      TextView dist;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imageView2);
            imageName = itemView.findViewById(R.id.shopname);
    //        dist = itemView.findViewById(R.id.district);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
