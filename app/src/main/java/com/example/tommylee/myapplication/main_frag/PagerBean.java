package com.example.tommylee.myapplication.main_frag;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tommylee.myapplication.DataFetch;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.Result_Page_Activity;
import com.greenfrvr.hashtagview.HashtagView;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

//import com.example.tommylee.myapplication.SearchResultAdapter;

public class PagerBean {
    //新闻ID
    private int index;
    //新闻图片的url
    private String imageUrl;

    public PagerBean(int index, String imageUrl) {
        this.imageUrl = imageUrl;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}