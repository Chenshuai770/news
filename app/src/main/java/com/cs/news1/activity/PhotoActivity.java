package com.cs.news1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cs.news1.R;
import com.cs.news1.entry.Photos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ImageButton mIvActPhotodown;
    private int downPosition=-1;


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
        mIvActPhotodown = (ImageButton) findViewById(R.id.iv_act_photodown);

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
        mTvActPhotocount.setText(pos + 1 + "/" + mList.size());
        mVpActPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTvActPhotocount.setText(mVpActPhoto.getCurrentItem() + 1 + "/" + mList.size());
                downPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(PhotoActivity.this, "图片已下载", Toast.LENGTH_SHORT).show();
            }
        };


        mIvActPhotodown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("TAG",pos+"");
                        if (downPosition==-1) {
                            Log.d("TAG",pos+"");
                            saveImageToSdCard(mList.get(pos).getUrl());
                            handler.sendEmptyMessage(001);

                            //下载图片pos
                            //Toast.makeText(PhotoActivity.this, pos+"", Toast.LENGTH_SHORT).show();
                        }else {
                            saveImageToSdCard(mList.get(downPosition).getUrl());
                            handler.sendEmptyMessage(002);
                            //Toast.makeText(PhotoActivity.this, downPosition+"", Toast.LENGTH_SHORT).show();
                            //downPosition

                        }
                    }
                }).start();
            }
        });
    }
    /**
     * 判断手机是否安装SDCard
     *
     * @return
     */
    private static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    /**
     * 获取手机存储根目录
     *
     * @return
     */
    public static String getStoreRootPath() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            return Environment.getDataDirectory().getAbsolutePath();
        }
    }

    private void saveImageToSdCard(String url) {
        try {
            // File.separator是可以跨平台输出的
            File file = new File(getStoreRootPath() + File.separator + "ABC");
            //创建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            //显示系统时间
            long time = System.nanoTime();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format1 = format.format(date);
            //创建输出目录
            File files=new File(file.getAbsoluteFile()+ File.separator+format1+".jpg");
            //HttpURLConnection文件下载
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setConnectTimeout(5*1000);
            conn.setRequestMethod("GET");
            conn.connect();
            //将流进行详细的转换
            InputStream inputStream = conn.getInputStream();
            byte[] buffer = new byte[1024];
            FileOutputStream fileOutputStream = new FileOutputStream(files);
            int len;
            //将输入流转变成输出流（如果是文件就直接输出了）
            while ((len=inputStream.read(buffer))!=-1){
                fileOutputStream.write(buffer,0,len);
            }
            fileOutputStream.close();
            inputStream.close();

        } catch (MalformedURLException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
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
