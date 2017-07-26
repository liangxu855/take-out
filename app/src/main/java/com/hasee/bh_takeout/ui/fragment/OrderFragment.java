package com.hasee.bh_takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hasee.bh_takeout.R;
import com.hasee.bh_takeout.ui.activity.OrderItemActivity;
import com.hasee.bh_takeout.ui.adapter.OrderFragmentAdapter;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by ywf on 2017/3/24.
 */
public class OrderFragment extends Fragment {

    @InjectView(R.id.xrecyclerview)
    com.jcodecraeer.xrecyclerview.XRecyclerView mXRecyclerview;
    private boolean isPullRefresh;
    private OrderFragmentAdapter orderFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置recycleView布局
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity());
        //设置横向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerview.setLayoutManager(linearLayoutManager);

        //设置recycleview下拉刷新进度条的样式
        mXRecyclerview.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        //设置recyclerview上拉加载更多的样式
        mXRecyclerview.setLoadingMoreProgressStyle(ProgressStyle.LineScalePulseOutRapid);

        //支持下拉刷新
        mXRecyclerview.setPullRefreshEnabled(true);
        //支持加载更多
        mXRecyclerview.setLoadingMoreEnabled(true);

        //设置recyclerview下拉刷新和加载更多的监听
        mXRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {//下拉刷新
                isPullRefresh = true;
                sendRequest();
            }

            @Override
            public void onLoadMore() {//上拉加载更多
                sendRequest();
            }
        });
        //设置初始化状态为刷新状态。作用： 界面初始加载时，刷新数据。
        mXRecyclerview.refresh();
        //设置适配器
        orderFragmentAdapter = new OrderFragmentAdapter(getActivity());
        mXRecyclerview.setAdapter(orderFragmentAdapter);
        //设施条目的适配器
        //创建并设置Adapter
        orderFragmentAdapter.setOnItemClickListener(new OrderFragmentAdapter.OnRecyclerViewItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "你点击了"+position+"条目", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getActivity().getApplicationContext(), OrderItemActivity.class);
                startActivity(intent);

            }
        });
    }

    private void sendRequest() {
        if (isPullRefresh){
            mXRecyclerview.refreshComplete();
            isPullRefresh = !isPullRefresh;
        }else{
            mXRecyclerview.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
