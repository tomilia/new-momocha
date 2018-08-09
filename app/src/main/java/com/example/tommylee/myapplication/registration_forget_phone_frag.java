package com.example.tommylee.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class registration_forget_phone_frag extends Fragment {
    TextView next_step;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.registration_forget_phone, container, false);

        Spinner mySpinner = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.spinner_phone_code));
        mySpinner.setAdapter(myAdapter);

        return v;
    }

}
