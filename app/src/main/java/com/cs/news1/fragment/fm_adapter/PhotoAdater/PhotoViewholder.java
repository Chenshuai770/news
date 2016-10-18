package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cs.news1.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoViewholder extends RecyclerView.ViewHolder {
    public Context mContext;
    public SimpleDraweeView mpic;
    public CardView mCardView;
    public PhotoViewholder(View itemView , final Context mContext) {

        super(itemView);
        this.mContext=mContext;
        mpic= (SimpleDraweeView) itemView.findViewById(R.id.item_photo_sdv);
        //mCardView= (CardView) itemView.findViewById(R.id.item_photo_cardview);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "你点击了"+getAdapterPosition()+"个位置", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

