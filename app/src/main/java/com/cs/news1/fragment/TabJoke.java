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

import com.cs.news1.Http.JokeLoader;
import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Jokes;
import com.cs.news1.fragment.jokeAdpter.JokeAdpter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabJoke extends BaseFragment {
    private View mRootview;
    private RecyclerView mRlJoke;
    private SwipeRefreshLayout mSrJoke;
    private List<Jokes.ResultBean.DataBean> mList=new ArrayList<>();
    private JokeAdpter mAdapter;
    private Map<String, String> options=new HashMap<>();
    private int page=1;
    private boolean refresh=false;
    private String sort="desc";
    private int pagesize=20;
    //获取当前的时间
    private long nowtime = System.currentTimeMillis() / 1000;
    private String time= String.valueOf(nowtime);
    private String key="c5709f8afde74b8dfc94f9ae86a4f95c";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootview = inflater.inflate(R.layout.fm_joke, container, false);
        initView(mRootview);
        initData(page);
        initRefresh();

        return mRootview;
    }

    private void initRefresh() {
        if (!mSrJoke.isRefreshing()) {
            refresh=true;
            mSrJoke.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mList.clear();
                    if (page == 1000) {
                        page=1;
                    }
                    page++;
                    initData(page);
                }
            });

        }

    }

    private void initData(int page) {
        this.page=page;
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Uri.JOKESURI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyApplication.defalutOkHttpClient())
                .build();
        final MyJokes myJokes = retrofit.create(MyJokes.class);
           Observable<Jokes> observable = myJokes.getData(options);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Jokes>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
                        mSrJoke.setRefreshing(false);
                    }
                    @Override
                    public void onNext(Jokes jokes) {
                        mList.addAll(jokes.getResult().getData());
                        mAdapter.notifyDataSetChanged();
                        refresh=false;
                        mSrJoke.setRefreshing(refresh);
                    }
                });
*/
       // MyJokes myJokes= RetrofitServiceManager.getInstance().create(MyJokes.class);

        options.put("sort",sort);
        options.put("page",page+"");
        options.put("pagesize",pagesize+"");
        options.put("time", time);
        options.put("key",key);

       /* new JokeLoader(options).observable().subscribe(new Subscriber<Jokes>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Jokes jokes) {

            }
        });*/

        JokeLoader jokeLoader = new JokeLoader(options);
        jokeLoader.observable().subscribe(new Subscriber<Jokes>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
                mSrJoke.setRefreshing(false);
            }

            @Override
            public void onNext(Jokes jokes) {
                mList.addAll(jokes.getResult().getData());
                mAdapter.notifyDataSetChanged();
                refresh=false;
                mSrJoke.setRefreshing(refresh);
            }
        });


    }

    private void initView(View mRootview) {
        mSrJoke = (SwipeRefreshLayout) mRootview.findViewById(R.id.sr_joke);
        mSrJoke.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSrJoke.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRlJoke = (RecyclerView) mRootview.findViewById(R.id.rl_joke);
        mRlJoke.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //mRlJoke.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));
        mAdapter=new JokeAdpter(getContext(),mList);
        mRlJoke.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        page=1;
        super.onDestroy();
    }
}
