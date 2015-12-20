package com.chong.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chong on 2015/12/20.
 * 自定义SimpleAdapter
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.MyViewHolder> {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<String> mData;

    /**
     * 点击接口
     */
    public interface OnItemClickLinstener {
        void onItemClick(View view, int position);
    }

    /**
     * 长按点击接口
     */
    public interface OnItemLongClickLinstener {
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLinstener mOnItemClickLinstener;
    private OnItemLongClickLinstener mOnItemLongClickLinstener;

    public void setOnIteClickLinstener(OnItemClickLinstener mOnItemClickLinstener) {
        this.mOnItemClickLinstener = mOnItemClickLinstener;
    }

    public void setOnItemLongClickLinstener(OnItemLongClickLinstener mOnItemLongClickLinstener) {
        this.mOnItemLongClickLinstener = mOnItemLongClickLinstener;
    }

    public SimpleAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_single_textview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
        setUpItemEvent(holder);
    }


    /**
     * 点击事件
     *
     * @param holder holder
     */
    protected void setUpItemEvent(final MyViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLinstener != null) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemClickLinstener.onItemClick(holder.itemView, layoutPosition);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickLinstener != null) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemLongClickLinstener.onItemLongClick(holder.itemView, layoutPosition);
                }
                // 返回true，LongClick消费点击事件，拦截Click
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_tv);
        }
    }

    public void addData(int position) {
        mData.add(position, "Add one");

        notifyItemInserted(position);
    }

    public void deleteData(int position) {
        mData.remove(position);

        notifyItemRemoved(position);
    }
}


