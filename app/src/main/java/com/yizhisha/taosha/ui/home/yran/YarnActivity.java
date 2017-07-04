package com.yizhisha.taosha.ui.home.yran;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yizhisha.taosha.R;
import com.yizhisha.taosha.base.BaseActivity;
import com.yizhisha.taosha.bean.MyOrderTabEntity;
import com.yizhisha.taosha.ui.home.selectyarn.SelectYarnActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * Created by Administrator on 2017/7/2.
 */

public class YarnActivity extends BaseActivity {
    @Bind(R.id.commontablayout)
    CommonTabLayout commonTabLayout;
    private String[] mTitles = {"产品", "参数", "色卡", "详情","评论"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    @Bind(R.id.vp)
    ViewPager viewPager;
    @Bind(R.id.img_back)
    ImageView img_back;
    private FragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_yarn;
    }

    @Override
    protected void initToolBar() {

    }
    @Override
    protected void initView() {
        //设置状态栏颜色
        StatusBarCompat.setStatusBarColor(YarnActivity.this, this.getResources().getColor(R.color.gray1));
        mFragments.add(new ProductYarnFragnment());
        for (String title : new String[]{ "参数", "色卡", "详情","评论"}) {

            //mFragments.add(MyCollectFragment.getInstance(title));
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new MyOrderTabEntity(mTitles[i]));
        }
        commonTabLayout.setTabData(mTabEntities);


        mAdapter = new FragmentPagerAdapter(YarnActivity.this.getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(0);

        // 对ViewPager设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            int lastPosition;

            @Override
            public void onPageSelected(int position) {
                // 页面被选中

                // 修改position
                position = position % mFragments.size();

                // 替换位置
                lastPosition = position;
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //导航栏设置监听
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                commonTabLayout.setCurrentTab(position);
                viewPager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @OnClick({R.id.img_back})
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.img_back:
                finish_Activity(YarnActivity.this);
                break;
        }
    }
}
