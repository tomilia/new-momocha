package com.example.tommylee.myapplication.main_frag;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.FontChangeCrawler;
import com.example.tommylee.myapplication.MainActivity;
import com.example.tommylee.myapplication.OnListFragmentInteractionListener;
import com.example.tommylee.myapplication.R;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.tommylee.myapplication.Result_Page_Activity;

import com.example.tommylee.myapplication.SearchResultModel;
import com.example.tommylee.myapplication.detail.CouponModel;
import com.example.tommylee.myapplication.detail.Detail_Activity;
import com.example.tommylee.myapplication.viewpager;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MainFragment extends Fragment implements MainPromocardAdapter.OnItemClicked {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private RecyclerView locationRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView ArticleRecyclerView;
    ArrayList<ArrayList<CouponModel>> allCouponlist = new ArrayList<ArrayList<CouponModel>>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mAdapter2;
    private ImageView drawerButton;
    private ArrayList<SearchResultModel>srms =new ArrayList<>();
    private RecyclerView.Adapter ArticleAdapter;
    private ArrayList<String> ArticleTitle;
    private RecyclerView.Adapter locationAdapter;
    private RecyclerView.LayoutManager locationLayoutManager;
    private Integer[] autoScrollImages={R.drawable.watercube,R.drawable.momocha,R.drawable.spa};
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.LayoutManager mLayoutManager2;
    MainPromocardAdapter myAdapter;
    private ArrayList<CouponModel> cpm =new ArrayList<>();
    private ArrayList<CouponModel> cpm2 =new ArrayList<>();
    private ArrayList<CouponModel> cpm3 =new ArrayList<>();
    private ArrayList<CouponModel> cpm4 =new ArrayList<>();
    private ArrayList<String> location;
    ViewPager viewPager;//slider
    RecyclerViewPager myviewPager;//promoting card
    private ArrayList<DataFetch> mDataset;
    private ArrayList<DataFetch> mDataset2;
    LinearLayout dots;
    private int dotscount;
    private ImageView[] dotsview;
    private ImageView searchbarbutton;
    private int Imagerow[];
    private int ArticleImagerow[];
    private Handler mHandler = new Handler() {// 定时轮播图片，需要在主线程里面修改UI
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == UPDATE_PAGER) {
                Log.d("msgsgs",String.valueOf(msg.arg1));
                if (msg.arg1 != 0) {
                    viewPager.setCurrentItem(msg.arg1);
                } else {
                    // 当从末页调到首页时，不显示翻页动画效果 false
                    viewPager.setCurrentItem(msg.arg1, true);
                }
            }
        }
    };

    // TODO: Rename and change types of parameters
    int[] locationimage;
    //time
    private static final int UPDATE_PAGER = 0;// 更新图片标记
    private int currentIndex = 0;// 设置当前第几个图片被选中
    private Timer timer = new Timer();// 为了方便取消定时轮播，将 Timer 设为全局
    //time

    private OnListFragmentInteractionListener mListener;
    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param /param1 Parameter 1.
     * @param /param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        return fragment;
    }
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
  /*  @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        for (String s :getActivity().getAssets().getLocales())
    Log.d("hey",s);
        FontChangeCrawler fontChanger = new FontChangeCrawler(getActivity().getAssets(), "GenJyuuGothicL-Monospace-ExtraLight.ttf");
        fontChanger.replaceFonts((ViewGroup) this.getView());
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        location = new ArrayList<>();
        mDataset = new ArrayList<>();
        mDataset2 = new ArrayList<>();
        ArticleTitle=new ArrayList<>();
        location.add("深圳－羅湖站");
        location.add("深圳－老街站");
        location.add("深圳－龍華站");
        location.add("深圳－福田口岸");
        location.add("深圳－紅山站");
        location.add("深圳－益田站");

        locationimage = new int[location.size()];
        locationimage[0]=R.drawable.locationposition0;
        locationimage[1]=R.drawable.locationposition1;
        locationimage[2]=R.drawable.locationposition2;
        locationimage[3]=R.drawable.locationposition3;
        locationimage[4]=R.drawable.locationposition4;
        locationimage[5]=R.drawable.locationposition5;
        Imagerow=new int[location.size()];
        Imagerow[0]=R.drawable.storeexample1;
        Imagerow[1]=R.drawable.storeexample2;
        Imagerow[2]=R.drawable.storeexample3;
        Imagerow[3]=R.drawable.storeexample4;
        Imagerow[4]=R.drawable.storeexample5;
        Imagerow[5]=R.drawable.storeexample6;

        ArticleTitle.add("週末好去處: Hea住嘆真正的地道中式全身按摩！");
        ArticleTitle.add("【甜蜜情人節】懷抱5層的浪漫水立方");
        ArticleTitle.add("【越北越正! 】靚裝新場: 麗晶水療登陸老街!");
        ArticleTitle.add("218包加一！Kay's SPA 不一樣的指尖享受");
        ArticleImagerow = new int[ArticleTitle.size()];
        ArticleImagerow[0]=R.drawable.article1;
        ArticleImagerow[1]=R.drawable.article2;
        ArticleImagerow[2]=R.drawable.article3;
        ArticleImagerow[3]=R.drawable.article4;
        cpm.clear();
        cpm.add(new CouponModel("水立方",60,58,48));
        cpm.add(new CouponModel("中式按摩＋拔罐/刮痧",75,88,78));
        cpm.add(new CouponModel("砭石足療＋頸肩臂按摩",90,108,88));
        cpm.add(new CouponModel("中醫推拿",90,108,98));
        cpm.add(new CouponModel("單人芳香中式全第推拿",90,158,138));
        allCouponlist.add(cpm);
        cpm2.clear();
        cpm2.add(new CouponModel("古法基礎足療",60,58,48));
        cpm2.add(new CouponModel("中式按摩＋拔罐/刮痧",75,88,78));
        cpm2.add(new CouponModel("砭石足療＋頸肩臂按摩",90,108,88));
        cpm2.add(new CouponModel("中醫推拿",90,108,98));
        cpm2.add(new CouponModel("單人芳香中式全第推拿",90,158,138));
        allCouponlist.add(cpm2);
        cpm3.clear();
        cpm3.add(new CouponModel("按摩",60,58,48));
        cpm3.add(new CouponModel("中式按摩＋拔罐/刮痧",75,88,78));
        cpm3.add(new CouponModel("砭石足療＋頸肩臂按摩",90,108,88));
        cpm3.add(new CouponModel("中醫推拿",90,108,98));
        cpm3.add(new CouponModel("單人芳香中式全第推拿",90,158,138));
        allCouponlist.add(cpm3);
        Log.d("cpm",allCouponlist.get(0).get(0).getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view=inflater.inflate(R.layout.fragment_main,container,false);

        Toolbar toolbar=view.findViewById(R.id.toolbar);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        dots = (LinearLayout) view.findViewById(R.id.Dots);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewpager viewPagerAdapter = new viewpager(view.getContext(),autoScrollImages);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.getLayoutParams().height = (int) (height / 3);
        viewPager.getLayoutParams().width=width;
        dotscount = viewPagerAdapter.getCount();
        dotsview = new ImageView[dotscount];
        srms.add(new SearchResultModel(1,"水立方"
                ,"" ,"福田區漁農村港城華庭裙樓1-6樓(近皇崗地鐵口岸)","86-172940941",
                127,133,"老街站","老街站",new ArrayList<Integer>(Arrays.asList(R.drawable.watercube1,R.drawable.watercube2))));
        srms.add(new SearchResultModel(2,"心一足道"
                ,"" ,"福田區皇崗新村45棟","0755-2391 5219",
                127,133,"皇崗山站","皇崗山站",new ArrayList<Integer>(Arrays.asList(R.drawable.heartfoot1,R.drawable.watercube1))));
        srms.add(new SearchResultModel(2,"泰舒適"
                ,"" ,"福田區皇崗新村45棟","0755-2391 5219",
                127,133,"皇崗村站","皇崗村站",new ArrayList<Integer>(Arrays.asList(R.drawable.heartfoot1,R.drawable.watercube1))));

        //code for promoting_card start
        myviewPager=(RecyclerViewPager) view.findViewById(R.id.myview);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myviewPager.setLayoutManager(manager);
        /*
        StartSnapHelper snapHelper = new StartSnapHelper();
        snapHelper.attachToRecyclerView(myviewPager);*/

        myAdapter=new MainPromocardAdapter(view.getContext(),1);
        myAdapter.setOnClick(this);
        myviewPager.setAdapter(myAdapter);
        final List<Integer> datas1 = Arrays.asList(R.color.cardview_dark_background ,R.color.base, R.color.cardview_light_background);

        //code for promoting_card end

        for (int i = 0; i < dotscount; i++) {
            dotsview[i] = new ImageView(view.getContext());
            dotsview[i].setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.nonactive_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            dots.addView(dotsview[i], params);
        }




        dotsview[0].setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dotsview[i].setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.nonactive_dot));
                }
                dotsview[position].setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.active_dot));
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }



        });





        locationRecyclerView=(RecyclerView)view.findViewById(R.id.location_recycler_view);
        locationRecyclerView.setHasFixedSize(true);

        locationLayoutManager= new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        locationRecyclerView.setLayoutManager(locationLayoutManager);
        locationAdapter=new MainLocationAdapter(view.getContext(),location,locationimage);
        locationRecyclerView.setAdapter(locationAdapter);

        mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        //drawerLayout=getActivity().findViewById(R.id.container);
        TestTask(0);

        mAdapter = new MainAdapter(view.getContext(), mDataset, 0,Imagerow);

        ArticleRecyclerView=(RecyclerView)view.findViewById(R.id.message_row_recycler_view);
        ArticleRecyclerView.setHasFixedSize(true);

        ArticleRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        ArticleRecyclerView.setHasFixedSize(true);
        ArticleAdapter=new MainFourInRowAdapter(view.getContext(),ArticleTitle,ArticleImagerow);
        ArticleRecyclerView.setAdapter(ArticleAdapter);
        ArticleRecyclerView.setLayoutFrozen(true);
        mRecyclerView2 = (RecyclerView) view.findViewById(R.id.recycler_view2);
        mRecyclerView2.setHasFixedSize(true);
        mLayoutManager2 = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);



        mRecyclerView2.setLayoutManager(mLayoutManager2);
        TestTask(1);
        ArrayList<Integer> ei = new ArrayList<>();
        ei.add(R.drawable.watercube1);
        ei.add(R.drawable.watercube2);
        ei.add(R.drawable.heartfoot1);
        ei.add(R.drawable.spa);
        mAdapter2 = new MainPromocardAdapter(view.getContext(),2);
        mRecyclerView2.setAdapter(mAdapter2);



      /*  searchbarbutton = (ImageView) view.findViewById(R.id.search_bar);
        searchbarbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Search_Activity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
            }
        });*/

        try {

            Drawable d = (Drawable) getResources().getDrawable(R.drawable.mapdetail);

        }catch (Exception e){
            e.printStackTrace();
        }
