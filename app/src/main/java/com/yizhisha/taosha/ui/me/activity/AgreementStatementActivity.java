package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;

import butterknife.Bind;

public class AgreementStatementActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement_statement;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(AgreementStatementActivity.this);
            }
        });
    }

    @Override
    protected void initView() {

    }
}
