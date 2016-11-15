package com.cs.news1.fragment.fm_adapter.PhotoAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cs.news1.R;
import com.cs.news1.entry.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/14.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewholder> {
    private List<Photo.ResultsBean> list;
    private Context context;
    private ArrayList mHeight=new ArrayList();
    private int imageWidth;
    private ArrayList<String > myUrl=new ArrayList<>();
    public OnItemClickLitener mOnItemClickLitener;
    /**
     * 6
     *  定义点击事件的接口
     */

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
  

    public PhotoAdapter(List<Photo.ResultsBean> list, Context context) {
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
    public void onBindViewHolder(final PhotoViewholder holder, final int position) {
        /*WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth()/2-5;
        int height = wm.getDefaultDisplay().getHeight()/3;

        Glide.with(context)
                .load(list.get(position).getUrl())
                .override(width,(int)(height+Math.random()*100))
                .placeholder(R.mipmap.noloading)
                .error(R.mipmap.nosccess)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.imageView);*/


     // PicassoUtils.loadImageWithHodler1(context,list.get(position).getUrl(),width,(int)(height+Math.random()*100),holder.imageView);
        /*ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height= (int) heights.get(position);
        holder.itemView.setLayoutParams(params);//把params设置给itemView布局*/
        //updateItemtHeight(200,holder.itemView);
        //Picasso.with(context).load(list.get(position)).into(holder.imageView);
        for (int i = 0; i <list.size() ; i++) {
            mHeight.add( (int) (100 + Math.random() * 200));
            myUrl.add(list.get(i).getUrl());
        }
        ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
        lp.height = (int) mHeight.get(position);
        imageWidth=context.getResources().getDisplayMetrics().widthPixels/3;
        lp.width=imageWidth;

        Glide.with(context)
                .load(myUrl.get(position))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getLayoutPosition();
                mOnItemClickLitener.onItemClick(holder.imageView,position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


}
