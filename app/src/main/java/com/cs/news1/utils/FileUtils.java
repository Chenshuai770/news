package com.cs.news1.utils;

import android.os.Environment;

import com.cs.news1.application.MyApplication;

import java.io.File;


public class FileUtils {
	public static final String CACHE = "cache";
	public static final String ICON = "icon";
	public static final String ROOT = "xshserice";
	/**
	 * 获取图片的缓存的路径
	 * @return
	 */
	public static File getIconDir(){
		return getDir(ICON);

	}
	/**
	 * 获取缓存路径
	 * @return
	 */
	public static File getCacheDir() {
		return getDir(CACHE);
	}
	public static File getDir(String cache) {
		StringBuilder path = new StringBuilder();
		if (isSDAvailable()) {
			path.append(Environment.getExternalStorageDirectory()
					.getAbsolutePath());
			path.append(File.separator);// '/'
			path.append(ROOT);// /mnt/sdcard/xshserice
			path.append(File.separator);
			path.append(cache);// /mnt/sdcard/xshserice/cache

		}else{
			File filesDir = MyApplication.getInstance().getApplicationContext().getCacheDir();//  cache  getFileDir file
			path.append(filesDir.getAbsolutePath());// /data/data/com.xshservice.xingfuxiangsheng/cache
			path.append(File.separator);///data/data/com.xshservice.xingfuxiangsheng/cache/
			path.append(cache);///data/data/com.xshservice.xingfuxiangsheng/cache/cache
		}
		File file = new File(path.toString());
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();// 创建文件夹
		}
		return file;
	}

	private static boolean isSDAvailable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {

			return false;
		}
	}


}
