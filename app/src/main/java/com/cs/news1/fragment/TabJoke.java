package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.fm_adapter.JokeAdater.JokeAdpter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabJoke extends BaseFragment {
    private View mRootview;
    private RecyclerView mRecyclerView;
    private List<String > banner_url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = getActivity().getLayoutInflater().inflate(R.layout.fm_joke, (ViewGroup) getActivity().findViewById(R.id.viewpager), false);//中间这个参数是试图组，再哪个里面的Activity
        mRecyclerView = (RecyclerView) mRootview.findViewById(R.id.rl_joke);
        banner_url=new ArrayList<>();
        banner_url.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        banner_url.add("http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg");
        banner_url.add("http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg");
        banner_url.add("http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg");


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        JokeAdpter adpter=new JokeAdpter(getActivity(),banner_url);
        mRecyclerView.setAdapter(adpter);


        return mRootview;
    }
}
