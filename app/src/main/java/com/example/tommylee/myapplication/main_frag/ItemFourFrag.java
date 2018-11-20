package com.example.tommylee.myapplication.main_frag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.MainActivity;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.Sessions.SessionManager;
import com.example.tommylee.myapplication.registration;
import com.example.tommylee.myapplication.registration_forget;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ItemFourFrag extends Fragment {
    Button reglister;
    TextView tforget;
    EditText username;
    EditText password;
    Button login;
    Button google;
    Button facebook;

    SessionManager session;
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
        session = new SessionManager(getContext());
        username = (EditText) v.findViewById(R.id.loginphone);
        password = (EditText) v.findViewById(R.id.pw);
        login = (Button) v.findViewById(R.id.login);
        reglister = (Button) v.findViewById(R.id.reg);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URI uri = null;
                try {
                    uri = new URI("http://59.148.36.170:8000/m_login/");
                    URL url = uri.toURL();

                    LoginTask lt = new LoginTask(getActivity());

                    lt.execute(url);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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
    private class LoginTask extends AsyncTask<URL, Integer, Long> {
        private ProgressDialog dialog;

        public LoginTask(FragmentActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        protected Long doInBackground(URL... urls) {
           Log.d("url",urls[0].toString());



                try {

                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    OkHttpClient client = new OkHttpClient();
                    RequestBody formBody = new FormBody.Builder()
                            .add("username", "koeax@gmail" +
                                    ".com").add("password", "a25480097")
                            .build();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .url(urls[0])
                            .addHeader("Content-Type","application/x-www-form-urlencoded")
                            .addHeader("cookie","sessionid="+session.getUserDetails().get("session"))
                            .post(formBody)
                            .build();

                    okhttp3.Response response = client.newCall(request).execute();
                    String rspo = response.body().string();
                    JSONObject lginStatus = new JSONObject(rspo);
                    Log.d("lgin",lginStatus.get("session").toString());
                    session.createLoginSession(lginStatus.get("session").toString());
                    Log.d("xgx",session.getUserDetails().get("session"));

                } catch (Exception ex) {
                    String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getStackTrace());
                    ex.printStackTrace();
                }

                // 將 totalSize 傳給 onPostExecute()
            return Long.valueOf(urls.length);
        }

        protected void onProgressUpdate(Integer... progress) {
            // 這裡接收傳入的 progress 值, 並更新進度表畫面
            // 參數是 Integer 型態的陣列
            // 但是因為在 doInBackground() 只傳一個參數
            // 所以以 progress[0] 取得傳入參數

        }

        protected void onPostExecute(Long result) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }
}

