package com.hasee.bh_takeout.ui.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.hasee.bh_takeout.R;

import java.util.Map;

/**
 * Created by dell on 2017/7/27.
 */

public class MapActivity extends Activity implements LocationSource {
    private AMapLocationClient mlocationClient;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    private AMap mAMap;
    private MapView mMapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.mv_map);
        //这个方法必须写
        mMapView.onCreate(savedInstanceState);
        init();
    }
//初始化
    private void init() {
        if (mAMap!=null){
            mAMap = mMapView.getMap();
            setUpMap();
        }
    }
//设置一些Map的属性
    private void setUpMap() {
        mAMap.setLocationSource(this);//设置定位监听
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示
        mAMap.setMyLocationEnabled(true);//设置为true表示显示定位层并可触发定位,false表示不可触发定位,默认为false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转3种
        //跟随：LOCATION_TYPE_MAP_FOLLOW
        //旋转：LOCATION_TYPE_MAP_ROTATE
        //定位：LOCATION_TYPE_LOCATE
        mAMap.setMyLocationType(AMap.MAP_TYPE_NORMAL);
    }

//激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
