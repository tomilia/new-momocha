package com.example.tommylee.myapplication.detail;

/**
 * Created by tommylee on 2/1/2018.
 */

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
import com.example.tommylee.myapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.transform.Result;

public class BasicInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LinkedHashMap<String, String> infoData = new LinkedHashMap<String, String>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;
    public BasicInfoAdapter(Context context, LinkedHashMap<String, String> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        infoData  = data;
        mKeys = infoData.keySet().toArray(new String[data.size()]);
    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
       if(position==3)return 0;
       else return 2;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
            View v = inflater.inflate(R.layout.iconic_recycler_button, parent, false);
            v.setOnClickListener(ClickIntHere);
            BasicInfoHolder1 viewHolder = new BasicInfoHolder1(v);
            return viewHolder;
            case  2:
                View v2 = inflater.inflate(R.layout.basic_info_list, parent, false);
                BasicInfoHolder2 viewHolder2 = new BasicInfoHolder2(v2);
                return viewHolder2;
                default:return null;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                BasicInfoHolder1 viewholder0=(BasicInfoHolder1) holder;
                viewholder0.tv2.setText(mKeys[position]);

            break;
            case 2:
                BasicInfoHolder2 viewholder2=(BasicInfoHolder2)holder;
                viewholder2.tv1.setText(mKeys[position]);
                viewholder2.tv2.setText(infoData.get(mKeys[position]));
                switch (position) {
                    case 0:
                        viewholder2.icon.setImageResource(R.drawable.ic_passage_of_time);
                        viewholder2.icon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

                        viewholder2.placetv2.setVisibility(View.GONE);
                        break;
                    case 1:
                    viewholder2.lefticon.setImageResource(R.drawable.ic_pin);
                        viewholder2.icon.setImageResource(R.drawable.ic_phone_call);
                    viewholder2.placetv2.setText("距地鐵2號錢湖貝站C出口步行420m");
                        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                        params2.setMargins(50,0,0,0);

                    viewholder2.placetv2.setLayoutParams(params2);
                        viewholder2.icon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                    break;
                    case 2:

                        viewholder2.icon.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

                        viewholder2.placetv2.setVisibility(View.GONE);
                        break;
                }
                break;
        }



    }
    class BasicInfoHolder1 extends RecyclerView.ViewHolder {

        TextView tv2;
        public BasicInfoHolder1(View itemView) {
            super(itemView);
            tv2= (TextView) itemView.findViewById(R.id.infokey);

        }


    }


    class BasicInfoHolder2 extends RecyclerView.ViewHolder {

        TextView tv1,tv2,placetv2;
        ImageView icon;
        ImageView lefticon;
        LinearLayout infoblock;
        public BasicInfoHolder2(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.infokey);
            tv2= (TextView) itemView.findViewById(R.id.infovalue);
            placetv2=(TextView) itemView.findViewById(R.id.additionalinfovalue);
            lefticon=(ImageView)itemView.findViewById(R.id.lefticon);
            icon=(ImageView)itemView.findViewById(R.id.detail_icon_hold2);
            infoblock=(LinearLayout)itemView.findViewById(R.id.infoblock);
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
        return infoData.size();
    }


}
