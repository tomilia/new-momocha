package com.example.tommylee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tommylee.myapplication.main_frag.ItemFourFrag;
import com.example.tommylee.myapplication.main_frag.ItemThreeFrag;
import com.example.tommylee.myapplication.main_frag.ItemTwoFrag;
import com.example.tommylee.myapplication.main_frag.MainFragment;
import com.example.tommylee.myapplication.settings.SettingFragment;
import com.example.tommylee.myapplication.traffic.TrafficFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener<Object>,NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ImageView searchbarbutton;
    int checkpagetype=R.id.navigation_home;
    BottomNavigationView bottomNavigationView;
    String mapnames[] ={"騰訊地圖","Google地圖"};
    NavigationView mNavigationView;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("GenJyuuGothicL-Monospace-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        if(savedInstanceState==null) {
            setContentView(R.layout.activity_main);

            try {
                Log.d("version",getPackageManager().getPackageInfo("com.google.android.gms", 0 ).versionName);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

         bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {

                   MainFragment mainFragment = new MainFragment();
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                       switch (item.getItemId()) {
                           case R.id.action_item1:
                               selectedFragment = ItemThreeFrag.newInstance();
                               break;

                            case R.id.action_item2:
                                selectedFragment = ItemTwoFrag.newInstance();
                                break;
                           case R.id.action_item3:
                               selectedFragment = mainFragment;
                               break;
                           case R.id.action_item4:
                               selectedFragment = ItemFourFrag.newInstance();
                               break;
                           case R.id.action_item5:
                               selectedFragment = ItemFourFrag.newInstance();
                               break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frag_content, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        MainFragment mainFragment=new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_content,mainFragment).commit();
        drawerLayout=findViewById(R.id.container);
        mNavigationView = (NavigationView) findViewById(R.id.hamburgermenu);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);

        }

        bottomNavigationView.setSelectedItemId(R.id.action_item3);
        }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override

    public void onStop(){
        Log.d("tryyy","check");
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.traffic:
                Log.d("traffic","traffic");
                return true;
            default:
                Log.d("traffic","traffic");
            return false;
        }

    }


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(id){
            case R.id.navigation_home:
                drawerLayout.closeDrawer(Gravity.LEFT);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                // your code here
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                MainFragment mainFragment = new MainFragment();
                                ft.replace(R.id.frag_content, mainFragment);
                                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                ft.addToBackStack(null);
                                ft.commit();

                            }
                        },
                        400
                );


                return true;
            case R.id.navigation_map:

                return true;
            case R.id.navigation_setting:
                SettingFragment settingFragment = new SettingFragment();
                ft.replace(R.id.frag_content, settingFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);

                ft.commit();
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            case R.id.traffic:
                TrafficFragment trafficFragment = new TrafficFragment();
                ft.replace(R.id.frag_content, trafficFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;

        }

        return false;
        }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }




    @Override
    public void onListFragmentInteraction(String tag, Object data) {
    }

}
