package com.hasee.bh_takeout.ui.adapter;



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

public class OrderFragmentAdapter extends RecyclerView.Adapter<OrderFragmentAdapter.ViewHolder>{
   private Context context;
   public OrderFragmentAdapter(FragmentActivity activity) {
       this.context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_xr, parent, false));
    }
    /*数据绑定
    @param holser
    @param position
    * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_content.setText("我是数据"+position);

    }


    @Override
    public int getItemCount() {
        return 30;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_content;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
