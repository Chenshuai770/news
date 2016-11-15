package com.cs.news1.act_adpter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by chenshuai on 2016/11/14.
 */

public class VpAdapter extends PagerAdapter {
    private Context context;
    private List<View> mViews;

    public VpAdapter(Context context, List<View> mViews) {
        this.context = context;
        this.mViews = mViews;
    }
    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override//加入一个视图
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override//销毁一个视图
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }


    @Override//将视图和view联系起来
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
