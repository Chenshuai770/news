package com.cs.news1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cs.news1.R;
import com.cs.news1.entry.Photos;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chenshuai on 2016/11/30.
 */

public class PhotoActivity extends Activity {
    private ViewPager mVpActPhoto;
    private List<Photos.ResultsBean> mList = new ArrayList<>();
    private List<View> mViews = new ArrayList<>();
    private int pos;
    private PhotoViewAdapter mAdapter;
    private TextView mTvActPhotocount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//无状态栏
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();

    }

    private void initView() {
        mVpActPhoto = (ViewPager) findViewById(R.id.vp_act_photo);
        mTvActPhotocount = (TextView) findViewById(R.id.tv_act_photocount);

        Intent intent = getIntent();
        // TODO: 2016/11/30   mList= (List<Photos.ResultsBean>) intent.getExtras().get("photoList");这两种写法是一样的

        mList = intent.getParcelableArrayListExtra("photoList");
        pos = (int) intent.getExtras().get("photoPos");

        for (int i = 0; i < mList.size(); i++) {
            String url = mList.get(i).getUrl();
            View view = LayoutInflater.from(this).inflate(R.layout.item_photoview, null);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.pv_photo);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress);
            Glide.with(this)
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .crossFade()
                    .into(photoView);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                }

                @Override
                public void onOutsidePhotoTap() {

                }
            });
            mViews.add(view);
        }
        mAdapter = new PhotoViewAdapter();
        mVpActPhoto.setAdapter(mAdapter);
        mVpActPhoto.setCurrentItem(pos);
        mTvActPhotocount.setText(pos+1+"/"+mList.size());
        mVpActPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvActPhotocount.setText(mVpActPhoto.getCurrentItem()+1+"/"+mList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       // mTvActPhotocount.setText(mVpActPhoto.getCurrentItem()+"/"+mList.size());




    }

    class PhotoViewAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override//加入一个视图
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
