package com.cs.news1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cs.news1.R;
import com.cs.news1.act_adpter.VpAdapter;
import com.cs.news1.entry.Photo;
import com.cs.news1.views.L;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chenshuai on 2016/11/15.
 */

public class PhotoActivity extends Activity {
    private ViewPager viewPager;
    private VpAdapter adapter;
    private List<View> viewList=new ArrayList<>();
    private List<Photo.ResultsBean> mlist=new ArrayList<>();
    private List<String > myUrls=new ArrayList<>();
    private int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//无状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_photo);
        initView();
    }
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.vp_photo);
      /*  Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        myUrls= (List<String>) bundle.get("myurl");*/

      /*  Intent intent = getIntent();
    //  List<Photo.ResultsBean> mlist=new ArrayList<>();
        mlist= (List<Photo.ResultsBean>) intent.getSerializableExtra("myurl");*/
        //postion= (int) bundle.get("pos");
        Intent intent = getIntent();
        mlist=intent.getParcelableArrayListExtra("myurl");

        L.d("TTT",mlist.size()+"");
        for (int i = 0; i < mlist.size(); i++) {
            String url = mlist.get(i).getUrl();
            View view = LayoutInflater.from(this).inflate(R.layout.photoview, null);
            PhotoView photoView= (PhotoView) view.findViewById(R.id.pv_photo);
            final ProgressBar progressBar= (ProgressBar) view.findViewById(R.id.progress);
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
            viewList.add(view);
        }

        adapter=new VpAdapter(this,viewList);
        viewPager.setAdapter(adapter);
        //viewPager.setCurrentItem(postion);
    }
}
