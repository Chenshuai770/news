package com.cs.news1.fragment.fm_adapter.JokeAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Recreation;
import com.cs.news1.utils.PicassoUtils;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/16.
 */

public class JokeAdpter extends RecyclerView.Adapter<JokeAdpter.JokeViewHolder> {
    private Context context;
    private List<Recreation.TngouBean> mDatas;


    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private static final String URL_HEADER="http://tnfs.tngou.net/image";

    //HeaderView, FooterView
    private View mHeaderView;
    private View mFooterView;
    public OnItemClickLitener mOnItemClickLitener;
    /**
     * 6
     *  定义点击事件的接口
     */

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public JokeAdpter(Context context, List<Recreation.TngouBean> list) {
        this.context= context;
        this.mDatas = list;
    }
    /**
     * HeaderView和FooterView的get和set函数
     */

    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);//加入头部
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);//注意这里
    }
    /**
     * 1
     *  重写这个方法，很重要，是加入Header和Footer的关键
     *  我们通过判断item的类型，从而绑定不同的view
     */
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null&& mFooterView==null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;

        }
        if (position == getItemCount()-1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }


    /**
     * 2
     * 根据type类型来创建对应的数量
     * 这里的具体位置onBindViewHolder里面有体现
     * adapter入口顺序
     * @return
     */
    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mDatas.size();
        }else if (mHeaderView == null && mFooterView != null) {
            return mDatas.size() + 1;
        }else if (mHeaderView != null && mFooterView == null) {
            return mDatas.size() + 1;
        }else {
            return mDatas.size()+2;
        }
    }

    /**
     * 3
     * 这里的视图载入xml是在调用的recycview里面去调用的，因为要载入父类必须是recycleview
     * 来创建header或者footer
     * 根据1的type类型来创建view,绑定视图
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new JokeViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new JokeViewHolder(mFooterView);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_joke_normal,parent,false);
        //这边可以做一些属性设置，甚至事件监听绑定
        //view.setBackgroundColor(Color.RED);
        return new JokeViewHolder(view);
    }

    /**
     *5
     * 载入具体的事件
     * 并加入点击事件
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(final JokeViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_NORMAL) {
            if (holder instanceof JokeViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.title.setText(mDatas.get(position-1).getKeywords());
                holder.content.setText(mDatas.get(position-1).getDescription());
                PicassoUtils.loadImageWithHodler1(context,URL_HEADER+mDatas.get(position-1).getImg(),100,100,holder.image);
                if (mOnItemClickLitener != null) {
                    ((JokeViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //获取系统的中的item实际数量
                            int position1 = holder.getLayoutPosition();
                            //建立关联
                            mOnItemClickLitener.onItemClick(((JokeViewHolder) holder).itemView,position1);
                        }
                    });
                }
                return;
            }
        }else if (getItemViewType(position)==TYPE_HEADER) {
           /* List<String > url=new ArrayList<>();
            for (int i = 0; i <mDatas.size() ; i++) {
                url.add(URL_HEADER+mDatas.get(i).getImg());

            }*/

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(((JokeViewHolder) holder).itemView,pos);

                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos=holder.getLayoutPosition();//测试下这里
                    mOnItemClickLitener.onItemLongClick(view,pos);
                    return false;
                }
            });

            return;

        }else {
            return;

        }

    }



    /**
     * 4
     * 这个是第四布，按顺序来就没那么困难了
     * 根据type来对应在载入布局
     * 并找到对应的id
     * 内部类成员变量必须public
     * 要不然调用不到
     *
     */
    class JokeViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;
        public ImageView image;
        public TextView title;
        public TextView content;
        public JokeViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView){
                banner= (Banner) itemView.findViewById(R.id.item_joke_banner);
                return;
            }
            if (itemView == mFooterView){
                return;
            }
            //载入文本
            image= (ImageView) itemView.findViewById(R.id.item_jook_image);
            title= (TextView) itemView.findViewById(R.id.item_jook_title);
            content= (TextView) itemView.findViewById(R.id.item_jook_content);

        }
    }
}
