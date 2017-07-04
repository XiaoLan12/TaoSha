package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initView() {

    }
    @OnClick({R.id.about_rl3})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.about_rl3:
                startActivity(AgreementStatementActivity.class);
                break;
        }
    }
}
