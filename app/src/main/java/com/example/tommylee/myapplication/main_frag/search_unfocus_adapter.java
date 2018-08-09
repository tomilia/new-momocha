package com.example.tommylee.myapplication.main_frag;

import android.content.Context;
import android.support.v4.util.CircularArray;
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
import com.example.tommylee.myapplication.R;

import java.util.ArrayList;

public class search_unfocus_adapter extends RecyclerView.Adapter<search_unfocus_adapter.ViewHolder> {
    private static final String TAG ="search_unfocus_adapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList();
    int[] mIcon;
    private Context mContext;

    public search_unfocus_adapter(Context context, ArrayList<String>ImageNames,ArrayList<String> Images,int[] Icons){
        mImageNames = ImageNames;
        mImages = Images;
        mIcon = Icons;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_unfocus_template, parent,false);
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
        Glide.with(mContext)
                .load(mIcon[position])
                .into(holder.imgicon);
        holder.imageName.setText(mImageNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));

                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public int getItemCount(){
        return mImageNames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView imageName;
        ImageView imgicon;
        RelativeLayout parentLayout;
        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.tempimage);
            imageName = itemView.findViewById(R.id.temp_image_name);
            imgicon = itemView.findViewById(R.id.tempinimage);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
