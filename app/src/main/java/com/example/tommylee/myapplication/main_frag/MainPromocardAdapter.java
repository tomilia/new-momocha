package com.example.tommylee.myapplication.main_frag;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.bumptech.glide.Glide;
import com.example.tommylee.myapplication.MainActivity;
import com.example.tommylee.myapplication.R;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;


public class MainPromocardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Integer[] imgsrc={R.drawable.watercube1,R.drawable.watercube2,R.drawable.spa1,R.drawable.spa2};
    private String[] tempStore={"水立方","心足一道","泰舒適","水立方"};
    private String[] tempStation = {"福田站","皇崗山站","老街站","福田站"};
    private String[] tempDistrict = {"漁農村","皇崗新村","皇崗新村","漁農村"};
    private OnItemClicked onClick;
    private int viewType;
    //make interface like this
    public interface OnItemClicked {
        void onItemClick(int position);
    }
    Context context;
    LayoutInflater inflater;
    int[] image;
    public MainPromocardAdapter(Context context,int viewType) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.viewType = viewType;
    }
    public MainPromocardAdapter(Context context, ArrayList<String> mData, int[] mImage) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        image=mImage;
    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if(viewType == 1)
        return 0;
        else
            return 1;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder,final int position){
        Log.d("csccs",String.valueOf(position));
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        cardHolder viewHolder2 = (cardHolder)holder;
        ((cardHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position);
            }
        });
        Glide.with(context).load(imgsrc[position]).into((viewHolder2).imageView);
        viewHolder2.tv1.setText(tempStore[position]);
        viewHolder2.station.setText(tempStation[position]);
        viewHolder2.tv2.setText(tempDistrict[position]);
        // Set a random color for TextView background



    }
    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick=onClick;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View v = inflater.inflate(R.layout.promoting_card, parent, false);
                cardHolder viewHolder = new cardHolder(v);
                return viewHolder;
            default:
                View v2 = inflater.inflate(R.layout.fourinonecell, parent, false);
                cardHolder viewHolder2 = new cardHolder(v2);
                return viewHolder2;
        }



    }

    @Override
    public int getItemCount(){
        return tempStore.length;
    }

    class cardHolder extends RecyclerView.ViewHolder {

        TextView tv1,station,tv2;

        ImageView imageView;

        public cardHolder(View itemView) {
            super(itemView);
            station = (TextView)itemView.findViewById(R.id.station);
            tv1= (TextView) itemView.findViewById(R.id.shopname);
            imageView= (ImageView) itemView.findViewById(R.id.imageView2);
            tv2=(TextView) itemView.findViewById(R.id.street);
        }

    }
    class cardHolder2 extends RecyclerView.ViewHolder {

        TextView tv1;

        ImageView imageView;

        public cardHolder2(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.shopname);
            imageView= (ImageView) itemView.findViewById(R.id.imageView2);


        }

    }
}

