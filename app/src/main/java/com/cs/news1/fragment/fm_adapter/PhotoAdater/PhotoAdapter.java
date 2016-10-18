package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs.news1.R;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewholder> {
    private List<String> list;
    private Context context;
  

    public PhotoAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public PhotoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_pic, parent, false);
        return new PhotoViewholder(root, context);

    }
  /*  private void updateItemtHeight(int height, View root) {

        CardView cardView = (CardView)root .findViewById(R.id.item_photo_cardview);
        View child = root.findViewById(R.id.item_photo_sdv);
        CardView.LayoutParams layoutParams = (CardView.LayoutParams) child.getLayoutParams();
        layoutParams.height = height;
        cardView.updateViewLayout(child,layoutParams);
    }*/

    @Override
    public void onBindViewHolder(PhotoViewholder holder, int position) {
        //updateItemtHeight(200,holder.itemView);
        holder.mpic.setImageURI(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
