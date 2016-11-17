package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Recreation;
import com.cs.news1.fragment.fm_adapter.JokeAdater.ApiJoke;
import com.cs.news1.fragment.fm_adapter.JokeAdater.JokeAdpter;
import com.cs.news1.fragment.fm_adapter.JokeAdater.MyViewpageAdapter;
import com.cs.news1.views.EndLessOnScrollListener;
import com.cs.news1.views.MyDecoration;
import com.cs.news1.views.NetUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
    private ViewPager viewPager;
    // 默认轮播时间
    private int delay = 3000;
    MyViewpageAdapter myViewpageAdapter;
    // 轮播当前位置
    private int mCurrentPosition = 0;
    private LinearLayout ponit;
    private String[] Imgs = {
            "http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg",
            "http://ww3.sinaimg.cn/large/610dc034jw1f9nuk0nvrdj20u011haci.jpg",
            "http://ww3.sinaimg.cn/large/610dc034jw1f9rc3qcfm1j20u011hmyk.jpg",
            "http://ww3.sinaimg.cn/large/610dc034jw1f9em0sj3yvj20u00w4acj.jpg"
    };
    private int size;
    private List<View> mViews = new ArrayList<>();
    private ImageView imageView;
    private View view;
    private Subscription subscribe;
    private ImageView[] mIndicators;
    private int ponitpos;
    private View shitu;
    private View header;


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

        header = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_header, view, false);
        viewPager= (ViewPager) header.findViewById(R.id.item_joke_banner);
        ponit = (LinearLayout) header.findViewById(R.id.ll_ponit);
        myViewpageAdapter=new MyViewpageAdapter(getContext(), mViews, size);
        viewPager.setAdapter(myViewpageAdapter);
        //载入数据

        //清空数据
        mViews.clear();
        //载入图片的时候需要用的item,默认用网络图片进行加载
       // 现在的处理方式:新图3、图1、图2、图3、新图1。
        shitu = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_shitu, null);
        imageView = (ImageView) shitu.findViewById(R.id.item_joke_shitu);
        Glide.with(this)
                .load(Imgs[Imgs.length - 1])
                .centerCrop()
                .into(imageView);
        mViews.add(shitu);
        for (int i = 0; i < Imgs.length; i++) {
            shitu = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_shitu, null);
            imageView = (ImageView) shitu.findViewById(R.id.item_joke_shitu);
            Glide.with(this)
                    .load(Imgs[i])
                    .centerCrop()
                    .into(imageView);
            mViews.add(shitu);
        }

        shitu = LayoutInflater.from(getContext()).inflate(R.layout.item_joke_shitu, null);
        imageView = (ImageView) shitu.findViewById(R.id.item_joke_shitu);
        Glide.with(this)
                .load(Imgs[0])
                .centerCrop()
                .into(imageView);
        mViews.add(shitu);
        //通知适配器做变化
        myViewpageAdapter.notifyDataSetChanged();
        //加入小圆点

        size = Imgs.length;
        mIndicators = new ImageView[size];
        for (int i = 0; i < size; i++) {
            mIndicators[i] = new ImageView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10,10);
            lp.setMargins(10, 0, 10, 0);
            mIndicators[i].setLayoutParams(lp);
            mIndicators[i].setBackgroundResource(R.drawable.selector_home_banner_points);
            ponit.addView(mIndicators[i]);

        }


        //进行监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int max = mViews.size() - 1;
                mCurrentPosition = position;
                if (position == 0) {
                    mCurrentPosition = max - 1;
                } else if (position == max) {
                    mCurrentPosition = 1;
                }
                ponitpos = mCurrentPosition - 1;
                int c = viewPager.getCurrentItem();
                Log.e("c",c+"");
                for (int i = 0; i <mViews.size(); i++) {
                    if(c==0||c==mViews.size()-1){
                        break;
                    }else{
                        c = c-1;
                        break;
                    }
                }
                /*switch (c){
                    case 0:
                        break;
                    case 1:
                        c =0;
                        break;
                    case 2:
                        c = 1;
                        break;
                    case 3:
                        c =2;
                        break;
                    case 4:
                        break;
                }*/
                Log.e("c",c+"");

                // TODO: 2016/11/17  这里实现的方式有两种,从不同思路去实现,有兴趣可以参考以上代码,新手会遇到坑
                for (int i = 0; i < mIndicators.length; i++) {
                    //mIndicators[i].setSelected(c==i);
                    mIndicators[i].setSelected(ponitpos==i);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    viewPager.setCurrentItem(mCurrentPosition, false);
                }

            }
        });

        //调用rxjava来执行
        subscribe = Observable.interval(delay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mCurrentPosition++;
                        if (viewPager != null)
                            viewPager.setCurrentItem(mCurrentPosition, false);
                    }
                });
        viewPager.setCurrentItem(1, false);
        // 初始化指示器图片集合
        myViewpageAdapter.setMyViewListenner(new MyViewpageAdapter.MyViewListenner() {
            @Override
            public void onItemClick(int possiton) {
                Toast.makeText(getContext(), "你点击了viewpager的第" + possiton + "个", Toast.LENGTH_SHORT).show();
            }
        });
        //缺少手指按下,onTouch方法不管用
        adapter.setHeaderView(header);

    }





}
