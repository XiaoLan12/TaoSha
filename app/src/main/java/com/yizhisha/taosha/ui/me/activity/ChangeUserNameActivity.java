package com.yizhisha.taosha.ui.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.event.ChangeUserInfoEvent;
import com.yizhisha.taosha.utils.CheckUtils;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import butterknife.Bind;

public class ChangeUserNameActivity extends BaseActivity{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.userinfo_et)
    ClearEditText mEtUserInfo;
    private int type;
    @Bind(R.id.save_changeusername_btn)
     Button mBtSava;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_user_name;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(ChangeUserNameActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        type=bundle.getInt("TYPE");
        if(type==1){
            toolbar.setTitle("修改用户名");
            mEtUserInfo.setHint("请输入您的用户名");
        }else if(type==2){
            toolbar.setTitle("修改真实姓名");
            mEtUserInfo.setHint("请输入您的真实姓名");
        }else if(type==3){
            toolbar.setTitle("修改邮箱");
            mEtUserInfo.setHint("请输入您的邮箱");
        }
        mBtSava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value=mEtUserInfo.getText().toString().trim();
                switch(type){
                    case 1:
                        if(value == null || value.equals("")){
                            ToastUtil.showbottomShortToast("请输入您的用户名");
                            return;
                        }
                        RxBus.$().postEvent(new ChangeUserInfoEvent(type,value));
                        finish_Activity(ChangeUserNameActivity.this);
                        break;
                    case 2:
                        if(value == null || value.equals("")){
                            ToastUtil.showbottomShortToast("请输入您的真实姓名");
                            return;
                        }
                        RxBus.$().postEvent(new ChangeUserInfoEvent(type,value));
                        finish_Activity(ChangeUserNameActivity.this);
                        break;
                    case 3:
                        if(value == null || value.equals("")){
                            ToastUtil.showbottomShortToast("请输入您的邮箱");
                            return;
                        }
                        if(!CheckUtils.isEmail(value)){
                            ToastUtil.showbottomShortToast("请输入正确的邮箱");
                            return;
                        }
                        RxBus.$().postEvent(new ChangeUserInfoEvent(type,value));
                        finish_Activity(ChangeUserNameActivity.this);
                        break;
                }
            }
        });

    }
}
