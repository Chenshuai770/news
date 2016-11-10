package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.Web;
import com.cs.news1.fragment.fm_adapter.VideoAdater.BaseRetrofit;
import com.cs.news1.fragment.fm_adapter.VideoAdater.MnAPIService;
import com.cs.news1.fragment.fm_adapter.VideoAdater.VideoAdapter;
import com.cs.news1.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabVideo extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<Web.ResultBean.DataBean> mData = new ArrayList<>();
    private VideoAdapter mVideoAdapter;
    private Button btn_start;
    private Button btn_clear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_video, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rl_video);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mVideoAdapter = new VideoAdapter(getContext(), mData);
        mRecyclerView.setAdapter(mVideoAdapter);

        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_start = (Button) view.findViewById(R.id.btn_start);
        btn_clear = (Button) view.findViewById(R.id.btn_clear);

        btn_start.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                MnAPIService mnAPIService = BaseRetrofit.getMnAPIService();
                Call<Web> call = mnAPIService.getData("top", "ce18f1786cf2e609acbc076a4b6a2df5");
                call.enqueue(new Callback<Web>() {
                    @Override
                    public void onResponse(Call<Web> call, Response<Web> response) {
                        Web body = response.body();
                        if (response.isSuccessful()) {
                            if (body!=null) {
                                mData.addAll(body.getResult().getData());
                                mVideoAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Web> call, Throwable t) {
                    }
                });

                break;
            case R.id.btn_clear:
                new GlideUtils().clearCache(getContext());
                break;
        }
    }
}
