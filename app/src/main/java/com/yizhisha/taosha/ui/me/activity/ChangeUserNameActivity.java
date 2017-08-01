package com.yizhisha.taosha.ui.me.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yizhisha.taosha.AppConstant;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.event.ChangeUserInfoEvent;
import com.yizhisha.taosha.ui.me.contract.ChangeUserInfoContract;
import com.yizhisha.taosha.ui.me.presenter.ChangeUserInfoPresenter;
import com.yizhisha.taosha.utils.ToastUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class ChangeUserNameActivity extends BaseActivity<ChangeUserInfoPresenter>
implements ChangeUserInfoContract.View{
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
                if(value == null || value.equals("")){
                    ToastUtil.showbottomShortToast("请输入");
                    return;
                }
                load(type,value);
            }
        });

    }
    private void load(int type,String value){
        Map<String,String> map=new HashMap<>();
        map.put("uid", String.valueOf(AppConstant.UID));
        switch (type){
            case 1:
                map.put("username",value);
                break;
            case 2:
                map.put("linkman",value);
                break;
            case 3:
                map.put("email",value);
                break;
        }
        mPresenter.changeUserInfo(map);
    }
    @Override
    public void changeSuccess(String msg) {
        ToastUtil.showbottomShortToast(msg);
        RxBus.$().postEvent(new ChangeUserInfoEvent(type,mEtUserInfo.getText().toString()));
    }
    @Override
    public void loadFail(String msg) {
        ToastUtil.showbottomShortToast(msg);
    }
}
