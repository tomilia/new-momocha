package com.example.tommylee.myapplication;

import android.content.Intent;
import android.os.Bundle;
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

public class registration_verify extends AppCompatActivity{
    Button next_step;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_verify);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        next_step = (Button) findViewById(R.id.next);

        next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_registration_set_password(v);
            }


        });

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
        Intent intert = new Intent(v.getContext(),registration_set_password.class);
        startActivity(intert);
    }
}


