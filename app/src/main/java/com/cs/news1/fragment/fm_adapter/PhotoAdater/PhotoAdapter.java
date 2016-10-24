package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.cs.news1.R;
import com.cs.news1.entry.Bean;
import com.cs.news1.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewholder> {
    private List<Bean.ResultsBean> list;
    private Context context;
    private ArrayList heights;
  

    public PhotoAdapter(List<Bean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public PhotoViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewholder(root);

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
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth()/2-5;
        int height = wm.getDefaultDisplay().getHeight()/3;

        PicassoUtils.loadImageWithHodler1(context,list.get(position).getUrl(),width,(int)(height+Math.random()*100),holder.imageView);
        /*ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height= (int) heights.get(position);
        holder.itemView.setLayoutParams(params);//把params设置给itemView布局*/
        //updateItemtHeight(200,holder.itemView);
        //Picasso.with(context).load(list.get(position)).into(holder.imageView);




    }
    private void getRadomHeight(List<Bean.ResultsBean> lists){
        this.list=lists;
        heights=new ArrayList();
        for (int i = 0; i <lists.size() ; i++) {
            heights.add((int)(200+Math.random()*400));

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
