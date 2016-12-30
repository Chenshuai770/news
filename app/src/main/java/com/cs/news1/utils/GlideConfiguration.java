package com.cs.news1.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.cs.news1.application.MyApplication;

import java.io.File;
import java.io.InputStream;

/**
 * Created by user on 2016/8/8.
 */

public class GlideConfiguration implements GlideModule {


    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //这里也能使用SD卡存储,换成相应的路径即可
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, getFileString(), 10 * 1024 * 1024));
        //builder.setDiskCache(new InternalCacheDiskCacheFactory(context, getFileString(), 10 * 1024 * 1024));
        builder.setMemoryCache(new LruResourceCache(getUseAbleSize()));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(MyApplication.defalutOkHttpClient());
        glide.register(GlideUrl.class, InputStream.class, factory);

    }

    private String getFileString() {
        // File myCache = new File(application.getApplicationContext().getExternalFilesDir("news"), "myCache");

       // File dirFile = new File(mContext.getCacheDir().getAbsolutePath().toString() + "GlideBitampCache");
      //  File dirFile = new File( MyApplication.getInstance().getApplicationContext().getExternalFilesDir("image") + "ImageCache");
        File dirFile = new File("ImageCache");

        File tempFile = new File(dirFile, "bitmaps");
        if (!tempFile.getParentFile().exists()) {
            tempFile.getParentFile().mkdirs();
        }
        return tempFile.getAbsolutePath().toString();
    }

    private int getUseAbleSize() {
        int result = (int) (Runtime.getRuntime().maxMemory() / 8);
        return result;
    }
}

