package com.example.tommylee.myapplication.Sessions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tommylee.myapplication.main_frag.MemberStruct;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public class SessionManager {
    SharedPreferences pref;
    Boolean is_login = false;
    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)

    // Email address (make variable public to access from outside)
    public static final String KEY_SESSION = "session";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String ssion){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref

        // Storing email in pref
        editor.putString(KEY_SESSION, ssion);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public boolean checkLogin() throws IOException {
        // Check login status

        URI uri = null;
        try {
            uri = new URI("http://59.148.36.170:8000/test/");
            URL url = uri.toURL();

            LoginCheckTask lt = new LoginCheckTask();

           is_login=lt.execute(url).get();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("stux",String.valueOf(is_login));
            return is_login;
            /*
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
            */


    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name

        // user email id
        user.put(KEY_SESSION, pref.getString(KEY_SESSION, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
 /*   public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
*/
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    private class LoginCheckTask extends AsyncTask<URL, Integer, Boolean> {
        private ProgressDialog dialog;
        private boolean lgin = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected Boolean doInBackground(URL... urls) {
            Log.d("url",urls[0].toString());



            try {

                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                OkHttpClient client = new OkHttpClient();

                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url(urls[0])
                        .addHeader("Content-Type","application/json")
                        .addHeader("cookie","sessionid="+getUserDetails().get("session"))
                        .build();

                okhttp3.Response response = client.newCall(request).execute();
                JSONObject lginStatus = new JSONObject(response.body().string());

                lgin = lginStatus.getBoolean("lgin");
                Log.d("stuxx",String.valueOf(lgin));

            } catch (Exception ex) {
                String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getStackTrace());
                ex.printStackTrace();
            }
            return lgin;
            // 將 totalSize 傳給 onPostExecute()
        }

        protected void onProgressUpdate(Integer... progress) {
            // 這裡接收傳入的 progress 值, 並更新進度表畫面
            // 參數是 Integer 型態的陣列
            // 但是因為在 doInBackground() 只傳一個參數
            // 所以以 progress[0] 取得傳入參數

        }

        protected void onPostExecute(Boolean result) {

        }
    }
    }
