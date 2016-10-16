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
    public PhotoAdapter(List<String > list,Context context) {
        this.list = list;
        this.context=context;
    }

    @Override
    public PhotoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(context).inflate(R.layout.item_pic,null);
        return new PhotoViewholder(root);
    }

    @Override
    public void onBindViewHolder(PhotoViewholder holder, int position) {
        //Bean.ResultsBean resultsBean=list.get(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
