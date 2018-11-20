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

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>  {
    private static final String TAG ="search_result_adapter";
    private OnItemClicked onClick;

    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }

    private ArrayList<SearchResultModel> srms;
    private Context mContext;
    public SearchResultAdapter(Context context, ArrayList<SearchResultModel> srms){
        this.srms = srms;
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
        try {
            Log.d("guten",srms.get(position).getImageUrl());
            Glide.with(mContext)
                    .asBitmap()
                    .load(srms.get(position).getImageUrl())
                    .into(holder.image);
        }catch(Exception e){
           Log.d("guten","tag");
            Glide.with(mContext)
                    .asBitmap()
                    .load(srms.get(position).getImageU().get(0))
                    .into(holder.image);
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
        holder.imageName.setText(srms.get(position).getCHtitle());
        //holder.dist.setText(mDist.get(position));

    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }


    @Override
    public int getItemCount(){
        return srms.size();
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
