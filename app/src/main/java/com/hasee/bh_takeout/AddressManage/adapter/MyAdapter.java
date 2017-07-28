package com.hasee.bh_takeout.AddressManage.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.bean.HomeInfo;
import com.hasee.bh_takeout.bean.HomeItem;
import com.hasee.bh_takeout.bean.Promotion;
import com.hasee.bh_takeout.presenter.fragment.HomeFragmentPresenter;
import com.hasee.bh_takeout.ui.fragment.HomeFragment;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dell on 2017/7/24.
 */

public class MyAdapter extends RecyclerView.Adapter{
    //分析条目的三个类型
    private final int TYPE_HEAD = 0;
    private final int TYPE_SELLER = 1;
    private final  int TYPE_RECOMMENT= 2;

    private Activity activity;
    private HomeInfo data = new HomeInfo();

    public MyAdapter(FragmentActivity activity) {
        this.activity = activity;
    }
    //
public void setData(HomeInfo data){
    this.data = data;
    notifyDataSetChanged();
}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_HEAD:
                holder = new HeadHolder(View.inflate(MyApplication.getContext(),R.layout.item_title,null));
                break;
            case TYPE_SELLER:
                holder = new SellerHolder(View.inflate(MyApplication.getContext(),R.layout.item_seller,null));
                break;
            case TYPE_RECOMMENT:
                holder = new RecommentHolder(View.inflate(MyApplication.getContext(),R.layout.item_division,null));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = holder.getItemViewType();
        switch (type){
            case TYPE_HEAD:
               initTitleView(holder);
        }
    }
//添加轮播图
    private void initTitleView(RecyclerView.ViewHolder holder) {
        HeadHolder titleHolder = (HeadHolder) holder;
        //加载轮播图
        initSliderLayout(titleHolder);
    }
//加载轮播图的方法
    private void initSliderLayout(HeadHolder titleHolder) {
        SliderLayout sl = titleHolder.sl;
        HashMap<String,String> url_maps = new HashMap<>();
        ArrayList<Promotion> promotionList = data.head.promotionList;
        for (int i = 0; i < promotionList.size(); i++) {
            Promotion promotion = promotionList.get(i);
            String pic = promotion.pic;
            url_maps.put("Number"+i,pic);
        }
        for (String desc : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(MyApplication.getContext());
            textSliderView.description(desc).image(url_maps.get(desc));
            sl.addSlider(textSliderView);
        }
    }

    @Override
    public int getItemCount() {
        if (data.body!=null){
            return data.body.size()+1;
        }else {
            return 0;
        }

    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (position==0){
            type = TYPE_HEAD;
        }else{
            HomeItem item = data.body.get(position-1);
            type = item.type==0?TYPE_SELLER:TYPE_RECOMMENT;
        }
        return type;
    }
//加载头的Holder
    private class HeadHolder extends RecyclerView.ViewHolder {
        private SliderLayout sl;
        private LinearLayout ll;
        public HeadHolder(View itemView) {
            super(itemView);
            sl = (SliderLayout) itemView.findViewById(R.id.slider);
            ll = (LinearLayout) itemView.findViewById(R.id.catetory_container);
        }
    }

    private class SellerHolder extends RecyclerView.ViewHolder {
        private  TextView tvCount;
        private TextView tvTitle;
        private RatingBar mRatingBar;
        public SellerHolder(View view) {
            super(view);
            tvCount = (TextView) view.findViewById(R.id.tv_count);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            mRatingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        }
    }

    private class RecommentHolder extends RecyclerView.ViewHolder {
        private TableRow trTop;
        private TableRow trBottom;

        public RecommentHolder(View view) {
            super(view);
            trTop = (TableRow) view.findViewById(R.id.tr_top);
            trBottom = (TableRow) view.findViewById(R.id.tr_bottom);
        }
    }
}
