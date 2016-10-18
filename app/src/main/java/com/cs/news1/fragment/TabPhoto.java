package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.fm_adapter.PhotoAdater.PhotoAdapter;

import java.util.ArrayList;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabPhoto extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private ArrayList<String> mlist = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_photo, null);
      /*  imageView= (ImageView) view.findViewById(R.id.iv_photo);
        imageView.setImageResource(R.mipmap.a);*/
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_photo);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        mlist.add("http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg");
        mlist.add("http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        mlist.add("http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg");
        mlist.add("http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        mlist.add("http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg");
        mlist.add("http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8uxlbptw7j20ku0q1did.jpg");
        mlist.add("http://ww1.sinaimg.cn/large/610dc034jw1f8rgvvm5htj20u00u0q8s.jpg");
        mlist.add("http://ww3.sinaimg.cn/large/610dc034jw1f8qd9a4fx7j20u011hq78.jpg");
        mlist.add("http://ww2.sinaimg.cn/large/610dc034jw1f8o2ov8xi0j20u00u0q61.jpg");


        mPhotoAdapter = new PhotoAdapter(mlist, getActivity());
        mRecyclerView.setAdapter(mPhotoAdapter);
        return view;
    }
}
