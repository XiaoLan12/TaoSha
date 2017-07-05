package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.adapter.MyOrderAdapter;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseRVActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.bean.FreeSmpleEntity;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.ui.me.fragment.FreeSampleFragment;
import com.yizhisha.taosha.ui.me.fragment.MyCollectFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FreeSampleActivity extends BaseRVActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;
    private String[] mTitles = {"全部","待发货", "待收货","已完成"};
    private int[] mType= {-1, 0, 1,2};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_acitvity;
    }

    @Override
    protected void initToolBar() {
        toolbar.setTitle("免费拿样");
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(FreeSampleActivity.this);
            }
        });
    }

    @Override
    protected void initView() {
        for (int type : mType) {
            mFragments.add(FreeSampleFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager,mTitles,this,mFragments);
    }
    /*private List<String> getAllData(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        data.add("待付款");
        data.add("待收货");
        data.add("待发货");
        data.add("已完成");

        return data;
    }
    private List<String> getData1(){
        List<String> data=new ArrayList<>();
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");

        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        data.add("待付款");
        return data;
    }
    private List<String> getData2(){
        List<String> data=new ArrayList<>();
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");

        data.add("待发货");
        data.add("待发货");
        data.add("待发货");
        data.add("待发货");


        return data;
    }
    private List<String> getData3(){
        List<String> data=new ArrayList<>();
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");

        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        data.add("待收货");
        return data;
    }
    private List<String> getData4(){
        List<String> data=new ArrayList<>();
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");

        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        data.add("已完成");
        return data;
    }*/
}
