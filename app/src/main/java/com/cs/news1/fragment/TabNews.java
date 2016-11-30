package com.cs.news1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.activity.WebActivity;
import com.cs.news1.application.MyApplication;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.News;
import com.cs.news1.fragment.fm_adpters.MyNews;
import com.cs.news1.fragment.fm_adpters.NewsAdapter;
import com.cs.news1.uri.Uri;
import com.cs.news1.utils.NetUtils;
import com.cs.news1.viwes.MyDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TabNews extends BaseFragment {
    private SwipeRefreshLayout mRefresh;
    private RecyclerView mRecyclerView;
    private NewsAdapter mAdapter;
    private List<News.ResultBean.DataBean> mList=new ArrayList<>();
    private String [] types={"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    private int lastpage=types.length-1;
    private int firstpage=0;
    private String key="ce18f1786cf2e609acbc076a4b6a2df5";

    private boolean refresh=false;
    private static final String FILE_NAME = "share";
    private Map<Integer,Boolean> map=new HashMap<>();





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fm_news, container, false);

        initView(rootview);
        initData(firstpage);
        initRefresh();
        initNetWork();
        return rootview;
    }

    private void initNetWork() {
        if (!NetUtils.isConnected(getContext())) {
            refresh=false;
            mRefresh.setRefreshing(refresh);
        }
    }

    private void initRefresh() {
        if (!mRefresh.isRefreshing()) {
            refresh=true;
            mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mList.clear();
                    if (firstpage == lastpage) {
                        firstpage=0;
                    }
                    firstpage++;
                    initData(firstpage);

                }
            });

        }


    }

    private void initData(final int page) {
            this.firstpage=page;
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Uri.NEWSURI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(MyApplication.defalutOkHttpClient())
                    .build();
            MyNews myNews = retrofit.create(MyNews.class);
            Map<String , String> options = new HashMap<>();
            options.put("type",types[page]);
            options.put("key",key);
            Observable<News> observable = myNews.getDates(options);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<News>() {
                        @Override
                        public void onCompleted() {

                        }
                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
                            mRefresh.setRefreshing(refresh);
                        }
                        @Override
                        public void onNext(News news) {

                            mList.addAll(news.getResult().getData());
                            mAdapter.notifyDataSetChanged();
                            //CacheUtils.saveLocal(String.valueOf(news.getResult().getData()),"News");
                            refresh=false;
                            mRefresh.setRefreshing(refresh);

                            mAdapter.setOnItemClickLitener(new NewsAdapter.OnItemClickLitener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                   // SPUtils.put(getContext(),"newspos",position);
                                    Intent intent = new Intent(getContext(), WebActivity.class);
                                    Bundle bundle = new Bundle();

                                    //序列化操作
                                    bundle.putParcelableArrayList("myNewsUrl", (ArrayList<? extends Parcelable>) mList);
                                    bundle.putInt("pos",position);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                    });



    }

    private void initView(View rootview) {

        mRefresh = (SwipeRefreshLayout) rootview.findViewById(R.id.sr_news);
        mRefresh.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mRefresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.rl_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));
        mAdapter=new NewsAdapter(getContext(),mList);
        mRecyclerView.setAdapter(mAdapter);



    }


}
