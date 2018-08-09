package com.example.tommylee.myapplication.main_frag;

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
import com.example.tommylee.myapplication.R;

import java.util.ArrayList;

public class search_focus_adapter extends RecyclerView.Adapter<search_focus_adapter.ViewHolder> {
    private static final String TAG ="search_focus_adapter";

    private ArrayList<String> data;
    private ArrayList<String> dataImg = new ArrayList();
    private Context mContext;

    public search_focus_adapter(Context context,ArrayList<String> data,ArrayList<String> Images)
    {
        this.data = data;
        dataImg = Images;
        mContext = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.initial.setText(data.get(position));
        Glide.with(mContext)
                .asBitmap()
                .load(dataImg.get(position))
                .into(holder.icon);
    }

    @Override
    public int  getItemCount()
    {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView name, initial;
    public ImageView icon;

    public ViewHolder(View itemView)
    {
     super(itemView);
     initial = (TextView) itemView.findViewById(R.id.txt_initial);
     icon = (ImageView) itemView.findViewById(R.id.bg_date_time);
    }
    }
}
