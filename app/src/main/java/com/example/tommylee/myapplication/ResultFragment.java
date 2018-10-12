package com.example.tommylee.myapplication;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tommylee.myapplication.main_frag.Search_unfocus_frag;
import com.example.tommylee.myapplication.main_frag.search_unfocus_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static java.lang.Thread.sleep;

/**
 * Created by tommylee on 17/12/2017.
 */

public class ResultFragment extends Fragment {
    public int a;
    ArrayList<DataFetch> result;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDist = new ArrayList<>();
    private ProgressBar spinner;
    public static TextView data;
    public int i=1;
    RecyclerView recyclerView;
    SearchResultAdapter adapter;
    private EndlessRecyclerViewScrollListener mScrollListener = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    public void reloadData(String url){
        i=1;
        Log.d("reloadi",String.valueOf(i));
        TransTaskfilter tt = new TransTaskfilter();
        tt.execute(url);
        Log.d("reloadiiiiiiiiiiiii",String.valueOf(url));
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.result_fragment,container,false);
        spinner = (ProgressBar)view.findViewById(R.id.progressBar1);
        Bundle strtext = getArguments();

        try{
            result=new ArrayList<>();
            initRecyclerView(view);
            TransTaskfilter tt=new TransTaskfilter();
            tt.execute("");

        }
        catch(Exception e){ }
        return view;
    }

    private void initRecyclerView(final View v){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = v.findViewById(R.id.result_list);
        adapter = new SearchResultAdapter(v.getContext(), mNames, mImageUrls,mDist);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        //enable pull up for endless loading
        mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                // do something...
                spinner.setVisibility(View.VISIBLE);
                TransTaskfilter tt = new TransTaskfilter();
                tt.execute("&page"+current_page);
                TransTaskfilter tt1 = new TransTaskfilter();
                tt1.execute("&page3");

                //reloadData("&page"+current_page);
                Log.d("onloadmore","onloadmore");
                // after loading is done, please call the following method to re-enable onLoadMore
                // usually it should be called in onCompleted() method
                mScrollListener.setLoading(false);
                spinner.setVisibility(View.GONE);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(mScrollListener);;
        // enable pull down to refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something
                spinner.setVisibility(View.VISIBLE);
                TransTaskfilter tt = new TransTaskfilter();
                tt.execute("");

                Log.d("refresh","refresh");
                // after refresh is done, remember to call the following code
                if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setRefreshing(false);  // This hides the spinner
                }
                spinner.setVisibility(View.GONE);
            }
        });
    }


    ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
        ArrayList<String> stringArray = new ArrayList<String>();
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            stringArray.add(jsonArray.getString(i));
        }
        return stringArray;
    }
    public class TransTaskfilter extends AsyncTask<String, Void, String> {

        String data = "";
        String dataParsed = "";
        String singleParsed = "";
        String sname="";
        String dists="";
        String pass = "";
        public int passnum;
        @Override
        protected void onPreExecute() {
            spinner.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            StringBuilder sb = new StringBuilder();
            try{
                if(i==1){mNames.clear();
                mDist.clear();
                mImageUrls.clear();}
                //URL url = new URL("https://api.myjson.com/bins/yaf58");
                URL url = new URL("http://192.168.0.101:8000/filter/?q=&sort="+params[0]);
                Log.d("reloading",url.toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while(line != null){
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONObject mainObject = new JSONObject(data);

                ArrayList<String> str=jsonStringToArray(mainObject.get("numpost").toString());
                passnum =str.size();
                Log.d("num123",String.valueOf(passnum));
                for(String s : str){
                    JSONObject jsonObj = new JSONObject(s);

                    Log.d("jsons",jsonObj.get("CHtitle").toString());
                    Log.d("jsons",jsonObj.get("id").toString());

                    singleParsed = jsonObj.get("i_id").toString() +"\n"
                            +jsonObj.get("id").toString() +"\n"+jsonObj.get("CHtitle").toString() +"\n"+
                            jsonObj.get("ENGtitle").toString() +"\n"+jsonObj.get("CHtitle").toString() +"\n"
                            +jsonObj.get("full_address").toString() +"\n"+jsonObj.get("telephone").toString()+"\n"
                            +jsonObj.get("number").toString()+"\n"+jsonObj.get("count").toString()+"\n"+jsonObj.get("lat").toString()+"\n"
                            +jsonObj.get("long").toString()+"\n"+jsonObj.get("station").toString();
                    sname=jsonObj.get("CHtitle").toString();
                    dists=jsonObj.get("full_address").toString();
                    mNames.add(sname);
                    mDist.add(dists);
                    mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");

                    Log.d("single",String.valueOf(mNames.size())+" "+String.valueOf(mImageUrls.size())+" "+String.valueOf(mDist.size()));
                    dataParsed = dataParsed + singleParsed + "\n";
                }
                Log.d("finishjson","finishjson");
            } catch (MalformedURLException e) {
                Log.d("testingurl","testing");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("testingio","testing");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.d("testingjson","testing");
                e.printStackTrace();
            }
            Log.d("doexe",dataParsed);
            return dataParsed;

        }
        @Override
        protected void onPostExecute(String s) {
            Log.d("postexe",s);
            super.onPostExecute(s);
            Log.d("JSON", s);
            spinner.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
            if (i==1) {recyclerView.scrollToPosition(0);}
            TextView txt4=getActivity().findViewById(R.id.textView4);
            txt4.setText("返回共"+passnum+"個結果");
        }

    }
    }

