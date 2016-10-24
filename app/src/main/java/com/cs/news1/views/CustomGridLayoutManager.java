package com.cs.news1.views;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by chenshuai on 2016/10/19.
 */

public class CustomGridLayoutManager extends StaggeredGridLayoutManager {
    private boolean isScrollEnabled = true;

    public CustomGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CustomGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}