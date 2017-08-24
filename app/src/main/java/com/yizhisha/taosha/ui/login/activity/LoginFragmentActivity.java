package com.yizhisha.taosha.ui.login.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.ui.login.fragment.FindPwdFragment;
import com.yizhisha.taosha.ui.login.fragment.LoginFragment;
import com.yizhisha.taosha.ui.login.fragment.PhoneLoginFragment;

public class LoginFragmentActivity extends BaseActivity implements LoginFragment.switchFragmentListener {
    private FindPwdFragment findPwdFragment;
    private LoginFragment loginFragment;
    private PhoneLoginFragment phoneLoginFragment;
    private Fragment currentFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_fragment;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        loginFragment=new LoginFragment();
        transaction.add(R.id.fragment,loginFragment).commit();
        currentFragment=loginFragment;

    }
    /**
     * hide和show切换Fragment
     */
    private void switchFragment(Fragment targetFragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            if(currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction
                    .add(R.id.fragment, targetFragment,tag).addToBackStack(null)
                    .commit();
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }

    @Override
    public void switchFragment(int index) {
        if(index==1){
            if(findPwdFragment==null){
                findPwdFragment=new FindPwdFragment();
            }
            switchFragment(findPwdFragment,"findPwdFragment");
        }else if(index==2){
            if(phoneLoginFragment==null){
                phoneLoginFragment=new PhoneLoginFragment();
            }
            switchFragment(phoneLoginFragment,"phoneLoginFragment");
        }
    }
}
