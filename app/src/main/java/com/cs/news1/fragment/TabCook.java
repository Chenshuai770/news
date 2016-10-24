package com.cs.news1.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.News;
import com.cs.news1.fragment.fm_adapter.CookAdapter.ApiCook;
import com.cs.news1.fragment.fm_adapter.CookAdapter.CookAdater;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabCook extends BaseFragment{
    private static final String TAG="TAG";
    private RecyclerView mRecyclerView;
    private List<News.TngouBean> mData=new ArrayList<>();

    private CookAdater madapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_cook,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rl_life);

        //创建retrofit的实例,传递参数
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //retrofit与api接口关联,创建
        ApiCook apiCook = retrofit.create(ApiCook.class);
        //请求具体数据
        Call<News> call = apiCook.getDate(1, 20, 1);
        //异步任务提交数据
        call.enqueue(new Callback<News>() {

            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                mData.addAll(response.body().getTngou());
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        madapter=new CookAdater(getContext(),mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(madapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override//绘制之前可以自定义颜色
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
               // c.drawColor(Color.GRAY);
            }

            @Override//绘制前景
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
            }

            @Override//绘制行间距
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,3,0,0);
            }
        });
      /*  madapter.setOnChildClickListenner(new CookAdater.OnChildClickListenner() {
            @Override
            public void onChildClick(RecyclerView parent, View view, int position, List<News.TngouBean> list) {
                Toast.makeText(getContext(), "你点击了第"+position+"个", Toast.LENGTH_SHORT).show();

            }
        });*/
        return view;
    }

}
