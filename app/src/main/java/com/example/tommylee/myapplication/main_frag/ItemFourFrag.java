package com.example.tommylee.myapplication.main_frag;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.registration;
import com.example.tommylee.myapplication.registration_forget;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
                    uri = new URI("http://192.168.0.101:8000/m_login/");
                    URL url = uri.toURL();
                    LoginTask lt = new LoginTask();
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
        // 對照前面提到的 3 個傳入的參數
        // URL 就是 Params 參數的類別
        // Integer 就是 Progress 參數的類別
        // Long 就是 Result 參數的類別
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
                            .addHeader("CONTENT_TYPE","application/x-www-form-urlencoded")
                            .post(formBody)
                            .build();

                    okhttp3.Response response = client.newCall(request).execute();
                    Log.d("response",response.body().string());
                } catch (Exception ex) {
                    String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());

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

        }
    }
}

