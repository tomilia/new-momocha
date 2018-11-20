package com.example.tommylee.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;


public class Result_Page_Activity extends AppCompatActivity {
    String s,url;
    private TabLayout mTabLayout;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result__page_);
        try{
            url=getIntent().getStringExtra("url");
        }
        catch(Exception e){        }
        Bundle bundle = new Bundle();
        bundle.putString("querys", url);
        final ResultFragment fragobj = new ResultFragment();

        fragobj.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.result_frag,fragobj).commit();

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("默認排序"));
        mTabLayout.addTab(mTabLayout.newTab().setText("評分"));
        mTabLayout.addTab(mTabLayout.newTab().setText("價格"));
        mTabLayout.addOnTabSelectedListener(
            new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    //do stuff here
                    Log.d("select tab", String.valueOf(tab.getPosition()));
                    switch (tab.getPosition()) {
                        case 0:
                            fragobj.reloadData("default");
                        break;
                        case 1:
                            fragobj.reloadData("popular");
                            break;
                        case 2:
                            fragobj.reloadData("price");
                            break;
                    }
                }
                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }

            }
    );
      //  Result_Page_Adapter = new Result_Page_Adapter();

        }
    public void onSupportNavigateUp2(View view) {
        onBackPressed();
        getIntent().removeExtra("url");
    }
}



