package com.example.tommylee.myapplication.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.support.design.widget.TabLayout;

import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.detail.tabbed.DetailStoreItemAdapter.DetailStoreItemAdapter;
import com.example.tommylee.myapplication.detail.tabbed.cmSection.CommentAdapter;
import com.example.tommylee.myapplication.detail.tabbed.cmSection.cmModel;

import java.util.ArrayList;


public class Detail_Activity extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView cm;
    DetailStoreItemAdapter dsia;
    CommentAdapter ca;
    ArrayList<String> infoData = new ArrayList<>();
    ArrayList<cmModel> cmData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        infoData.add("中式");
        infoData.add("泰式");
        infoData.add("熱石");
        createCMModel();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] intentfrag=new String[4];
        ArrayList<CouponModel> x=new ArrayList<>();
        try {
            intentfrag[0]= getIntent().getExtras().getString("id");
            intentfrag[1]=getIntent().getExtras().getString("name");
            intentfrag[2]=getIntent().getExtras().getString("district");
            intentfrag[3]=getIntent().getExtras().getString("full_address");
            x = getIntent().getParcelableArrayListExtra("coupon");
            //pic=getIntent().getExtras().getIntegerArrayList("imageU");
            Log.d("hahaha",String.valueOf(x.size()));
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }if(savedInstanceState==null) {

            Bundle bundle = new Bundle();
            bundle.putStringArray("datafrag", intentfrag);
            //bundle.putIntegerArrayList("pic",pic);
            BasicInfoFrag fragobj = new BasicInfoFrag();
            fragobj.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.detail_frag, fragobj).commit();
            rv = (RecyclerView)findViewById(R.id.item_recycler);
            rv.setLayoutFrozen(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(layoutManager);
            dsia = new DetailStoreItemAdapter(getApplicationContext(),x);
            rv.setAdapter(dsia);

            cm = (RecyclerView)findViewById(R.id.cm_recycler);
            LinearLayoutManager layoutManagercm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            cm.setLayoutManager(layoutManagercm);
            ca = new CommentAdapter(getApplicationContext(),cmData);
            cm.setAdapter(ca);
            cm.setLayoutFrozen(true);
 /*           TabbedBottomFrag tabobj = new TabbedBottomFrag();
            getSupportFragmentManager().beginTransaction().add(R.id.tabbed_frag, tabobj).commit();
            */


        }

    }
    private void createCMModel(){
        cmData.add(new cmModel("Tommy","讚","2018-07-04",""));
        cmData.add(new cmModel("Wong","真讚！","2018-07-04",""));
        cmData.add(new cmModel("陳先生","非常好的服務","2018-07-04",""));

    }
    public void onSupportNavigateUp2(View view) {
        onBackPressed();
        getIntent().removeExtra("url");

    }

}
