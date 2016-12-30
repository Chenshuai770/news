package com.cs.news1.fragment.photosAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cs.news1.R;
import com.cs.news1.entry.Photos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/11/30.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoHolder> {
    private Context context;
    private List<Photos.ResultsBean> mList;
    private View mFooterView;
    private static final int TYPE_FOOTER = 0;  //说明是带有Footer的
    private static final int TYPE_NORMAL = 1;  //说明是带有Normal的
    private ArrayList mHeight=new ArrayList();
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

    }
    public OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public View getmFooterView() {
        return mFooterView;
    }
    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount()-1);
    }

    @Override
    public int getItemCount() {
        if (mFooterView==null) {
            return mList.size();
        }else {
            return mList.size()+1;
        }
    }
    /**
     * 1
     *  重写这个方法，很重要，是加入Header和Footer的关键
     *  我们通过判断item的类型，从而绑定不同的view
     */
    @Override
    public int getItemViewType(int position) {
        if (mFooterView==null){
            return TYPE_NORMAL;
        }
        if (position==getItemCount()-1){
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;

    }

    public PhotosAdapter(Context context, List<Photos.ResultsBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterView != null && viewType==TYPE_FOOTER) {
            return new PhotoHolder(mFooterView);
        }
        View view= LayoutInflater.from(context).inflate(R.layout.item_photo,parent,false);
        PhotoHolder photoHolder = new PhotoHolder(view);
        return photoHolder;
    }

    @Override
    public void onBindViewHolder(final PhotoHolder holder, int position) {
        if (getItemViewType(position)==TYPE_NORMAL) {
            for (int i = 0; i < mList.size(); i++) {
                mHeight.add( (int) (100 + Math.random() * 200));
            }
            ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
            lp.height= (int) mHeight.get(position);
            int imgWidth = context.getResources().getDisplayMetrics().widthPixels / 3;
            lp.width=imgWidth;
            Glide.with(context)
                    .load(mList.get(position).getUrl())
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //获取系统的中的item实际数量
                        int position1 = holder.getLayoutPosition();
                        //建立关联
                        mOnItemClickLitener.onItemClick(((PhotoHolder) holder).itemView,position1);
                    }
                });
            }


        }else if (getItemViewType(position)==TYPE_FOOTER){
            return;
        }

    }


    class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        LinearLayout layout;

        public PhotoHolder(View itemView) {
            super(itemView);
            if (itemView==mFooterView) {
               return;
            }
            imageView= (ImageView) itemView.findViewById(R.id.iv_item_image);

        }
    }
}
