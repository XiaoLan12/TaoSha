package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.fragment.MyFootprintFragment;

import java.util.ArrayList;

import butterknife.Bind;

public class MyFootprintActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;
    private String[] mTitles = {"全部", "棉纺纱", "麻纺纱", "毛纺纱","化纤纱","混纺纱"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initToolBar() {
        toolbar.setTitle("我的足迹");
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyFootprintActivity.this);
            }
        });
    }
    @Override
    protected void initView() {
        for (String title : mTitles) {
            mFragments.add(MyFootprintFragment.getInstance(title));
        }
        slidingTabLayout.setViewPager(viewPager,mTitles,this,mFragments);
    }
}
