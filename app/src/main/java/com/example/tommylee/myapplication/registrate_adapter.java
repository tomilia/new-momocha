package com.example.tommylee.myapplication;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class registrate_adapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"電話", "電郵"};

    public registrate_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
    if (position == 1) {
            return new registration_email();
        }
        return new registration_phone_frag();

    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }




}