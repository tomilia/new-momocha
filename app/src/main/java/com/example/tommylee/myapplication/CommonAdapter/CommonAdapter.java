package com.example.tommylee.myapplication.CommonAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tommylee.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context; //context
    private ArrayList<String> items; //data source of the list adapter
    LayoutInflater inflater;
    //public constructor
    public CommonAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;

        inflater=LayoutInflater.from(context);
    }



    @Override
    public OptionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.profile_option, parent, false);
        OptionsHolder viewHolder = new OptionsHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OptionsHolder viewHolder2 = (OptionsHolder)holder;

        viewHolder2.tv1.setText(items.get(position));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        Log.d("itemx",String.valueOf(items.size()));
        return items.size();
    }


    class OptionsHolder extends RecyclerView.ViewHolder {

        TextView tv1;

        public OptionsHolder(View itemView) {
            super(itemView);

            tv1= (TextView) itemView.findViewById(R.id.txt_initial);

        }

    }
}