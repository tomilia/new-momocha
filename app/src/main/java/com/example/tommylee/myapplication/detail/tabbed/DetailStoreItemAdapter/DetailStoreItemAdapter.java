package com.example.tommylee.myapplication.detail.tabbed.DetailStoreItemAdapter;

/**
 * Created by tommylee on 2/1/2018.
 */

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

/**
 * Created by tommylee on 16/12/2017.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.Payment.PaymentDetailSelectionActivity;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.detail.CouponModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.transform.Result;

public class DetailStoreItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<CouponModel> infoData = new ArrayList<CouponModel>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;
    public DetailStoreItemAdapter(Context context, ArrayList<CouponModel> data) {
        Log.d("czx",String.valueOf(data.size()));
        this.context=context;
        inflater=LayoutInflater.from(context);
        infoData  = data;
        Log.d("inf",infoData.toString());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v2 = inflater.inflate(R.layout.detailstoreitemcell, parent, false);
                BasicInfoHolder2 viewHolder2 = new BasicInfoHolder2(v2);
                return viewHolder2;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        BasicInfoHolder2 viewholder2 = (BasicInfoHolder2) holder;
        viewholder2.tv2.setText(String.valueOf(infoData.get(position).getName()));
        viewholder2.newPrice.setText("¥"+String.valueOf(infoData.get(position).getNewPrice()));
        viewholder2.originPrice.setText("¥"+String.valueOf(infoData.get(position).getOriginPrice()));
        viewholder2.timelimit.setText(String.valueOf(infoData.get(position).getTimelimit())+"分鐘");

    }


    class BasicInfoHolder2 extends RecyclerView.ViewHolder {

        TextView tv2,originPrice,newPrice,timelimit;
        public BasicInfoHolder2(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PaymentDetailSelectionActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    v.getContext().startActivity(intent);
                    //Log.d("RecyclerView", "onClick：" + getAdapterPosition());
                }
            });
            tv2= (TextView) itemView.findViewById(R.id.txt_item);
            newPrice=(TextView) itemView.findViewById(R.id.newprice);
            timelimit  =(TextView) itemView.findViewById(R.id.timelimit);
            originPrice = (TextView) itemView.findViewById(R.id.originPrice);
            originPrice.setPaintFlags(originPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }




    @Override
    public int getItemCount() {
        return infoData.size();
    }


}

