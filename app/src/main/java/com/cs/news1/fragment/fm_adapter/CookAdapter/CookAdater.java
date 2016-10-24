package com.cs.news1.fragment.fm_adapter.CookAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.News;
import com.cs.news1.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/24.
 */

public class CookAdater extends RecyclerView.Adapter<CookAdater.MyViewHolder> implements View.OnClickListener {
    private List<News.TngouBean> list=new ArrayList<>();
    private Context context;
    private OnChildClickListenner listenner;
    private RecyclerView recyclerView;

    /**
     * 设置监听事件
     * @param listenner
     */
    public void setOnChildClickListenner(OnChildClickListenner listenner){
        this.listenner=listenner;
    }
    /**
     * 提供数据的适合会调用到的方法
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView=recyclerView;
    }
    /**
     *
     * * 解绑数据
     * @param recyclerView
     */
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView=null;
    }

    /**
     * 构造方法传递参数
     * @param context
     * @param list
     */

    public CookAdater( Context context,List<News.TngouBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_cook,parent,false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PicassoUtils.loadImageWithHodler1(context,"http://tnfs.tngou.net/image"+list.get(position).getImg(),100,100,holder.image);
        holder.title.setText(list.get(position).getKeywords());
        holder.content.setText(list.get(position).getDescription());
       // Picasso.with(context).load("http://tnfs.tngou.net/image"+list.get(position).getImg()).into(holder.image);
    }

    /**
     * 绑定数据
     * @return
     */

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 进行头部和尾部的操作
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * 对点击事件进行了绑定
     * @param view
     */
    public void onClick(View view){
        if (recyclerView!=null&&listenner!=null) {
            int position = recyclerView.getChildAdapterPosition(view);
            listenner.onChildClick(recyclerView,view,position, (List<News.TngouBean>) list.get(position));

        }

    }

 class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.item_cook_title);
            content= (TextView) itemView.findViewById(R.id.item_cook_content);
            image= (ImageView) itemView.findViewById(R.id.item_cook_image);
        }
    }
    public interface OnChildClickListenner{
        void onChildClick(RecyclerView parent,View view,int position, List<News.TngouBean> list );
    }
}
