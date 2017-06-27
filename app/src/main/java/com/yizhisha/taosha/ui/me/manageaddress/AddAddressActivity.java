package com.yizhisha.taosha.ui.me.manageaddress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;

public class AddAddressActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    private int type=0;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initToolBar() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt("TYPE");
        if(type==1){
            toolbar.setTitle("修改地址");
        }
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(AddAddressActivity.this);
            }
        });
    }

    @Override
    protected void initView() {

    }
}
