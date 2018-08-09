package com.example.tommylee.myapplication.main_frag;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.registration;
import com.example.tommylee.myapplication.registration_forget;

public class ItemFourFrag extends Fragment {
    TextView reglister;
    TextView tforget;
    Button google;
    Button facebook;
    public static ItemFourFrag newInstance() {
        ItemFourFrag fragment = new ItemFourFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_item_four, container, false);
        reglister = (TextView) v.findViewById(R.id.reg);
        reglister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_registration(v);
            }


        });

        tforget = (TextView) v.findViewById(R.id.forget);
        tforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forget(v);
            }


        });

        facebook = (Button) v.findViewById(R.id.fb);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));
                startActivity(browserIntent);
            }


        });

        google = (Button) v.findViewById(R.id.goo);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
            }


        });

        return v;
    }
    public void open_registration(View v) {
        Intent intert = new Intent(v.getContext(),registration.class);
        startActivity(intert);
    };
    public void open_forget(View v) {
        Intent intert = new Intent(v.getContext(),registration_forget.class);
        startActivity(intert);
    };
}