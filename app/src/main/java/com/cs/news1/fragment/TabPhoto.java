package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.fm_adapter.PhotoAdapter;

import java.util.ArrayList;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabPhoto extends BaseFragment{
    private ImageView imageView;
    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private ArrayList<String > mlist=new ArrayList<String >();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_photo,null);
      /*  imageView= (ImageView) view.findViewById(R.id.iv_photo);
        imageView.setImageResource(R.mipmap.a);*/
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_photo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPhotoAdapter=new PhotoAdapter(mlist,getActivity());
        mRecyclerView.setAdapter(mPhotoAdapter);




        return view;
    }
}
