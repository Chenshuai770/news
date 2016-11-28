package com.cs.news1.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by user on 2016/8/8.
 */

public class GlideConfiguration implements GlideModule {
    private Context mContext;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        mContext = context;
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //这里也能使用SD卡存储,换成相应的路径即可
        //builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, getFileString(), 10 * 1024 * 1024));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, getFileString(), 10 * 1024 * 1024));
        builder.setMemoryCache(new LruResourceCache(getUseAbleSize()));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }

    private String getFileString() {
        File dirFile = new File(mContext.getCacheDir().getAbsolutePath().toString() + "GlideBitampCache");
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

