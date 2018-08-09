package com.example.tommylee.myapplication.main_frag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.Result_Page_Activity;
import com.example.tommylee.myapplication.SavedResultAdapter;
//import com.example.tommylee.myapplication.SearchResultAdapter;
import com.example.tommylee.myapplication.SearchResultAdapter;
import com.example.tommylee.myapplication.SmartScreen_Activity;
import com.example.tommylee.myapplication.detail.Detail_Activity;
import com.greenfrvr.hashtagview.HashtagView;
import com.orhanobut.hawk.Hawk;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ItemTwoFrag extends Fragment {
    // List view
    private SharedPreferences savedstring;
    private static final String inputstring="KEYWORD";
    private ListView lv;
    Uri.Builder builder = new Uri.Builder();
    private ArrayList<String> recent=new ArrayList<String>();
    private LinearLayout layout;
    private LinearLayout layout2;

    protected HashtagView hashtagView1;
    private static int counter;
    private boolean checknull=true;
    private boolean hotsearch=true;
    private boolean waitAsyc=true;
    ArrayList<DataFetch> productResults = new ArrayList<DataFetch>();
    // Listview Adapter
    AutoCompleteTextView edit;
    FrameLayout frag;
    private ProgressBar spinner;
    public static ItemTwoFrag newInstance() {
        ItemTwoFrag fragment = new ItemTwoFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_item_two, container, false);

        Toolbar toolbar = view.findViewById(R.id.search_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton but=view.findViewById(R.id.problem);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f = getChildFragmentManager().findFragmentById(R.id.search_page_frag);
                Log.d("fragmenttt",f.toString());
                if(f instanceof Search_unfocus_frag)
                {
                    FragmentTransaction ft=getChildFragmentManager().beginTransaction();
                    search_focus_frag ff=new search_focus_frag();
                    ft.replace(R.id.search_page_frag,ff);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else if (f instanceof search_focus_frag)
                {
                    FragmentTransaction ft=getChildFragmentManager().beginTransaction();
                    Search_unfocus_frag ff=new Search_unfocus_frag();
                    ft.replace(R.id.search_page_frag,ff);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });
/*
        lv = view.findViewById(R.id.list_view);
        LayoutInflater myinflater = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup)myinflater.inflate(R.layout.listviewheaderlayout, lv, false);
        lv.addHeaderView(myHeader, null, false);
        Hawk.init(ItemTwoFrag.this.getContext()).build();
        spinner=view.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
*/
        edit=view.findViewById(R.id.searchmain);
        frag=view.findViewById(R.id.search_page_frag);
        layout= view.findViewById(R.id.table);//Can also be done in xml by android:orientation="vertical"
       // ItemTwoFrag.myTask hotsearchregex=new ItemTwoFrag.myTask();
       // hotsearchregex.execute("p");
        List<String> temp=new ArrayList<String>();
        for(int i=0;i<productResults.size();i++) {
            temp.add( productResults.get(i).getDescription());

        }

        HashtagView hashtagView=view.findViewById(R.id.hashtags1);

        //hashtagView.setData(temp);
        layout2=view.findViewById(R.id.table2);//Can also be done in xml by android:orientation="vertical"

        final ListView recentr=view.findViewById(R.id.resultrecent);
/*
        for(int a=0;a<Hawk.count();a++)
            recent.add(a,Hawk.get(String.valueOf(a)).toString());
        final SavedResultAdapter adapter = new SavedResultAdapter(view.getContext(),recent);

        recentr.setAdapter(adapter);
        recentr.setVisibility(View.VISIBLE);
  */
        FragmentTransaction ft=getChildFragmentManager().beginTransaction();
        Search_unfocus_frag ff=new Search_unfocus_frag();
        ft.replace(R.id.search_page_frag,ff);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasfocus) {
                if (hasfocus) {
                    Log.e("TAG", "e1 focused");
                    edit.setCursorVisible(true);
                    FragmentTransaction ft=getChildFragmentManager().beginTransaction();
                    search_focus_frag ff=new search_focus_frag();
                    ft.replace(R.id.search_page_frag,ff);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                    frag.requestFocus();

                } else {
                    Log.e("TAG", "e1 not focused");

                    edit.setCursorVisible(false);
                }
            }
        });

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Log.d("texthahah",String.valueOf(actionId));
                if (actionId == EditorInfo.IME_ACTION_GO&&!edit.getText().toString().equals("")) {

                    recent.add((int)(Hawk.count()),edit.getText().toString());
                    Hawk.put(String.valueOf((int)Hawk.count()),edit.getText().toString());
                    for(int x=0;x<Hawk.count();x++)
                        Log.d("xmen",x+" "+ Hawk.get(String.valueOf(x)).toString());
                    Log.d("spalachi",String.valueOf(Hawk.count())+" "+String.valueOf(recent.size()));

                    Intent intent = new Intent(((AppCompatActivity)getActivity()).getApplicationContext(), Result_Page_Activity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    intent.putExtra("url",uriBuilder(edit.getText().toString(),0));
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });


        edit.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("hahah",String.valueOf(i));
                if(i == KeyEvent.KEYCODE_BACK) {


                    return true;
                }
                else return false;
            }
        });
        edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub


            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
