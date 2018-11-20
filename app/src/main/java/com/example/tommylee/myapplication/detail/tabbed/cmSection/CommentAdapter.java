package com.example.tommylee.myapplication.detail.tabbed.cmSection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tommylee.myapplication.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<cmModel> infoData = new ArrayList<cmModel>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;
    public CommentAdapter(Context context, ArrayList<cmModel> data) {
        Log.d("czx",String.valueOf(data.size()));
        this.context=context;
        inflater=LayoutInflater.from(context);
        infoData  = data;
        Log.d("inf",infoData.toString());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v2 = inflater.inflate(R.layout.cmcell, parent, false);
        BasicInfoHolder2 viewHolder2 = new BasicInfoHolder2(v2);
        return viewHolder2;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        BasicInfoHolder2 viewholder2 = (BasicInfoHolder2) holder;
        viewholder2.tv2.setText(infoData.get(position).getAuthor());
        viewholder2.tv_desc.setText(infoData.get(position).getDesc());
    }


    class BasicInfoHolder2 extends RecyclerView.ViewHolder {

        TextView tv2,tv_desc;
        public BasicInfoHolder2(View itemView) {
            super(itemView);
            tv2= (TextView) itemView.findViewById(R.id.txt_author);
            tv_desc = (TextView) itemView.findViewById(R.id.txt_desc);
        }


    }




    @Override
    public int getItemCount() {
        return infoData.size();
    }


}