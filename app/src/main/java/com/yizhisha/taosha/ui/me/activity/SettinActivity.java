package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.utils.GlideUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettinActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.head_set_iv)
    ImageView headIv;
    @Bind(R.id.username_set_tv)
    TextView usernameTv;


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
        if(AppConstant.infoBean!=null){
            String url="http://www.taoshamall.com/data/attached/avatar/100x100/";
            GlideUtil.getInstance().LoadContextCircleBitmap(this,url+AppConstant.infoBean.getAvatar(),headIv);
            usernameTv.setText(AppConstant.infoBean.getUsername());
        }
    }

    @OnClick({R.id.changepwd_rl, R.id.changephone_rl, R.id.changeoneinfo_rl,
            R.id.managedeladdress_rl, R.id.changeweixin_rl})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.changeoneinfo_rl:
                Bundle bundle = new Bundle();
                bundle.putInt("TARGET", 0);
                startActivity(SetInfoActivity.class, bundle);
                break;
            case R.id.changepwd_rl:
                Bundle bundle1 = new Bundle();
                bundle1.putInt("TARGET", 1);
                startActivity(SetInfoActivity.class, bundle1);
                break;
            case R.id.changephone_rl:
                Bundle bundle2 = new Bundle();
                bundle2.putInt("TARGET", 2);
                startActivity(SetInfoActivity.class, bundle2);
                break;
            case R.id.changeweixin_rl:
                Bundle bundle3 = new Bundle();
                bundle3.putInt("TARGET", 3);
                startActivity(SetInfoActivity.class, bundle3);
                break;
            case R.id.managedeladdress_rl:
                startActivity(MyAddressActivity.class);
                break;

        }
    }
}
