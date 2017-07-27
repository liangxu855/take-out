package com.hasee.bh_takeout.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.hasee.bh_takeout.R;

/**
 * Created by laidoudou on 2017/7/26.
 */

public class OrderItemActivity extends BaseActivity{
    private ImageButton ib_goback;
    private ImageButton ib_iphone;
    private String phoneNumber = "123456789";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_item);
        ib_goback = (ImageButton) findViewById(R.id.ib_goback);
        ib_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ib_iphone = (ImageButton) findViewById(R.id.ib_iphone);
        ib_iphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
                startActivity(dialIntent);

            }
        });
    }
}
