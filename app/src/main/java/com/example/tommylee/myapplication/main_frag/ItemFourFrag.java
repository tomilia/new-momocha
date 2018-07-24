package com.example.tommylee.myapplication.main_frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tommylee.myapplication.R;

public class ItemFourFrag extends Fragment {
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
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_four, container, false);
    }
}