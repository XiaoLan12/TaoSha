package com.yizhisha.taosha.ui.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;

public class LoginFragmentActivity extends BaseActivity implements LoginFragment.switchFragmentListener {
    private FindPwdFragment findPwdFragment;
    private RegisterFragment registerFragment;
    private LoginFragment loginFragment;
    private PhoneLoginFragment phoneLoginFragment;
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

    }
    /**
     * hide和show切换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment,String tag)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment,fragment,tag).addToBackStack(null).commit();
    }

    @Override
    public void switchFragment(int index) {
        if(index==1){
            if(findPwdFragment==null){
                findPwdFragment=new FindPwdFragment();
            }
            switchFragment(findPwdFragment,"findPwdFragment");
        }else if(index==2){
            if(registerFragment==null){
                registerFragment=new RegisterFragment();
            }
            switchFragment(registerFragment,"registerFragment");
        }else if(index==3){
            if(phoneLoginFragment==null){
                phoneLoginFragment=new PhoneLoginFragment();
            }
            switchFragment(phoneLoginFragment,"phoneLoginFragment");
        }
    }
}
