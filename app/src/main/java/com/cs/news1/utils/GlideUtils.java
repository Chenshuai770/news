package com.cs.news1.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cs.news1.R;


/**
 * Created by chenshuai on 2016/11/7.
 */

public class GlideUtils {
    /**
     * 高度和宽度的封装，及压缩
     * @param context
     * @param path
     * @param wedth
     * @param height
     * @param imageView
     */
    public static void loadImageWithSize(Context context, String path, int wedth, int height, ImageView imageView){
        Glide.with(context).load(path).override(wedth,height).centerCrop().into(imageView);
    }

    /**
     * 占位图的封装
     * @param context
     * @param path
     * @param width
     * @param height
     * @param imageview
     */
    public static void loadImagewithHodler(Context context, String  path, int width, int height, ImageView imageview){
        Glide.with(context).load(path).override(width,height).placeholder(R.mipmap.noloading).error(R.mipmap.nosccess).fitCenter().centerCrop().into(imageview);
    }

    /**
     * 跳过内存缓存
     * @param context
     * @param path
     * @param width
     * @param height
     * @param imageview
     */
    public static void loadImagewithMc(Context context, String  path, int width, int height, ImageView imageview){
        Glide.with(context).load(path).override(width,height).placeholder(R.mipmap.noloading).error(R.mipmap.nosccess).skipMemoryCache(true).centerCrop().into(imageview);
    }

    /**跳过内存缓存
     * 在SD卡里面缓存
     *
     * @param context
     * @param path
     * @param width
     * @param height
     * @param imageview
     */
    public static void loadImagewithDisk(Context context, String  path, int width, int height, ImageView imageview){
        Glide.with(context).load(path).override(width,height).placeholder(R.mipmap.noloading).error(R.mipmap.nosccess).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(imageview);
    }

    /**
     * 必须开启线程才行
     * @param context
     */
    public void clearCache(final Context context){
        clearMemoreCache(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
              clearDiskCache(context);
            }
        });

    }
    /**
     * 清除内存缓存
     */
    public void clearMemoreCache(Context context){
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     * @param context
     */
    public void clearDiskCache(Context context){
        Glide.get(context).clearDiskCache();
    }



}
