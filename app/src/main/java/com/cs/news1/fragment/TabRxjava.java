package com.cs.news1.fragment;

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
import com.cs.news1.entry.Food;
import com.cs.news1.fragment.fm_adapter.FoodAdapter.ApiFood;
import com.cs.news1.fragment.fm_adapter.FoodAdapter.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabRxjava extends BaseFragment{
    private static final String TAG="TAG";
    private RecyclerView mRecyclerView;
    private List<Food.TngouBean> mData=new ArrayList<>();
    private FoodAdapter madapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fm_food,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rl_food);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        madapter=new FoodAdapter(getContext(),mData);
        mRecyclerView.setAdapter(madapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.tngou.net")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiFood apiFood = retrofit.create(ApiFood.class);
        rx.Observable<Food> observable = apiFood.getData(2,10,1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Food>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Food food) {
                        Log.d(TAG,food+"");
                        mData.addAll(food.getTngou());
                        Log.d(TAG,mData+"");
                        madapter.notifyDataSetChanged();
                    }
                });
        return view;
    }


}
