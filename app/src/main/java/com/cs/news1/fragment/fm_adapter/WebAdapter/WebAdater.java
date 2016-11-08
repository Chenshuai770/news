package com.cs.news1.fragment.fm_adapter.WebAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.JuheNews;
import com.cs.news1.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/31.
 */

public class WebAdater  extends RecyclerView.Adapter<WebAdater.WebViewHodler>{
    private Context context;
    private List<JuheNews.ResultBean.DataBean> list;
    private ItemClickListern listern;

    public interface ItemClickListern{
        void ItemClick(View view,int possiton);

    }
    public void setOnItemClickListern(ItemClickListern listern){
        this.listern=listern;
    }
    public WebAdater(Context context, List<JuheNews.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 回收资源
     * @param parent
     * @param viewType
     * @return
     */

    @Override
    public WebViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_webivew,parent,false);
        return new WebViewHodler(view);
    }


    @Override
    public void onViewRecycled(WebViewHodler holder) {
        super.onViewRecycled(holder);
        //取消
        Picasso.with(holder.imageView.getContext()).cancelRequest(holder.imageView);
    }
    @Override
    public void onBindViewHolder(final WebViewHodler holder, final int position) {
        PicassoUtils.loadImageWithHodler1(context,list.get(position).getThumbnail_pic_s(),100,100,holder.imageView);
        holder.title.setText(list.get(position).getAuthor_name());
        holder.content.setText(list.get(position).getTitle());
        holder.time.setText(list.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listern!=null) {
                    int position1 = holder.getLayoutPosition();
                    listern.ItemClick(holder.itemView,position1);
                }
            }
        });
       /* holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               // int position1 = holder.getLayoutPosition();
                return false;
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class WebViewHodler extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView content;
        TextView time;
        public WebViewHodler(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_item_web);
            title= (TextView) itemView.findViewById(R.id.tv_web_title);
            content= (TextView) itemView.findViewById(R.id.tv_web_content);
            time= (TextView) itemView.findViewById(R.id.tv_web_time);
        }
    }
}
