package com.hasee.bh_takeout.ui.adapter;



import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasee.bh_takeout.R;

/**
 * Created by laidoudou on 2017/7/24.
 * xRecyclerview的数据适配器
 */

public class OrderFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
   private Activity activity;
    private OrderViewHolder orderViewHolder;


    public OrderFragmentAdapter(Activity activity) {
       this.activity = activity;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_xr,parent,false);
        orderViewHolder = new OrderViewHolder(view);
        //将创建的view进行点击事件
        view.setOnClickListener(this);
        return orderViewHolder;
    }
/*绑定数据*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        orderViewHolder = (OrderViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public void onClick(View view) {

    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
        public OrderViewHolder(View itemView) {
            super(itemView);
        }
    }
    //设置条目点击事件,在OrderFragment里面使用回调来使用
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
