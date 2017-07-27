package com.hasee.bh_takeout.AddressManage.adapter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.gson.Gson;
import com.hasee.bh_takeout.MyApplication;
import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.bean.Category;
import com.hasee.bh_takeout.bean.HomeInfo;
import com.hasee.bh_takeout.bean.HomeItem;
import com.hasee.bh_takeout.bean.Promotion;
import com.hasee.bh_takeout.presenter.fragment.HomeFragmentPresenter;
import com.hasee.bh_takeout.ui.fragment.HomeFragment;
import com.hasee.bh_takeout.utils.UiUtils;
import com.squareup.picasso.Picasso;


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
public void setData(HomeInfo data){
    this.data = data;
    notifyDataSetChanged();
}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //RecyclerView.ViewHolder holder = null;
        View view = null;
        switch (viewType){
            case TYPE_HEAD:
                /*holder = new HeadHolder(View.inflate(MyApplication.getContext(),R.layout.item_title,null));
                break;*/
                view = View.inflate(MyApplication.getContext(), R.layout.item_title,null);
                return new HeadHolder(view);
            case TYPE_SELLER:
               /* holder = new SellerHolder(View.inflate(MyApplication.getContext(),R.layout.item_seller,null));
                break;*/
                view = View.inflate(MyApplication.getContext(),R.layout.item_seller,null);
                return new SellerHolder(view);
            case TYPE_RECOMMENT:
                /*holder = new RecommentHolder(View.inflate(MyApplication.getContext(),R.layout.item_division,null));
                break;*/
                view = View.inflate(MyApplication.getContext(),R.layout.item_division,null);
                return new RecommentHolder(view);
        }
        return null;
    }
//seller类型的个数,recomment类型的个数
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = holder.getItemViewType();
        switch (type){
            case TYPE_HEAD:
               initTitleView(holder);
                break;
            case TYPE_SELLER:
                initSellerView(holder,position);
                break;
            case TYPE_RECOMMENT:
                initRecommentView(holder,position);
                break;
        }
    }
//加载大家都在找的页面
    private void initRecommentView(RecyclerView.ViewHolder holder, int position) {
        RecommentHolder recommentHolder = (RecommentHolder) holder;
        TableRow trTop = recommentHolder.trTop;
        trTop.removeAllViews();
        TableRow trBottom = recommentHolder.trBottom;
        trBottom.removeAllViews();
        HomeItem homeItem = data.body.get(position - 1);
        List<String> recommentInfos = homeItem.recommendInfos;
        int size = recommentInfos.size();
        int temp = 0;
        if (size%2 == 0){
            temp = size/2;
        }else{
            temp = size/2+1;
        }
        for (int i = 0; i < temp; i++) {
            TextView tvTop = new TextView(MyApplication.getContext());
            tvTop.setGravity(Gravity.CENTER_HORIZONTAL);
            tvTop.setPadding(UiUtils.dip2Px(25),UiUtils.dip2Px(10),UiUtils.dip2Px(20),UiUtils.dip2Px(10));
            tvTop.setTextColor(MyApplication.getContext().getResources().getColor(R.color.colorTextDefault));
            tvTop.setText(recommentInfos.get(i));
            trTop.addView(tvTop);
            if (temp +i <size){
                TextView tvBottom = new TextView(MyApplication.getContext());
                tvBottom.setGravity(Gravity.CENTER_HORIZONTAL);
                tvBottom.setPadding(UiUtils.dip2Px(25),UiUtils.dip2Px(10),UiUtils.dip2Px(20),UiUtils.dip2Px(10));
                tvBottom.setTextColor(MyApplication.getContext().getResources().getColor(R.color.colorTextDefault));
                tvBottom.setText(recommentInfos.get(temp+i));
                trBottom.addView(tvBottom);
            }
        }
    }
//加载店家信息的条目页面
    private void initSellerView(RecyclerView.ViewHolder holder, int position) {
        SellerHolder sellerHolder = (SellerHolder) holder;
        RatingBar ratingBar = sellerHolder.mRatingBar;
        TextView tvTitle = sellerHolder.tvTitle;
        TextView tvCount = sellerHolder.tvCount;

        HomeItem homeItem = data.body.get(position - 1);
        String name = homeItem.seller.getName();
        sellerHolder.tvTitle.setText(name);
        //条目点击事件
        setListener(sellerHolder,homeItem,position);
    }

    private void setListener(SellerHolder sellerHolder, HomeItem homeItem, final int position) {
        sellerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyApplication.getContext(),"点击的第"+position+"个条目",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //添加轮播图
    private void initTitleView(RecyclerView.ViewHolder holder) {
        HeadHolder titleHolder = (HeadHolder) holder;
        //加载轮播图
        initSliderLayout(titleHolder);
        //加载分类View
        initCategoryView(titleHolder);
    }
//加载分类View
    private void initCategoryView(HeadHolder titleHolder) {
        LinearLayout ll = titleHolder.ll;
        ArrayList<Category>categorieList = data.head.categorieList;
        int temp = 0;
        if (categorieList.size()%2==1){
            temp = categorieList.size()/2+1;
        }else{
            temp = categorieList.size()/2;
        }
        int j = 1;
        int k = 2;
        int l = 0;
        int m = 1;
        for (int i = 0; i < temp; i++) {
        View mView = View.inflate(MyApplication.getContext(), R.layout.item_home_head_category, null);
            ImageView top_iv = (ImageView) mView.findViewById(R.id.top_iv);
            TextView top_tv = (TextView) mView.findViewById(R.id.top_tv);
            ImageView bottom_iv = (ImageView) mView.findViewById(R.id.bottom_iv);
            TextView bottom_tv = (TextView) mView.findViewById(R.id.bottom_tv);

            Category categoryTop = data.head.categorieList.get(l);
            l+=2;
            String nameTop = categoryTop.name;
            String picTop = categoryTop.pic;
            picTop = "http://192.168.125.37:8080/TakeoutService/imgs/category/"+j+".png";
            j+=2;
            top_tv.setText(nameTop);
            Picasso.with(MyApplication.getContext()).load(picTop).into(top_iv);
            if (categorieList.size() % 2 != 1){
                Category categoryBottom = categorieList.get(m);
                m+=2;
                String nameBottom = categoryBottom.name;
                String picBottom = categoryBottom.pic;
                picBottom = "http://192.168.125.37:8080/TakeoutService/imgs/category/"+k+".png";
                k+=2;
                bottom_tv.setText(nameBottom);
                Picasso.with(MyApplication.getContext()).load(picBottom).into(bottom_iv);
            }
                ll.addView(mView);
        }
    }

    //加载轮播图的方法
    private void initSliderLayout(HeadHolder titleHolder) {
        SliderLayout sl = titleHolder.sl;
        HashMap<String,String> url_maps = new HashMap<>();
        ArrayList<Promotion> promotionList = data.head.promotionList;
        for (int i = 0; i < promotionList.size(); i++) {
            Promotion promotion = promotionList.get(i);
            String pic = promotion.pic;
            pic = "http://192.168.125.37:8080/TakeoutService/imgs/promotion/"+(i+1)+".jpg";
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
//条目的类型
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
//加载附近商家的Holder
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
//加载大家都在找的Holder
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