/*
        drawerButton = (ImageView) view.findViewById(R.id.mapcaller);
        drawerButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        drawerLayout.openDrawer(Gravity.LEFT);
                    }
                });
                */
/*
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = UPDATE_PAGER;
                if (currentIndex == autoScrollImages.length - 1) {
                    currentIndex = -1;
                }


                message.arg1=currentIndex+1;
                Log.d("msg11",String.valueOf(currentIndex));
                mHandler.sendMessage(message);
            }
        }, 3000, 3000);
*/
            return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onListFragmentInteraction(this.getTag(),uri);
        }
    }
    //mylam timer

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


        @Override
        public void onItemClick(int position) {

            Intent intent = new Intent(getActivity().getApplication().getApplicationContext(), Detail_Activity.class);
            intent.putExtra("id",srms.get(position).getId());
            intent.putExtra("name",srms.get(position).getCHtitle());
            intent.putExtra("district",srms.get(position).getDistrict());
            intent.putExtra("full_address",srms.get(position).getFull_address());
            intent.putExtra("coupon",allCouponlist.get(position));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

            startActivity(intent);

            Log.d("pos",String.valueOf(allCouponlist.size()));
        }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void TestTask(final int id) {
        AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();


            }


            @Override
            protected Void doInBackground(Integer... integer) {


                OkHttpClient client = new OkHttpClient();
                Request request;
                if(id==0) {
                    request = new Request.Builder().url("http://192.168.0.101:8000/filter/?q=").build();
                }
                else {
                    request = new Request.Builder().url("http://58.176.222.168:5555/autocomplete?company=z").build();
                    Log.d("checksum2",String.valueOf(id));
                }

                try {



                    Response response = client.newCall(request).execute();

                    JSONObject jsonarray = new JSONObject(response.body().string());

                    /*for (int i = 0; i < jsonarray.length(); i++) {

                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("company");
                        String url = jsonobject.getString("address");
                        DataFetch fetch = new DataFetch(jsonobject.getString("company"), jsonobject.getString("address"),jsonobject.getInt("price"),jsonobject.getString("_id"));
                        if(id==0)
                            mDataset.add(fetch);
                        else
                            mDataset2.add(fetch);
                    }*/
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                mAdapter.notifyDataSetChanged();

            }

            private String RetrieveData(String UrlPath) throws IOException {
                StringBuilder result = new StringBuilder();
                BufferedReader buffer = null;
                try {
                    URL url = new URL(UrlPath);

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    Log.wtf("connect123", urlConnection.toString());
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setConnectTimeout(10000);

                    urlConnection.setRequestMethod("GET");

                    urlConnection.setRequestProperty("Content-Type", "application/JSON");

                    urlConnection.connect();

                    InputStream input = urlConnection.getInputStream();
                    buffer = new BufferedReader(new InputStreamReader(input));

                    String line;
                    while ((line = buffer.readLine()) != null) {
                        result.append(line).append("\n");

                    }

                } finally {
                    if (buffer != null)
                        buffer.close();
                }
                return result.toString();
            }
        };
        task.execute(id);
    }
    public class ColumnQty {
        private int width, height, remaining;
        private DisplayMetrics displayMetrics;

        public ColumnQty(Context context, int viewId) {

            View view = View.inflate(context, viewId, null);
            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            width = view.getMeasuredWidth();
            height = view.getMeasuredHeight();
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        public int calculateNoOfColumns() {

            int numberOfColumns = displayMetrics.widthPixels / width;
            remaining = displayMetrics.widthPixels - (numberOfColumns * width);
//        System.out.println("\nRemaining\t" + remaining + "\nNumber Of Columns\t" + numberOfColumns);
            if (remaining / (2 * numberOfColumns) < 15) {
                numberOfColumns--;
                remaining = displayMetrics.widthPixels - (numberOfColumns * width);
            }
            return numberOfColumns;
        }
        public int calculateSpacing() {

            int numberOfColumns = calculateNoOfColumns();
//        System.out.println("\nNumber Of Columns\t"+ numberOfColumns+"\nRemaining Space\t"+remaining+"\nSpacing\t"+remaining/(2*numberOfColumns)+"\nWidth\t"+width+"\nHeight\t"+height+"\nDisplay DPI\t"+displayMetrics.densityDpi+"\nDisplay Metrics Width\t"+displayMetrics.widthPixels);
            return remaining / (2 * numberOfColumns);
        }
    }
}
