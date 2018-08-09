package com.example.tommylee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class registration extends AppCompatActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private registrate_adapter  registrate_adapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;

    //Button
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mViewPager= (ViewPager) findViewById(R.id.pw_viewPager);
        registrate_adapter = new registrate_adapter(getSupportFragmentManager());
        mViewPager.setAdapter(registrate_adapter);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        // one.setIcon(R.mipmap.ic_launcher_round);
        // two.setIcon(R.mipmap.ic_launcher_round);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                // Respond to the action bar's Up/Home button
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    public void open_registration_set_password(View v) {
        Intent intert = new Intent(v.getContext(),registration_forget_email.class);
        startActivity(intert);
    }
}


