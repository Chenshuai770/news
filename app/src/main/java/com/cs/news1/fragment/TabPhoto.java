package com.cs.news1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.activity.PhotoActivity;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Photo;
import com.cs.news1.fragment.fm_adapter.PhotoAdater.PhotoAdapter;
import com.cs.news1.utils.SpacesItemDecoration;
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
    private List<Photo.ResultsBean> mlist=new ArrayList<>();
    private List<String > myUrls=new ArrayList<String>();


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_photo, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_photo);
        //CustomGridLayoutManager customGridLayoutManager=new CustomGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(4));
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
                        Photo bean = gson.fromJson(response, Photo.class);
                        mlist.addAll(bean.getResults());
                        for (int i = 0; i <mlist.size() ; i++) {
                            myUrls.add(mlist.get(i).getUrl());
                        }
                        mPhotoAdapter.notifyDataSetChanged();
                    }
                });



        mPhotoAdapter = new PhotoAdapter(mlist, getActivity());
        mRecyclerView.setAdapter(mPhotoAdapter);
//mRecyclerView.setLayoutFrozen(true);//禁止滑动呢
//mRecyclerView.setNestedScrollingEnabled(false);
        mPhotoAdapter.setOnItemClickLitener(new PhotoAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
               //Log.d("BBB",myUrls.size()+"");
             /*   Intent intent=new Intent(getContext(), PhotoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("myurl", (ArrayList<String>) myUrls);
                bundle.putInt("pos",position);
                intent.putExtras(bundle);*/
             /*   Intent intent=new Intent(getContext(), PhotoActivity.class);
                Bundle bundle=new Bundle();
                //List<Photo.ResultsBean> mlist=new ArrayList<>(); mlist的类型
                bundle.putSerializable("myurl", (Serializable) mlist);
                intent.putExtras(bundle);
                startActivity(intent);*/
                Intent intent=new Intent(getContext(), PhotoActivity.class);
                Bundle bundle=new Bundle();
                //List<Photo.ResultsBean> mlist=new ArrayList<>(); mlist的类型
                bundle.putParcelableArrayList("myurl", (ArrayList<? extends Parcelable>) mlist);
                bundle.putInt("pos",position);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
        return view;

        }
    }


