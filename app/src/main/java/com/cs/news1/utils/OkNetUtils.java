package com.cs.news1.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by chenshuai on 2016/11/4.
 * 判读是否有网络连接
 */

public class OkNetUtils {
    public static final  boolean hasNetWorkConection(Context context){
        // 获取连接活动管理器
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取连接的网络信息 对应的节点配置
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo!=null && networkInfo.isAvailable());//true为真,false为假
    }

}
