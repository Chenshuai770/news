package com.cs.news1.viwes;

/**
 * Created by chenshuai on 2016/10/25.
 */


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;


public  abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener{
    //声明一个LinearLayoutManager
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    //当前页，从0开始
    private int currentPage = 0;
    //已经加载出来的Item的数量
    private int totalItemCount;

    //主要用来存储上一个totalItemCount
    private int previousTotal = 0;

    //在屏幕上可见的item数量
    private int visibleItemCount;

    //在屏幕可见的Item中的第一个
    private int firstVisibleItem;

    //是否正在上拉数据
    private boolean loading = true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public EndLessOnScrollListener(StaggeredGridLayoutManager mStaggeredGridLayoutManager) {
        this.mStaggeredGridLayoutManager = mStaggeredGridLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //在屏幕上可见的item数量
        visibleItemCount = recyclerView.getChildCount();
        //已经加载出来的Item的数量
        totalItemCount = mLinearLayoutManager.getItemCount();
        //在屏幕可见的Item中的第一个
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(loading){
            Log.d("wnwn","firstVisibleItem:在屏幕可见的Item中的第一个 " +firstVisibleItem);
            Log.d("wnwn","totalPageCount:已经加载出来的Item的数量" +totalItemCount);
            Log.d("wnwn", "visibleItemCount:在屏幕上可见的item数量" + visibleItemCount);

            if(totalItemCount > previousTotal){
                //说明数据已经加载结束
                loading = false;
                //主要用来存储上一个totalItemCount
                previousTotal = totalItemCount;
            }
        }
        //这里需要好好理解,loading 是正在加载中,当前页面的所有条目-可以看到的条目<=1条的时候,执行刷新,页面加载1
        if (!loading && totalItemCount-visibleItemCount <= firstVisibleItem){
            currentPage ++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    /**
     * 提供一个抽闲方法，在Activity中监听到这个EndLessOnScrollListener
     * 并且实现这个方法
     * */
    public abstract void onLoadMore(int currentPage);
}


