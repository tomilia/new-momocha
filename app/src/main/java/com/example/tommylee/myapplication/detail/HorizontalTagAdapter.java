package com.example.tommylee.myapplication.detail;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tommylee.myapplication.R;

import java.util.ArrayList;

public class HorizontalTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> infoData = new ArrayList<String>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;

    public HorizontalTagAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v2 = inflater.inflate(R.layout.basic_info_tagcell, parent, false);
                BasicInfoHolder1 viewHolder2 = new BasicInfoHolder1(v2);
                return viewHolder2;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

                BasicInfoHolder1 viewholder0=(BasicInfoHolder1) holder;
                viewholder0.tv2.setText("超級精選");



    }
    class BasicInfoHolder1 extends RecyclerView.ViewHolder {

        TextView tv2;
        public BasicInfoHolder1(View itemView) {
            super(itemView);
            tv2= (TextView) itemView.findViewById(R.id.txt_tag);


        }


    }



    @Override
    public int getItemCount() {
        return 2;
    }


}
