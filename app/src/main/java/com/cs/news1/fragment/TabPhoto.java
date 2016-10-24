package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Bean;
import com.cs.news1.fragment.fm_adapter.PhotoAdater.PhotoAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabPhoto extends BaseFragment {

    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private List<Bean.ResultsBean> mlist=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_photo, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_photo);
        //CustomGridLayoutManager customGridLayoutManager=new CustomGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        final String url = "http://gank.io/api/data/福利/100/1";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("TAT", response);
                        Gson gson = new Gson();
                        Bean bean = gson.fromJson(response, Bean.class);
                        mlist.addAll(bean.getResults());
                        mPhotoAdapter.notifyDataSetChanged();
                    }
                });


        mPhotoAdapter = new PhotoAdapter(mlist, getActivity());
        mRecyclerView.setAdapter(mPhotoAdapter);
//mRecyclerView.setLayoutFrozen(true);//禁止滑动呢
//mRecyclerView.setNestedScrollingEnabled(false);
        return view;

        }
    }


