package com.yizhisha.taosha.ui.login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;

public class LoginFragmentActivity extends BaseActivity implements LoginFragment.switchFragmentListener {
    private FindPwdFragment findPwdFragment;
    private RegisterFragment registerFragment;
    private LoginFragment loginFragment;
    private PhoneLoginFragment phoneLoginFragment;
    private Fragment currentFragment;
    private FragmentManager fragmentManager=getSupportFragmentManager();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_fragment;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        loginFragment=new LoginFragment();
        transaction.add(R.id.fragment,loginFragment).commit();
    }
    public void switchFragment(Fragment fragment){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(fragment!=null){
            if(currentFragment!=null){
                transaction.hide(currentFragment);
            }
            if(!fragment.isAdded()){
                transaction.add(R.id.fragment,fragment);
                transaction.addToBackStack(null).commit();
            }else{
                if(currentFragment!=null){
                    transaction.hide(currentFragment);
                }
                transaction.show(fragment).commit();

            }

            currentFragment=fragment;
        }
    }

    @Override
    public void switchFragment(int index) {
        if(index==1){
            if(findPwdFragment==null){
                findPwdFragment=new FindPwdFragment();
            }
            switchFragment(findPwdFragment);
        }else if(index==2){
            if(registerFragment==null){
                registerFragment=new RegisterFragment();
            }
            switchFragment(registerFragment);
        }else if(index==3){
            if(phoneLoginFragment==null){
                phoneLoginFragment=new PhoneLoginFragment();
            }
            switchFragment(phoneLoginFragment);
        }
    }
}
