package com.example.tommylee.myapplication.detail;

/**
 * Created by tommylee on 2/1/2018.
 */

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

public class BasicInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> infoData = new ArrayList<String>();
    private String[] mKeys;
    Context context;
    LayoutInflater inflater;
    private static RecyclerView horizontalList;
    public BasicInfoAdapter(Context context, ArrayList<String> data) {
        Log.d("czx",String.valueOf(data.size()));
        this.context=context;
        inflater=LayoutInflater.from(context);
        infoData  = data;
    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
       if(position==2)return 0;
       else return 2;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
            View v = inflater.inflate(R.layout.storetag, parent, false);
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
            break;
            case 2:
                BasicInfoHolder2 viewholder2=(BasicInfoHolder2)holder;
                viewholder2.tv2.setText(infoData.get(position));
                switch (position) {
                    case 0:
                        viewholder2.tv2.setTextColor(Color.rgb(255,0,0));
                        viewholder2.placetv2.setVisibility(View.GONE);
                        break;
                    case 1:
                        viewholder2.placetv2.setVisibility(View.GONE);
                    break;
                    case 2:
                        viewholder2.placetv2.setVisibility(View.GONE);
                        break;
                }
                break;
        }



    }
    class BasicInfoHolder1 extends RecyclerView.ViewHolder {

        RecyclerView tv2;
        private HorizontalTagAdapter horizontalAdapter;
        public BasicInfoHolder1(View itemView) {
            super(itemView);
            tv2= (RecyclerView) itemView.findViewById(R.id.horizontal_list);
            tv2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new HorizontalTagAdapter(context);
            tv2.setAdapter(horizontalAdapter);

        }


    }


    class BasicInfoHolder2 extends RecyclerView.ViewHolder {

        TextView tv2,placetv2;
        ImageView icon;
        ImageView lefticon;
        LinearLayout infoblock;
        public BasicInfoHolder2(View itemView) {
            super(itemView);
            tv2= (TextView) itemView.findViewById(R.id.infovalue);
            placetv2=(TextView) itemView.findViewById(R.id.additionalinfovalue);
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
