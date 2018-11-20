package com.example.tommylee.myapplication.Payment;


import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.tommylee.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.transform.Result;

public class PaymentSelectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> infoData = new ArrayList<String>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;
    private static RecyclerView horizontalList;
    public PaymentSelectionAdapter(Context context) {

        this.context=context;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v2 = inflater.inflate(R.layout.paymentselectioncell, parent, false);
                BasicInfoHolder2 viewHolder2 = new BasicInfoHolder2(v2);
                return viewHolder2;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

                BasicInfoHolder2 viewholder2=(BasicInfoHolder2)holder;




    }


    class BasicInfoHolder2 extends RecyclerView.ViewHolder {

        TextView tv2;
        ImageView icon;
        public BasicInfoHolder2(View itemView) {
            super(itemView);
            tv2= (TextView) itemView.findViewById(R.id.method);
            icon=(ImageView)itemView.findViewById(R.id.image);
        }

    }


    private View.OnClickListener ClickIntHere = new View.OnClickListener() {
        @Override
        //按下Button事件時會進入這個 function
        public void onClick(View v) {
            Log.d("tester","abc");
        }
    };

    @Override
    public int getItemCount() {
        return 4;
    }


}

