package com.example.tommylee.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Carson_Ho on 16/7/22.
 */
public class registration_email extends Fragment {

    TextView textDummyHintUsername;
    TextView textDummyHintPassword;
    EditText editUsername;
    EditText editPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.registration_email, container, false);

        /*textDummyHintUsername = (TextView) v.findViewById(R.id.text_dummy_hint_username);
        textDummyHintPassword = (TextView) v.findViewById(R.id.text_dummy_hint_pw);

        editUsername = (EditText)v.findViewById(R.id.fgemail1);
        editPassword = (EditText)v.findViewById(R.id.pw);

        // Username
       /* editUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // Show white background behind floating label
                            textDummyHintUsername.setVisibility(View.VISIBLE);
                            //textDummyHintUsername.bringToFront();
                        }
                    }, 100);
                } else {
                    // Required to show/hide white background behind floating label during focus change
                    if (editUsername.getText().length() > 0){
                        textDummyHintUsername.setVisibility(View.VISIBLE);
                        //textDummyHintUsername.bringToFront();
                        }
                    else
                        textDummyHintUsername.setVisibility(View.INVISIBLE);
                }
            }
        });
        editPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            // Show white background behind floating label
                            textDummyHintPassword.setVisibility(View.VISIBLE);
                            //textDummyHintUsername.bringToFront();
                        }
                    }, 100);
                } else {
                    // Required to show/hide white background behind floating label during focus change
                    if (editPassword.getText().length() > 0){
                        textDummyHintPassword.setVisibility(View.VISIBLE);
                        //textDummyHintUsername.bringToFront();
                        }
                    else
                        textDummyHintPassword.setVisibility(View.INVISIBLE);
                }
            }
        });*/
        return v;
    }

}

