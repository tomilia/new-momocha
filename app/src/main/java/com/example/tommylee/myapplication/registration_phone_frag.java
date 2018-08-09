package com.example.tommylee.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tommylee.myapplication.main_frag.ItemFourFrag;

public class registration_phone_frag extends Fragment {
    Button next_step;

    public static registration_phone_frag newInstance() {
        registration_phone_frag fragment = new registration_phone_frag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.registration_phone, container, false);

        Spinner mySpinner = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_phone_code));
        mySpinner.setAdapter(myAdapter);

        next_step = (Button) v.findViewById(R.id.next);
        next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_code(v);
            }


        });
        return v;
    }
    public void open_code(View v) {
        Intent intert = new Intent(v.getContext(),registration_phone_verify.class);
        startActivity(intert);
    };
}
