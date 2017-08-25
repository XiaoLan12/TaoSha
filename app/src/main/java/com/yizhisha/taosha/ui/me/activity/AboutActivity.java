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
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.fragment.AboutTaoShaFragment;
import com.yizhisha.taosha.ui.me.fragment.AgreementStatementFragment;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment1;
import com.yizhisha.taosha.ui.me.fragment.CommonQuestionFragment2;
import com.yizhisha.taosha.ui.me.fragment.TaoShaIntroduceFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements AboutTaoShaFragment.switchFragmentListener {
    private AboutTaoShaFragment aboutTaoShaFragment;
    private TaoShaIntroduceFragment taoShaIntroduceFragment;
    private AgreementStatementFragment agreementStatementFragment;
    private FragmentManager fragmentManager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected void initView() {
        fragmentManager=getSupportFragmentManager();
        aboutTaoShaFragment=new AboutTaoShaFragment();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();
        transaction
                .replace(R.id.fragment, aboutTaoShaFragment,"aboutTaoShaFragment")
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
        switch (index) {
            case 1:
                if (taoShaIntroduceFragment == null) {
                    taoShaIntroduceFragment = new TaoShaIntroduceFragment();
                }
                switchFragment(taoShaIntroduceFragment, "taoShaIntroduceFragment");
                break;
            case 3:
                if (agreementStatementFragment == null) {
                    agreementStatementFragment = new AgreementStatementFragment();
                }
                switchFragment(agreementStatementFragment, "agreementStatementFragment");
                break;
        }
    }
}
