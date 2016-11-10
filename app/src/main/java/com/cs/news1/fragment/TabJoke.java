package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Recreation;
import com.cs.news1.fragment.fm_adapter.JokeAdater.ApiJoke;
import com.cs.news1.fragment.fm_adapter.JokeAdater.JokeAdpter;
import com.cs.news1.views.EndLessOnScrollListener;
import com.cs.news1.views.MyDecoration;
import com.cs.news1.views.NetUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
        * 刷新
        * setOnRefreshListener(OnRefreshListener):添加下拉刷新监听器

        *setRefreshing(boolean):显示或者隐藏刷新进度条

        *isRefreshing():检查是否处于刷新状态
        * RecyclerView
        * findFirstVisibleItemPosition()
        *findFirstCompletlyVisibleItemPosition()
        *findLastVisibleItemPosition()
        *findLastCompletlyVisibleItemPosition()
        */


public class TabJoke extends BaseFragment {
    private View mRootview;
    private RecyclerView recycle;
    private SwipeRefreshLayout refresh;
    private LinearLayoutManager layoutManager;
    private List<Recreation.TngouBean> data = new ArrayList<>();
    private JokeAdpter adapter;
    private static final String BASEURL="http://www.tngou.net";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = getActivity().getLayoutInflater().inflate(R.layout.fm_joke, (ViewGroup) getActivity().findViewById(R.id.viewpager), false);//中间这个参数是试图组，再哪个里面的Activity

        recycle = (RecyclerView) mRootview.findViewById(R.id.rl_joke);
        refresh= (SwipeRefreshLayout) mRootview.findViewById(R.id.srf_joke);

        refresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        layoutManager = new LinearLayoutManager(getContext());
        recycle.setLayoutManager(layoutManager);

        getData();
        //载入数据
        adapter=new JokeAdpter(getContext(),data);
        adapter.setOnItemClickLitener(new JokeAdpter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "你点击了第"+position+"个", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        recycle.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));
       /* MyAnimator animator = new MyAnimator();
        animator.setChangeDuration(2000);//自定义动画效果
        recycle .setItemAnimator(animator);*/
        recycle.setAdapter(adapter);
        //为RecyclerView添加HeaderView和FooterView
        setHeaderView(recycle);
        if (NetUtils.isConnected(getContext())) {
            setFooterView(recycle);
        }
        if (!NetUtils.isConnected(getContext())) {
           refresh.setVisibility(View.GONE);

        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASEURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                final ApiJoke apiJoke = retrofit.create(ApiJoke.class);
                Observable<Recreation> observable = apiJoke.getData(1, 1, 1);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Recreation>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Recreation recreation) {
                              // 生成1－26之间的随机数，包括26
                                Random rand = new Random();
                                int randNum = rand.nextInt(data.size()-2)+1;
                                data.add(0,recreation.getTngou().get(0
                                ));
                                adapter.notifyDataSetChanged();
                                refresh.setRefreshing(false);
                                Toast.makeText(getContext(), "已经刷新一条数据", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

        recycle.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                //异步载入数据,更新ｕｉ，可以用rxjava
                simulateLoadMoreData();
            }
        });
        return mRootview;
    }

    private void simulateLoadMoreData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final ApiJoke apiJoke = retrofit.create(ApiJoke.class);
        Observable<Recreation> observable = apiJoke.getData(2,5,1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Recreation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Recreation recreation) {
                        // 生成1－26之间的随机数，包括26
                        Random rand = new Random();
                        int randNum = rand.nextInt(data.size()-2)+1;
                        List<Recreation.TngouBean> mlist=new ArrayList<Recreation.TngouBean>();
                        for (int i = 0; i <recreation.getTngou().size() ; i++) {
                            mlist.addAll(recreation.getTngou());

                        }

                        mlist.addAll(data);
                        data.clear();
                        data.addAll(mlist);

                        adapter.notifyDataSetChanged();
                        refresh.setRefreshing(false);
                        Toast.makeText(getContext(), "已经刷新一条数据", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final ApiJoke apiJoke = retrofit.create(ApiJoke.class);
        Observable<Recreation> observable = apiJoke.getData(1, 10, 1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Recreation>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Recreation recreation) {
                        data.addAll(recreation.getTngou());
                        adapter.notifyDataSetChanged();

                    }
                });


    }
    private void setFooterView(RecyclerView view) {
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_footer, view, false);
        adapter.setFooterView(footer);

    }



    private void setHeaderView(RecyclerView view) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_header, view, false);
        Banner banner = (Banner) header.findViewById(R.id.item_joke_banner);
        adapter.setHeaderView(header);
    }


}
