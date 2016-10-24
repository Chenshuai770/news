package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.cs.news1.R;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoViewholder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public PhotoViewholder(View itemView) {

        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.item_photo_image);

     /*   itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "你点击了" + getAdapterPosition() + "个位置", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}

