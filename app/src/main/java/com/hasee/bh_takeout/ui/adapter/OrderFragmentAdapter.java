package com.hasee.bh_takeout.ui.adapter;



import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
//        将创建的view进行点击事件
        view.setOnClickListener(this);
        orderViewHolder.again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"点击打开商家的界面",Toast.LENGTH_SHORT).show();
            }
        });
        orderViewHolder.neirong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return orderViewHolder;
    }
/*绑定数据*/
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null){
            //注意,这里使用getTag 的方法获取数据
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    class OrderViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout rl;
        Button again;
        RelativeLayout neirong;
        public OrderViewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl);
            again = (Button) itemView.findViewById(R.id.again);
            neirong = (RelativeLayout) itemView.findViewById(R.id.rl_neirong);
        }
    }
    //设置条目点击事件,在OrderFragment里面使用回调来使用
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,int position);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
