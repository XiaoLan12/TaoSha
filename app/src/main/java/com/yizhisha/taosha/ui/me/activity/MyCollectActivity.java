package com.yizhisha.taosha.ui.me.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.ActivityManager;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.base.BaseToolbar;
import com.yizhisha.taosha.ui.me.fragment.MyCollectFragment;
import com.yizhisha.taosha.ui.me.fragment.MyOrderFragment;
import com.yizhisha.taosha.utils.RescourseUtil;
import com.yizhisha.taosha.widget.ClearEditText;
import com.yizhisha.taosha.widget.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class MyCollectActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    BaseToolbar toolbar;
    @Bind(R.id.slidingtablayout)
    SlidingTabLayout slidingTabLayout;
    @Bind(R.id.vp)
    ViewPager viewPager;

    @Bind(R.id.search_myorder_ll)
    LinearLayout searchLl;
    @Bind(R.id.search_myorder_et)
    ClearEditText searchEt;
    @Bind(R.id.search_myorder_tv)
    TextView searchTv;
    @Bind(R.id.cacel_myorder_tv)
    TextView cacelTv;

    private String[] mTitles = {"全部", "棉纺纱", "麻纺纱", "毛纺纱","化纤纱","混纺纱","花式纱"};
    private int[] mType= {0, 544, 539, 540,545,541,542};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initToolBar() {
        toolbar.setLeftButtonOnClickLinster(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getActivityMar().finishActivity(MyCollectActivity.this);
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
            mFragments.add(MyCollectFragment.getInstance(type));
        }
        slidingTabLayout.setViewPager(viewPager,mTitles,this,mFragments);
    }
    @OnClick({R.id.search_myorder_tv,R.id.cacel_myorder_tv})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_myorder_tv:
                MyCollectFragment fragment= (MyCollectFragment) mFragments.get(slidingTabLayout.getCurrentTab());
                fragment.search(searchEt.getText().toString().trim());
                break;
            case R.id.cacel_myorder_tv:
                searchLl.setVisibility(View.GONE);
                break;
        }
    }
}
