package com.cs.news1.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by ben on 2016/8/11.
 */
public class CacheUtils {
    //把整个json文件写到本地文件中
    //写入缓存
    public static void saveLocal(String json, String location) {
        BufferedWriter bw = null;
        try {
            File dir = FileUtils.getCacheDir();
            File file = new File(dir, location);
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(json);//把这个json文件保存起来
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取本地缓存
    public static String loadLocal(String location) {
        File dir = FileUtils.getCacheDir();//获取缓存所在的文件夹
        File file = new File(dir, location);
        StringWriter sw = new StringWriter();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = null;
            while ((str = br.readLine()) != null) {
                sw.write(str);
            }
            return sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
