package com.chong.recyclerviewdemo;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chong on 2015/12/20.
 * 瀑布流StaggeredAdapter
 */
public class StaggeredAdapter extends SimpleAdapter {
    /**
     * item高度
     */
    private List<Integer> mHeight;

    public StaggeredAdapter(Context context, List<String> data) {
        super(context, data);

        mHeight = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            // 随机产生高度
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // 设置item的LayoutParams，改变item高度
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.itemView.setLayoutParams(lp);

        holder.tv.setText(mData.get(position));

        // 点击
        setUpItemEvent(holder);
    }
}