/*
                if (arg0.length() > 0) {

                    //     ItemTwoFrag.myTask task = new ItemTwoFrag.myTask();
                    spinner.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                    checknull = false;
                    //   task.execute(arg0.toString());
                    layout.setVisibility(View.GONE);
                    hotsearch = false;
                    layout2.setVisibility(View.GONE);
                } else {
                    checknull = true;
                    lv.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    hotsearch = true;
                    spinner.setVisibility(View.GONE);
                    Log.d("monty", String.valueOf(hotsearch));
                }
                */

            }
        });


/*
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("test",String.valueOf(id));
                Intent intent = new Intent(v.getContext(), Detail_Activity.class);
                intent.putExtra("id",productResults.get((int)id).getId());
                intent.putExtra("name",productResults.get((int)id).getDescription());
                intent.putExtra("district",productResults.get((int)id).getImage_link());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);

            }
        });
        recentr.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("scaoc",recent.get(position));
            }
        });
        */
        return view;

    }




    //functions
   /* @Override

    public void onChangeToSmart(View view) {
        Intent intent = new Intent(view.getContext(), SmartScreen_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity(intent);

    }
    @Override
    public boolean onSupportNavigateUp() {
        edit.clearFocus();
        onBackPressed();
        return true;
    }

    public void onSupportNavigateUp2(View view) {
        edit.clearFocus();
        onBackPressed();

    }*/
    public void showAlertDialogButtonClicked(View view) {

        Log.d("hello","123");

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
    private String uriBuilder(String buildpath,int querytype) {
        builder.clearQuery();
        switch (querytype) {
            case 0:builder.appendQueryParameter("company", buildpath);
                break;
            case 1:builder.appendQueryParameter("location", buildpath);
                break;
        }
        String myUrl = builder.build().toString();
        return myUrl;
    }
   /* public class myTask extends AsyncTask<String, Void, String>{
        JSONObject jObject;
        JSONArray searchList;
        String URL;
        String textSearch;




        @Override
        protected String doInBackground(String... sText) {
            searchList=new JSONArray();
            jObject=new JSONObject();
            OkHttpClient client = new OkHttpClient();
            Request request;
            OkHttpClient client1=client.newBuilder().build();
            request = new Request.Builder().url("http://58.176.222.168:5555/autocomplete?company="+sText[0]).build();

            try {

                Response response = client1.newCall(request).execute();
                productResults.clear();
                JSONArray jsonarray = new JSONArray(response.body().string());
                for (int i = 0; i < jsonarray.length(); i++) {

                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String name = jsonobject.getString("company");
                    String url = jsonobject.getString("address");
                    int averageprice=jsonobject.getInt("price");
                    String Id=jsonobject.getString("_id");
                    Log.d("jsonn",sText[0]+" "+name+" "+Id);
                    DataFetch fetch = new DataFetch(name,url,averageprice,Id);


                    productResults.add(fetch);
                }

            } catch (Exception e) {
                Log.d("showa","FUCK");
                e.printStackTrace();
            }
            waitAsyc=false;
            return sText[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("montyx",String.valueOf(hotsearch));

            if(!hotsearch) {

                lv.setAdapter(new SearchResultAdapter(ItemTwoFrag.this.getContext(), productResults));

                spinner.setVisibility(View.GONE);
                if (lv.getCount() != 0 && !checknull)
                    lv.setVisibility(View.VISIBLE);
                else if (lv.getCount() == 0 || checknull)
                    lv.setVisibility(View.GONE);

            }

        }


    };*/
   @Override
   public void onActivityCreated(Bundle savedInstanceState) {
       super.onActivityCreated(savedInstanceState);
       final View root = getActivity().getWindow().getDecorView().findViewById(R.id.coor);

       root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
           public void onGlobalLayout() {
               int heightDiff = root.getRootView().getHeight() - root.getHeight();
               // IF height diff is more then 150, consider keyboard as visible.
               Log.d("slideactivity", "total " + root.getRootView().getHeight());
               Log.d("slideactivity", "total " + root.getHeight());
               Log.d("slideactivity", "heightdiff = " + heightDiff);
               /*if (heightDiff<934&&edit.hasFocus()){
                   edit.clearFocus();

               }*/
           }
       });
   }
}