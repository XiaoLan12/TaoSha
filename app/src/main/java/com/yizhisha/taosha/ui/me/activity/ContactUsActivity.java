package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseFragment;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.login.fragment.LoginFragment;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment1;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment2;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment3;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment4;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment5;
import com.yizhisha.taosha.ui.me.fragment.ContactUsFrqment;

import butterknife.Bind;

public class ContactUsActivity extends BaseActivity implements ContactUsFrqment.switchFragmentListener {
    private ContactUsFrqment contactUsFrqment;
    private CommonQuestionFragment1 commonQuestionFragment1;
    private CommonQuestionFragment2 commonQuestionFragment2;
    private CommonQuestionFragment3 commonQuestionFragment3;
    private CommonQuestionFragment4 commonQuestionFragment4;
    private CommonQuestionFragment5 commonQuestionFragment5;
    private FragmentManager fragmentManager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        fragmentManager=getSupportFragmentManager();
        contactUsFrqment=new ContactUsFrqment();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction
                .replace(R.id.fragment, contactUsFrqment,"contactUsFrqment")
                .commit();
    }
    /**
     * hide和show切换Fragment
     */
    private void switchFragment(Fragment targetFragment, String tag) {
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
            transaction
                    .replace(R.id.fragment, targetFragment,tag).addToBackStack(tag)
                    .commit();

    }

    @Override
    public void switchFragment(int index) {
        switch (index){
            case 1:
                if(commonQuestionFragment1==null){
                    commonQuestionFragment1=new CommonQuestionFragment1();
                }
                switchFragment(commonQuestionFragment1,"commonQuestionFragment1");
                break;
            case 2:
                if(commonQuestionFragment2==null){
                    commonQuestionFragment2=new CommonQuestionFragment2();
                }
                switchFragment(commonQuestionFragment2,"commonQuestionFragment2");
                break;
            case 3:
                if(commonQuestionFragment3==null){
                    commonQuestionFragment3=new CommonQuestionFragment3();
                }
                switchFragment(commonQuestionFragment3,"commonQuestionFragment3");
                break;
            case 4:
                if(commonQuestionFragment4==null){
                    commonQuestionFragment4=new CommonQuestionFragment4();
                }
                switchFragment(commonQuestionFragment4,"commonQuestionFragment4");
                break;
            case 5:
                if(commonQuestionFragment5==null){
                    commonQuestionFragment5=new CommonQuestionFragment5();
                }
                switchFragment(commonQuestionFragment5,"commonQuestionFragment5");
                break;
        }
    }
}
