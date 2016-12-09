package com.cs.news1.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Keshi;
import com.cs.news1.fragment.fm_adapter.NewsAdapter.NewsAdapter;
import com.cs.news1.fragment.fm_adapter.NewsAdapter.NewsFirst;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

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

public class TabNews extends BaseFragment {
    private SwipeRefreshLayout mSrfLayout;
    private ExpandableListView mListView;
    private RollPagerView mRollPagerView;
    private View rootview;
    private NewsAdapter mNewsAdapter;
    private List<Keshi.TngouBean> mNewsTitle=new ArrayList<>();
    private boolean isRefresh=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootview =  inflater.inflate(R.layout.fm_news, container, false);
       /* mSrLayout= (SwipeRefreshLayout) view.findViewById(R.id.sr_news);
        mListView= (ListView) view.findViewById(R.id.iv_news);*/
        initView();
        initData();

        return rootview;
    }

    private void initView() {
        mRollPagerView = (RollPagerView) rootview.findViewById(R.id.rollview);
        mRollPagerView.setPlayDelay(2000);

        mListView= (ExpandableListView) rootview.findViewById(R.id.iv_news);
        mNewsAdapter=new NewsAdapter(getContext(),mNewsTitle);
        mListView.setAdapter(mNewsAdapter);
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(getContext(), "你点了"+(i1+1)+"个位置", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        mSrfLayout= (SwipeRefreshLayout) rootview.findViewById(R.id.swpLayout);
        mSrfLayout.setColorSchemeResources(R.color.red,R.color.blue,R.color.orgine,R.color.green);
        mSrfLayout.setProgressViewOffset(true,20,100);
        mSrfLayout.setSize(SwipeRefreshLayout.DEFAULT);
        mSrfLayout.setProgressBackgroundColorSchemeResource(R.color.red);

        mSrfLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefresh){
                    isRefresh=true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //显示或隐藏刷新进度条

                           // mListView.setSelection(0);
                            mNewsTitle.clear();
                            initData();
                            mSrfLayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
                isRefresh=false;
            }
        });
        mRollPagerView.setAdapter(new TestLoopAdapter(mRollPagerView));
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(),"Item "+(position+1)+" clicked",Toast.LENGTH_SHORT).show();
            }
        });

        //mListView.setAdapter();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        final NewsFirst newsFirst = retrofit.create(NewsFirst.class);

        Observable<Keshi> observable = newsFirst.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Keshi>() {


                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Keshi keshi) {
                        mNewsTitle.addAll(keshi.getTngou());
                        mNewsAdapter.notifyDataSetChanged();
                        if (mNewsAdapter != null && mNewsAdapter != null) {
                            //展开view
                            for (int i = 0; i < mNewsTitle.size(); i++) {
                                mListView.expandGroup(i);
                            }
                        }
                    }
                });

    }

    }
    class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {

                R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d,R.mipmap.d,R.mipmap.e
        };

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }

}
