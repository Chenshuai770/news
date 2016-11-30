package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.application.MyApplication;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Photos;
import com.cs.news1.fragment.photosAdapter.MyPhotos;
import com.cs.news1.fragment.photosAdapter.PhotosAdapter;
import com.cs.news1.uri.Uri;
import com.cs.news1.utils.NetUtils;
import com.cs.news1.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabPhoto extends BaseFragment {
    private RecyclerView mRlPhoto;
    private SwipeRefreshLayout mSrPhoto;
    private List<Photos.ResultsBean> mList=new ArrayList<>();
    private PhotosAdapter mAdapter;
    private int count=40;
    private int page=1;
    private boolean refresh=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_photo, container, false);
        initView(view);
        initDatas(page);
        initRefresh();
        initNetWork();
        return view;
    }

    private void initNetWork() {
        if (!NetUtils.isConnected(getContext())) {
            refresh=false;
            mSrPhoto.setRefreshing(refresh);
        }
    }

    private void initRefresh() {
        if (!mSrPhoto.isRefreshing()) {
            refresh=true;
            mSrPhoto.setRefreshing(refresh);
            mSrPhoto.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //这里不清空会出现问题
                    mList.clear();
                    if (page==11) {
                        page=1;
                    }
                    page++;
                    initDatas(page);

                }
            });


        }
    }

    private void initDatas(int page) {
        this.page=page;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Uri.PHOTOURI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyApplication.defalutOkHttpClient())
                .build();
        MyPhotos myPhotos = retrofit.create(MyPhotos.class);
        Observable<Photos> observable = myPhotos.getDatas(count, page);
        observable.subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Subscriber<Photos>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {
                       Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
                       mSrPhoto.setRefreshing(refresh);

                   }

                   @Override
                   public void onNext(Photos photos) {
                       mList.addAll(photos.getResults());
                       mAdapter.notifyDataSetChanged();
                       refresh=false;
                       mSrPhoto.setRefreshing(refresh);

                   }
               });


    }

    private void initView(View view) {
        mSrPhoto = (SwipeRefreshLayout) view.findViewById(R.id.sr_photo);
        mSrPhoto.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSrPhoto.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        mRlPhoto = (RecyclerView) view.findViewById(R.id.rl_photo);
        mRlPhoto.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mRlPhoto.addItemDecoration(new SpacesItemDecoration(2));
        mAdapter=new PhotosAdapter(getContext(),mList);
        View footer=LayoutInflater.from(getContext()).inflate(R.layout.item_photo_footer,mRlPhoto,false);
        mAdapter.setmFooterView(footer);

        mRlPhoto.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new PhotosAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "你点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
       // mRlPhoto.setAdapter();

    }
}
