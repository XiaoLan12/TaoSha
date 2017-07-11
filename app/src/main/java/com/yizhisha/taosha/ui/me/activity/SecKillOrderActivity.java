package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.fragment.MyOrderFragment;
import com.yizhisha.taosha.ui.me.fragment.SecKillOrderFragment;
import com.yizhisha.taosha.ui.me.presenter.SecKillOrderPresenter;

import java.util.ArrayList;

import butterknife.Bind;

public class SecKillOrderActivity extends BaseActivity{
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private String[] mTitles = {"全部", "待付款", "待发货", "待收货","已完成"};
    private int[] mType= {-1,0,1,2,3};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_second_kill_order;
    }
    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(SecKillOrderActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        for (int type : mType) {
            mFragments.add(SecKillOrderFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager,mTitles,this,mFragments);
    }
}
