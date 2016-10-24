package com.cs.news1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cs.news1.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 创建Picasso的工具库
 * 并进行压缩
 * Created by chenshuai on 2016/10/22.
 */

public class PicassoUtils {
    /**
     *高和宽的封装，以及压缩
     * @param context
     * @param path
     * @param width
     * @param height
     * @param image
     */
    public static void  loadImageWithSize(Context context, String  path, int width, int height, ImageView image){
        Picasso.with(context).load(path).resize(width,height).centerCrop().into(image);
    }

    public static void loadImageWithHodler1(Context context, String  path, int width, int height, ImageView image){
        Picasso.with(context).load(path).resize(width,height).placeholder(R.mipmap.noloading).error(R.mipmap.nosccess).centerCrop().into(image);
    }

    /**
     * 占位图的选取
     * @param context
     * @param path
     * @param resID
     * @param image
     */
    public static void loadImageWithHodler2(Context context, String  path, int resID, ImageView image){
        Picasso.with(context).load(path).fit().placeholder(resID).into(image);//fit是图片自适应大小，相当于wrap

    }

    /**
     * 对图片进行裁剪
     * @param context
     * @param path
     *
     */
    public static void loadImageWithCrop(Context context, String  path,ImageView image){
      //  Picasso.with(context).load(path).fit().placeholder(resID).into(image);//fit是图片自适应大小，相当于wrap
        Picasso.with(context).load(path).transform(new CropSquaerTransformation()).into(image);
    }

    /**
     * 实现对图片的自定义裁剪
     *
     */
    public static class CropSquaerTransformation implements Transformation{
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x=(source.getWidth()-size)/2;//根据宽度进行裁剪
            int y=(source.getHeight()-size)/2;//根据宽度进行裁剪
            Bitmap result = Bitmap.createBitmap(source, x, y, size,size);
            if (result != null) {
                source.recycle();//二次裁剪后必须回收资源
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }

}
