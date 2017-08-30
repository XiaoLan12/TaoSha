package com.yizhisha.taosha.ui.me.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.base.rx.RxBus;
import com.yizhisha.taosha.ui.me.fragment.MyOrderFragment;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.ClearEditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MyOrderAcitvity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.search_myorder_ll)
    LinearLayout searchLl;

    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.search_myorder_et)
    ClearEditText searchEt;
    @Bind(R.id.search_myorder_tv)
    TextView searchTv;
    @Bind(R.id.cacel_myorder_tv)
    TextView cacelTv;
    private String[] mTitles = {"全部", "待付款", "待发货", "待收货", "待评价","已评价"};
    private int[] mType = {0, 1, 2, 3, 4,5};
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_acitvity;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyOrderAcitvity.this);
            }
        });
        toolbar.setRightButtonIcon(RescourseUtil.getDrawable(R.drawable.icon_search));
        toolbar.showRightButton();
        toolbar.setRightButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchLl.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void initView() {
        for (int type : mType) {
            mFragments.add(MyOrderFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager, mTitles, this, mFragments);

    }
    @OnClick({R.id.search_myorder_tv,R.id.cacel_myorder_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_myorder_tv:
                MyOrderFragment fragment= (MyOrderFragment) mFragments.get(slidingTabLayout.getCurrentTab());
                fragment.search(searchEt.getText().toString().trim());
                break;
            case R.id.cacel_myorder_tv:
                searchLl.setVisibility(View.GONE);
                break;
        }
    }

}
