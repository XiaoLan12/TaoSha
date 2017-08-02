package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.ui.me.fragment.ChangeBindFragment;
import com.yizhisha.taosha.ui.me.fragment.ChangeOneInfoFragment;
import com.yizhisha.taosha.ui.me.fragment.ChangePwdFragment;

public class SetInfoActivity extends BaseActivity {
    private ChangePwdFragment changePwdFragment;
    private ChangeBindFragment changeBindFragment;
    private ChangeOneInfoFragment changeOneInfoFragment;
    private int currTarget=0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_info;
    }
    @Override
    protected void initToolBar() {

    }
    private void switchFragment(Fragment fragment){
        if (fragment == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //目标Fragment替换原来的Fragment
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }
    @Override
    protected void initView() {
        Bundle bundle=getIntent().getExtras();
        currTarget=bundle.getInt("TARGET");
        switch (currTarget){
            case 0:
                if(changeOneInfoFragment ==null){
                    changeOneInfoFragment =new ChangeOneInfoFragment();
                }
                switchFragment(changeOneInfoFragment);
                break;
            case 1:
                if(changePwdFragment==null){
                    changePwdFragment=new ChangePwdFragment();
                }
                switchFragment(changePwdFragment);

                break;
            case 2:
                changeBindFragment =new ChangeBindFragment();
                Bundle bundle1=new Bundle();
                bundle1.putInt("TYPE",1);
                changeBindFragment.setArguments(bundle1);
                switchFragment(changeBindFragment);
                break;
            case 3:
                changeBindFragment =new ChangeBindFragment();
                Bundle bundle2=new Bundle();
                bundle2.putInt("TYPE",2);
                changeBindFragment.setArguments(bundle2);
                switchFragment(changeBindFragment);
                break;

        }
    }

}
