package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoViewholder extends RecyclerView.ViewHolder {
    private TextView textView;
  //SimpleDraweeView pic;
    ImageView imageView;



    public PhotoViewholder(View itemView) {
        super(itemView);
       //imageView= (ImageView) itemView.findViewById(R.id.iv_photo);
        textView=(TextView)itemView.findViewById(R.id.item_name);

    }
}

