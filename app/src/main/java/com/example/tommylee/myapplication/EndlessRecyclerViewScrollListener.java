package com.example.tommylee.myapplication;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import static java.lang.String.valueOf;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerViewScrollListener.class.getSimpleName();
    public int i=1;
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = false; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 2; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount,lastVisibleItem,comlastVisibleItem;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy < 0) {
            return;
        }
        // check for scroll down only
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        comlastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
//
        if(visibleItemCount > firstVisibleItem)
            Log.i("MOOOOOOOOOV", "scroll up!");
        else
            Log.i("MOOOOOOOOOV", "scroll down!");
        firstVisibleItem = visibleItemCount;
//
        Log.d("onScrolled", valueOf(totalItemCount)+" "+ valueOf(visibleItemCount)+" pos"+ valueOf(firstVisibleItem)+" last"+ valueOf(lastVisibleItem)+" "+ valueOf(comlastVisibleItem));
        // to make sure only one onLoadMore is triggered
        synchronized (this) {

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached, Do something
                    Log.d("!loading", valueOf(totalItemCount)+" "+ valueOf(visibleItemCount)+"pos "+ valueOf(firstVisibleItem)+" "+ valueOf(lastVisibleItem)+" "+ valueOf(comlastVisibleItem)+" "+ valueOf(current_page));
                    current_page++;
                    onLoadMore(current_page);
                    Log.d("!loading",valueOf(current_page));
                    loading = true;
                }
            }
        }



    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public abstract void onLoadMore(int current_page);
}
