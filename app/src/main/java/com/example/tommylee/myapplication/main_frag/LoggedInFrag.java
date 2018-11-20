
package com.example.tommylee.myapplication.main_frag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.CommonAdapter.CommonAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class LoggedInFrag extends Fragment {
   Button setting;
   Button logout;
   TextView username;
    SessionManager session;
    RecyclerView rv;
    CommonAdapter a;
    ArrayList<String> options  = new ArrayList<String>();
    public static LoggedInFrag newInstance() {
        LoggedInFrag fragment = new LoggedInFrag();
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

        View v=inflater.inflate(R.layout.fragment_loggedin, container, false);
        username = v.findViewById(R.id.username);
        logout = v.findViewById(R.id.fragment_right_logout);
        options.add("邀請朋友");
        options.add("意見反饋");
        options.add("我要投訴");
        rv=(RecyclerView)v.findViewById(R.id.rvid);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        a = new CommonAdapter(getContext(),options);
        rv.setAdapter(a);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("foapsi","fodsv");
            }


        });
        URI uri = null;
        try {
            uri = new URI("http://59.148.36.170:8000/test/");
            URL url = uri.toURL();

            UserDataRetrieveTask lt = new UserDataRetrieveTask();

            lt.execute(url);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
    private class UserDataRetrieveTask extends AsyncTask<URL, Integer, MemberStruct> {
        private ProgressDialog dialog;
        MemberStruct memberStruct;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected MemberStruct doInBackground(URL... urls) {
            Log.d("url",urls[0].toString());



            try {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();
                session = new SessionManager(getContext());
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
                Log.d("stus",lginStatus.toString());
                memberStruct = new MemberStruct(lginStatus.getInt("id"),lginStatus.getBoolean("lgin"),
                        lginStatus.getString("first_name"),lginStatus.getString("username"),lginStatus.getString("email"),
                        lginStatus.getInt("mcash"),lginStatus.getString("phone_num"));
            } catch (Exception ex) {
                String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getStackTrace());
                ex.printStackTrace();
            }

            // 將 totalSize 傳給 onPostExecute()
            return memberStruct;
        }

        protected void onProgressUpdate(Integer... progress) {
            // 這裡接收傳入的 progress 值, 並更新進度表畫面
            // 參數是 Integer 型態的陣列
            // 但是因為在 doInBackground() 只傳一個參數
            // 所以以 progress[0] 取得傳入參數

        }

        protected void onPostExecute(MemberStruct result) {
            if (result.getFirst_name()!=null)
            {
                username.append(result.getFirst_name());
            }
            else if (result.getUsername()!=null)
            {
                username.append(result.getUsername());
            }
            else{
                username.append(result.getPhone_num());
            }

        }
    }
}

