package com.cs.news1.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.fm_adapter.NewsAdapter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabNews extends BaseFragment {
    private SwipeRefreshLayout mSrfLayout;
    private ListView mListView;
    private RollPagerView mRollPagerView;
    private View rootview;
    private NewsAdapter mNewsAdapter;
    private List<String> mNewsTitle;
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

        mListView= (ListView) rootview.findViewById(R.id.iv_news);
        mNewsTitle=new ArrayList<>();
        mNewsTitle.add("项目一");
        mNewsTitle.add("项目二");
        mNewsTitle.add("项目三");
        mNewsTitle.add("项目四");
        mNewsTitle.add("项目五");

        mNewsAdapter=new NewsAdapter(getActivity(),mNewsTitle);
        mListView.setAdapter(mNewsAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "你点击了"+(i+1)+"个it1em", Toast.LENGTH_SHORT).show();
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

                            mNewsTitle.add("新项目");
                            mNewsAdapter.notifyDataSetChanged();
                           // mListView.setSelection(0);
                            mSrfLayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "已刷新", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }
                isRefresh=false;
            }
        });




        //mListView.setAdapter();
    }

    private void initData() {
        mRollPagerView.setAdapter(new TestLoopAdapter(mRollPagerView));
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(),"Item "+(position+1)+" clicked",Toast.LENGTH_SHORT).show();
            }
        });
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
}
