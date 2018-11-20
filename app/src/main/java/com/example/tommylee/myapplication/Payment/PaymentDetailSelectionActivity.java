package com.example.tommylee.myapplication.Payment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tommylee.myapplication.R;

public class PaymentDetailSelectionActivity extends AppCompatActivity {
    ImageView title;
    RecyclerView rv;
    PaymentSelectionAdapter psa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        title = findViewById(R.id.title_image);
        Glide.with(this)
                .load(R.drawable.ocean)
                .into(title);
        rv = findViewById(R.id.rv);
        psa = new PaymentSelectionAdapter(this);
        rv.setAdapter(psa);
        LinearLayoutManager layoutManagercm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManagercm);
        rv.setLayoutFrozen(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv.getContext(),
                layoutManagercm.getOrientation());
        rv.addItemDecoration(dividerItemDecoration);

    }
}
