package com.cs.news1.fragment;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.cs.news1.R;
import com.cs.news1.application.MyApplication;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.VideoListBean;
import com.cs.news1.entry.Videos;
import com.cs.news1.fragment.videoAdapter.MyVideos;
import com.cs.news1.fragment.videoAdapter.VideoAdapter;
import com.cs.news1.uri.Uri;
import com.superplayer.library.SuperPlayer;
import com.superplayer.library.SuperPlayerManage;
import com.superplayer.library.mediaplayer.IjkVideoView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabVideo extends BaseFragment {
    private RecyclerView mRlVideo;
    private VideoAdapter adapter;
    private List<Videos.V9LG4CHORBean> mList=new ArrayList<>();
    private SuperPlayer player;
    private RelativeLayout fullScreen;
    private int postion = -1;
    private int lastPostion = -1;
    private LinearLayoutManager mLayoutManager;
    private List<VideoListBean> dataList = new ArrayList<>();
    protected boolean isCreated = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_video, container, false);
        initPlayer();
        setData();
       //initData();
        initView(view);
        initAdapter();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated=true;
        SharedPreferences first = getContext().getSharedPreferences("first", MODE_PRIVATE);
        SharedPreferences.Editor edit = first.edit();
        edit.putBoolean("isfirst",true);
        edit.commit();
    }


    private List<VideoListBean> setData() {
            dataList.clear();
            VideoListBean bean1 = new VideoListBean();
            bean1.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9502&editionType=normal");
            dataList.add(bean1);
            VideoListBean bean2 = new VideoListBean();
            bean2.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9508&editionType=normal");
            dataList.add(bean2);
            VideoListBean bean3 = new VideoListBean();
            bean3.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=8438&editionType=normal");
            dataList.add(bean3);
            VideoListBean bean4 = new VideoListBean();
            bean4.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=8340&editionType=normal");
            dataList.add(bean4);
            VideoListBean bean5 = new VideoListBean();
            bean5.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9392&editionType=normal");
            dataList.add(bean5);
            VideoListBean bean6 = new VideoListBean();
            bean6.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=7524&editionType=normal");
            dataList.add(bean6);
            VideoListBean bean7 = new VideoListBean();
            bean7.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9444&editionType=normal");
            dataList.add(bean7);
            VideoListBean bean8 = new VideoListBean();
            bean8.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9442&editionType=normal");
            dataList.add(bean8);
            VideoListBean bean9 = new VideoListBean();
            bean9.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=8530&editionType=normal");
            dataList.add(bean9);
            VideoListBean bean10 = new VideoListBean();
            bean10.setVideoUrl("http://baobab.wandoujia.com/api/v1/playUrl?vid=9418&editionType=normal");
            dataList.add(bean10);
            return dataList;

    }

    private void initAdapter() {
        adapter = new VideoAdapter(getContext(), dataList);
        mRlVideo.setAdapter(adapter);
        adapter.setPlayClick(new VideoAdapter.onPlayClick() {
            @Override
            public void onPlayclick(int position, RelativeLayout image) {
                image.setVisibility(View.GONE);
                if (player.isPlaying() && lastPostion == position){
                    return;
                }
                postion = position;
                if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                    if (position != lastPostion) {
                        player.stopPlayVideo();
                        player.release();
                    }
                }
                if (lastPostion != -1) {
                    player.showView(R.id.adapter_player_control);
                }
                //找到播放器
                View view = mRlVideo.findViewHolderForAdapterPosition(position).itemView;
                FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
                frameLayout.removeAllViews();
                player.showView(R.id.adapter_player_control);
                frameLayout.addView(player);
                //播放视频的逻辑处理在这里
                player.play(dataList.get(position).getVideoUrl());
                lastPostion = position;

            }
        });
        /**
         * 播放完设置还原播放界面
         */
        player.onComplete(new Runnable() {
            @Override
            public void run() {
                ViewGroup last = (ViewGroup) player.getParent();//找到videoitemview的父类，然后remove
                if (last != null && last.getChildCount() > 0) {
                    last.removeAllViews();
                    View itemView = (View) last.getParent();
                    if (itemView != null) {
                        itemView.findViewById(R.id.adapter_player_control).setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        /***
         * 监听列表的下拉滑动
         */
        mRlVideo.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                //获取当前view的位置
                int index = mRlVideo.getChildAdapterPosition(view);
                View controlview = view.findViewById(R.id.adapter_player_control);
                if (controlview == null) {
                    return;
                }
                view.findViewById(R.id.adapter_player_control).setVisibility(View.VISIBLE);
                if (index == postion) {
                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
                    frameLayout.removeAllViews();
                    if (player != null &&
                            ((player.isPlaying()) || player.getVideoStatus() == IjkVideoView.STATE_PAUSED)) {
                        view.findViewById(R.id.adapter_player_control).setVisibility(View.GONE);
                    }
                    if (player.getVideoStatus() == IjkVideoView.STATE_PAUSED) {
                        if (player.getParent() != null)
                            ((ViewGroup) player.getParent()).removeAllViews();
                        frameLayout.addView(player);
                        return;
                    }
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                int index = mRlVideo.getChildAdapterPosition(view);
                if ((index) == postion) {
                    if (true) {
                        if (player != null) {
                            player.stop();
                            player.release();
                            player.showView(R.id.adapter_player_control);
                        }
                    }
                }

            }
        });

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                fullScreen.setVisibility(View.GONE);
                fullScreen.removeAllViews();
                mRlVideo.setVisibility(View.VISIBLE);
                if (postion <= mLayoutManager.findLastVisibleItemPosition()
                        && postion >= mLayoutManager.findFirstVisibleItemPosition()) {
                    View view = mRlVideo.findViewHolderForAdapterPosition(postion).itemView;
                    FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.adapter_super_video);
                    frameLayout.removeAllViews();
                    ViewGroup last = (ViewGroup) player.getParent();//找到videoitemview的父类，然后remove
                    if (last != null) {
                        last.removeAllViews();
                    }
                    frameLayout.addView(player);
                }
                int mShowFlags =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                fullScreen.setSystemUiVisibility(mShowFlags);
            } else {
                ViewGroup viewGroup = (ViewGroup) player.getParent();
                if (viewGroup == null)
                    return;
                viewGroup.removeAllViews();
                fullScreen.addView(player);
                fullScreen.setVisibility(View.VISIBLE);
                int mHideFlags =
                        View.SYSTEM_UI_FLAG_LOW_PROFILE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        ;
                fullScreen.setSystemUiVisibility(mHideFlags);
            }
        } else {
            fullScreen.setVisibility(View.GONE);
        }
    }

    private void initPlayer() {
        player = SuperPlayerManage.getSuperManage().initialize(getContext());
        player.setShowTopControl(false).setSupportGesture(false);
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Uri.VIDEOLIST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyApplication.defalutOkHttpClient())
                .build();
        final MyVideos myVideos = retrofit.create(MyVideos.class);
        Observable<Videos> observable = myVideos.getdata();
        observable.subscribe(new Subscriber<Videos>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Videos videos) {
                Log.d("GGG",videos+"");
                mList.addAll(videos.getV9LG4CHOR());
                Log.d("XXX",mList.size()+"");
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initView(View view) {
        mRlVideo = (RecyclerView) view.findViewById(R.id.rl_video);
        fullScreen = (RelativeLayout)view.findViewById(R.id.full_screen);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRlVideo.setLayoutManager(mLayoutManager);

    }
    /**
     * 下面的这几个Activity的生命状态很重要
     */
    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player != null && player.onBackPressed()) {
            return;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.stop();
    }

}
