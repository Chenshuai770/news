package com.cs.news1.act_adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cs.news1.base.BaseFragment;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mList_fm;
    private List<String> mList_title;

    public MyAdapter(FragmentManager fm, List<BaseFragment> mList_fm,List<String> mList_title) {
        super(fm);
        this.mList_fm=mList_fm;
        this.mList_title=mList_title;
    }

    @Override
    public Fragment getItem(int position) {
        return mList_fm.get(position);
    }

    @Override
    public int getCount() {
        return mList_fm.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList_title.get(position);
    }
}
