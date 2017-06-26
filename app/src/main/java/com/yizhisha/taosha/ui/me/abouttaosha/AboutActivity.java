package com.yizhisha.taosha.ui.me.abouttaosha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;

import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolBar() {

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
