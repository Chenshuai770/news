package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabJoke extends BaseFragment{
    private View mRootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview=inflater.inflate(R.layout.fm_joke, (ViewGroup) getActivity().findViewById(R.id.viewpager),false);//中间这个参数是试图组，再哪个里面的Activity
        return mRootview;
}}
