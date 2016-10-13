package com.cs.news1.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class NoScllorViewPager extends ViewPager {
    public NoScllorViewPager(Context context) {
        super(context);
    }

    public NoScllorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
