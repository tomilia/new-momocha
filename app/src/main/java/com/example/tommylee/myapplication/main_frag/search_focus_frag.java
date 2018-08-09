package com.example.tommylee.myapplication.main_frag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.Result_Page_Activity;
import com.example.tommylee.myapplication.SavedResultAdapter;
import com.example.tommylee.myapplication.detail.Detail_Activity;
import com.greenfrvr.hashtagview.HashtagView;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class search_focus_frag extends Fragment {
    private RecyclerView recyclerView;
    private search_focus_adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String>title, subtitle;
    private ArrayList<String> tImage = new ArrayList<>();

    //public search_focus_frag fragment = new search_focus_frag();
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
    private ProgressBar spinner;
    public static ItemTwoFrag newInstance() {
        ItemTwoFrag fragment = new ItemTwoFrag();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



        title = new ArrayList<>();
        subtitle = new ArrayList<>();

        tImage.add("https://www.lunapic.com/editor/premade/blur.gif");
        title.add("Apla");
        tImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKxhUtdp915j5M3swNBRWUX0TqQjH-Q5gBY7s5rmorBoHFm5zk");
        title.add("Beta");
        tImage.add("https://ak1.picdn.net/shutterstock/videos/12210521/thumb/1.jpg");
        title.add("Gamma");
        tImage.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        title.add("ZexaL");
        tImage.add("https://i.redd.it/k98uzl68eh501.jpg");
        title.add("Delta");
        tImage.add("https://www.lunapic.com/editor/premade/blur.gif");
        title.add("1Apla");
        tImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKxhUtdp915j5M3swNBRWUX0TqQjH-Q5gBY7s5rmorBoHFm5zk");
        title.add("1Beta");
        tImage.add("https://ak1.picdn.net/shutterstock/videos/12210521/thumb/1.jpg");
        title.add("1Gamma");
        tImage.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        title.add("1ZexaL");
        tImage.add("https://i.redd.it/k98uzl68eh501.jpg");
        title.add("1Delta");
        tImage.add("https://www.lunapic.com/editor/premade/blur.gif");
        title.add("2Apla");
        tImage.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKxhUtdp915j5M3swNBRWUX0TqQjH-Q5gBY7s5rmorBoHFm5zk");
        title.add("2Beta");
        tImage.add("https://ak1.picdn.net/shutterstock/videos/12210521/thumb/1.jpg");
        title.add("2Gamma");
        tImage.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        title.add("2ZexaL");
        tImage.add("https://i.redd.it/k98uzl68eh501.jpg");
        title.add("2Delta");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_search_, container, false);
        lv = view.findViewById(R.id.list_view);
        LayoutInflater myinflater = getLayoutInflater();
        ViewGroup myHeader = (ViewGroup)myinflater.inflate(R.layout.listviewheaderlayout, lv, false);
        lv.addHeaderView(myHeader, null, false);
        Hawk.init(search_focus_frag.this.getContext()).build();
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < productResults.size(); i++) {
            temp.add(productResults.get(i).getDescription());
        }


        recyclerView = (RecyclerView) view.findViewById(R.id.content_search_recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new search_focus_adapter(view.getContext(),title,tImage);
        recyclerView.setAdapter(recyclerAdapter);


        HashtagView hashtagView = view.findViewById(R.id.hashtags1);

        //hashtagView.setData(temp);
        layout2 = view.findViewById(R.id.table2);//Can also be done in xml by android:orientation="vertical"

        final ListView recentr = view.findViewById(R.id.resultrecent);
        for (int a = 0; a < Hawk.count(); a++)
            recent.add(a, Hawk.get(String.valueOf(a)).toString());
        final SavedResultAdapter adapter = new SavedResultAdapter(view.getContext(), recent);

        recentr.setAdapter(adapter);
        recentr.setVisibility(View.VISIBLE);
/*
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View arg0, boolean hasfocus) {
                if (hasfocus) {
                    Log.e("TAG", "e1 focused");
                } else {
                    Log.e("TAG", "e1 not focused");
                }
            }
        });

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Log.d("texthahah", String.valueOf(actionId));
                if (actionId == EditorInfo.IME_ACTION_GO && !edit.getText().toString().equals("")) {

                    recent.add((int) (Hawk.count()), edit.getText().toString());
                    Hawk.put(String.valueOf((int) Hawk.count()), edit.getText().toString());
                    for (int x = 0; x < Hawk.count(); x++)
                        Log.d("xmen", x + " " + Hawk.get(String.valueOf(x)).toString());
                    Log.d("spalachi", String.valueOf(Hawk.count()) + " " + String.valueOf(recent.size()));

                    Intent intent = new Intent(((AppCompatActivity) getActivity()).getApplicationContext(), Result_Page_Activity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("url", uriBuilder(edit.getText().toString(), 0));
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });
        edit.requestFocus();
        edit.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d("hahah", String.valueOf(i));
                if (i == KeyEvent.KEYCODE_BACK) {

                    edit.clearFocus();
                    return true;
                } else return false;
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

            }
        });
*/
        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("test", String.valueOf(id));
                Intent intent = new Intent(v.getContext(), Detail_Activity.class);
                intent.putExtra("id", productResults.get((int) id).getId());
                intent.putExtra("name", productResults.get((int) id).getDescription());
                intent.putExtra("district", productResults.get((int) id).getImage_link());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        recentr.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.d("scaoc", recent.get(position));
            }
        });
        return view;
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


       // return inflater.inflate(R.layout.content_search_, container, false);
}

