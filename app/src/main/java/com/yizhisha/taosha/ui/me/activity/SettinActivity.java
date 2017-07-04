package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;
import butterknife.OnClick;

public class SettinActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_settin;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(SettinActivity.this);
            }
        });
    }
    @Override
    protected void initView() {

    }

    @OnClick({R.id.changepwd_rl,R.id.changephone_rl,R.id.changeoneinfo_rl,
    R.id.managedeladdress_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.changeoneinfo_rl:
                Bundle bundle=new Bundle();
                bundle.putInt("TARGET",0);
                startActivity(SetInfoActivity.class,bundle);
                break;
            case R.id.changepwd_rl:
                Bundle bundle1=new Bundle();
                bundle1.putInt("TARGET",1);
                startActivity(SetInfoActivity.class,bundle1);
                break;
            case R.id.changephone_rl:
                Bundle bundle2=new Bundle();
                bundle2.putInt("TARGET",2);
                startActivity(SetInfoActivity.class,bundle2);
                break;
            case R.id.managedeladdress_rl:
                startActivity(MyAddressActivity.class);
                break;

        }
    }


}
